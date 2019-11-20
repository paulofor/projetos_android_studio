package  br.com.lojadigicom.coletorprecocliente.remoto;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;

import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.telefonia.Telefone;

public class InteresseProdutoRemote {
	
	private int getVersaoSincronizacao() {
        return 3;
    }

    // Alterado
    public void listaSincronizadaAlteracao(Cursor cursor, Context contexto) throws JSONException {
        Map<String,String> params = new HashMap<String,String>();
        String url = getUrl("ListaSincronizadaAlteracao");
        String saida = " [ ";
        boolean primeiro = true;
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            if (primeiro) {
                primeiro = false;
            } else {
                saida += " , ";
            }
            // saida += " " + ((ObjetoSinc)item).JSonSinc() + " ";
            saida += " " + JSonAtributosSinc(cursor).toString();
            cursor.moveToNext();
        }
        saida += " ] ";
        params.put("lista", saida);
        params.put("versao",String.valueOf(getVersaoSincronizacao()));
        //if (!Dispositivo.isTablet(contexto)) {
        params.put("tmp",Telefone.getNumero(contexto));
        //} else {
        //    params.put("cod",Dispositivo.getId(contexto));
        //}
        DCLog.d(DCLog.SINCRONIZACAO_JSON,this,"Enviando...lista<NaturezaProduto>:" + saida);
        String resultado = getHttpResponse(url,params);
        // mensagem, recebi lista com dois elementos.
    }

    private byte[] montaParametros(Map<String,String> params) throws UnsupportedEncodingException {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        return postDataBytes;
    }

    private String getHttpResponse(String urlEnt, Map<String,String> params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            byte[] parametros = montaParametros(params);
            URL url = new URL(urlEnt);
			DCLog.d(DCLog.SINCRONIZACAO_JSON,this,urlEnt);
			
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length", String.valueOf(parametros.length));
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(parametros);

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            return buffer.toString();
        } catch (IOException e) {
        	DCLog.e(DCLog.SINCRONIZACAO_JSON,this,e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        return null;
    }

   

    private String getUrl(String metodo) {
        String objeto = "InteresseProduto";
        String ultimaUrl = "http://" + DataSourceRemoto.getServer() + "/" + (DataSourceRemoto.getAplicacao()!=null?DataSourceRemoto.getAplicacao() + "/":"") + objeto + "WS/" + metodo + getRequest();
        return ultimaUrl;
        //return "http://" + server + "/" + aplicacao + "/" + objeto + "/" + metodo + getFiltro().getRequest();
    }

    public String getRequest() {
        String saida = "";
        //if (_CodigoPesquisa!=null)
        //    saida += "&CodigoPesquisa="+_CodigoPesquisa;
        return saida;
    }

    public int listaSincronizadaDao(Context contexto, boolean delete) throws JSONException {
        

        int tam = 0;
        String urlEnt = getUrl("ListaSincronizada");
        //if (!Dispositivo.isTablet(contexto)) {
        String numeroTel = Telefone.getNumero(contexto);
        urlEnt += "?tmp=" + numeroTel + "&versao=" + getVersaoSincronizacao();
        //} else {
        //    String codId = Dispositivo.getId(contexto);
        //    url += "?cod=" + codId + "&versao=" + getVersaoSincronizacao();
        //}
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {

            URL url = new URL(urlEnt);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return tam;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            JSONObject obj = null;
            if ((line = reader.readLine()) != null) {
                obj = new JSONObject(line);
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return tam;
            }

            JSONArray lista = obj.getJSONArray("Lista");
            tam = lista.length();
         
            ContentValues[] vetor = new ContentValues[lista.length()];
            for (int x = 0 ; x < lista.length() ; x++) {
                JSONObject item = lista.getJSONObject(x);
                ContentValues valor = this.ContentValuesAtributos(item);
                vetor[x] = valor;
            }
            if (delete) {
                contexto.getContentResolver().delete(InteresseProdutoContract.buildDeleteAllRecreate(), null, null);
            }
            contexto.getContentResolver().bulkInsert(InteresseProdutoContract.getContentUri(), vetor);

 			DCLog.d(DCLog.SINCRONIZACAO_JSON, this, "Url:" + url);
            DCLog.d(DCLog.SINCRONIZACAO_JSON,this,buffer.toString());
            
            return tam;
        } catch (Exception e) {
        	DCLog.e(DCLog.SINCRONIZACAO_JSON,this,"Url:" + urlEnt);
        	DCLog.e(DCLog.SINCRONIZACAO_JSON,this,e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        return tam;
    }

  	public int listaSincronizadaDaoAtualizaPorId(Context contexto, boolean delete, String codigoAplicacao) throws JSONException {
        int tam = 0;
        String urlEnt = getUrl("ListaSincronizada");
        String numeroTel = Telefone.getNumero(contexto);
        urlEnt += "?tmp=" + numeroTel + "&versao=" + getVersaoSincronizacao() + "&app=" + codigoAplicacao;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlEnt);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return tam;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            JSONObject obj = null;
            if ((line = reader.readLine()) != null) {
                obj = new JSONObject(line);
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return tam;
            }
            JSONArray lista = obj.getJSONArray("Lista");
            tam = lista.length();
            ContentValues[] vetor = new ContentValues[lista.length()];
            for (int x = 0 ; x < lista.length() ; x++) {
                JSONObject item = lista.getJSONObject(x);
                ContentValues valor = this.ContentValuesAtributos(item);
                //vetor[x] = valor;
                String where = "id_interesse_produto=" + valor.getAsString("id_interesse_produto");
                contexto.getContentResolver().update(InteresseProdutoContract.buildAtualiza(),valor,where,null);
            }

            DCLog.d(DCLog.SINCRONIZACAO_JSON, this, "Url:" + url);
            DCLog.d(DCLog.SINCRONIZACAO_JSON,this,buffer.toString());
            return tam;
        } catch (Exception e) {
            DCLog.e(DCLog.SINCRONIZACAO_JSON,this,"Url:" + urlEnt);
            DCLog.e(DCLog.SINCRONIZACAO_JSON,this,e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        return tam;
    }

    public int listaSincronizadaDao(Context contexto, boolean delete, String codigoAplicacao) throws JSONException {
        int tam = 0;
        String urlEnt = getUrl("ListaSincronizada");
        String numeroTel = Telefone.getNumero(contexto);
        urlEnt += "?tmp=" + numeroTel + "&versao=" + getVersaoSincronizacao() + "&app=" + codigoAplicacao;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlEnt);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return tam;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            JSONObject obj = null;
            if ((line = reader.readLine()) != null) {
                obj = new JSONObject(line);
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return tam;
            }
            JSONArray lista = obj.getJSONArray("Lista");
            tam = lista.length();
            ContentValues[] vetor = new ContentValues[lista.length()];
            for (int x = 0 ; x < lista.length() ; x++) {
                JSONObject item = lista.getJSONObject(x);
                ContentValues valor = this.ContentValuesAtributos(item);
                vetor[x] = valor;
            }
            if (delete) {
                contexto.getContentResolver().delete(InteresseProdutoContract.buildDeleteAllRecreate(), null, null);
            }
            contexto.getContentResolver().bulkInsert(InteresseProdutoContract.getContentUri(), vetor);
 			DCLog.d(DCLog.SINCRONIZACAO_JSON, this, "Url:" + url);
            DCLog.d(DCLog.SINCRONIZACAO_JSON,this,buffer.toString());
            return tam;
        } catch (Exception e) {
        	DCLog.e(DCLog.SINCRONIZACAO_JSON,this,"Url:" + urlEnt);
        	DCLog.e(DCLog.SINCRONIZACAO_JSON,this,e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        return tam;
    }



 	
    private JSONObject JSonAtributos(Cursor c) throws JSONException {
        int pos = 0;
        JSONObject json = new JSONObject();
        	
		
		json.put("IdInteresseProduto",c.getString(InteresseProdutoContract.COL_ID_INTERESSE_PRODUTO));
		
			
		
		json.put("Nota",c.getString(InteresseProdutoContract.COL_NOTA));
		
			
		
		json.put("Data",c.getString(InteresseProdutoContract.COL_DATA));
		
			
		
		json.put("Valor",c.getString(InteresseProdutoContract.COL_VALOR));
		
			
		
		// Tipo Logico precisa ser convertido para a leitura correta no servidor.
		json.put("Espera",c.getInt(InteresseProdutoContract.COL_ESPERA)==1);
		
			
		
		// Tipo Logico precisa ser convertido para a leitura correta no servidor.
		json.put("Monitora",c.getInt(InteresseProdutoContract.COL_MONITORA)==1);
		
			
		
		// Tipo Logico precisa ser convertido para a leitura correta no servidor.
		json.put("VisualizacaoConcluida",c.getInt(InteresseProdutoContract.COL_VISUALIZACAO_CONCLUIDA)==1);
		
			
		
		json.put("PrecoMedio",c.getString(InteresseProdutoContract.COL_PRECO_MEDIO));
		
			
		
		json.put("PrecoMinimo",c.getString(InteresseProdutoContract.COL_PRECO_MINIMO));
		
			
		
		// Tipo Logico precisa ser convertido para a leitura correta no servidor.
		json.put("Novo",c.getInt(InteresseProdutoContract.COL_NOVO)==1);
		
			
		
		// Tipo Logico precisa ser convertido para a leitura correta no servidor.
		json.put("Mudanca",c.getInt(InteresseProdutoContract.COL_MUDANCA)==1);
		
			
		
		json.put("DiferencaAnterior",c.getString(InteresseProdutoContract.COL_DIFERENCA_ANTERIOR));
		
			
		
		json.put("MinhaAvaliacao",c.getString(InteresseProdutoContract.COL_MINHA_AVALIACAO));
		
			
		
		json.put("DataUltimaMudanca",c.getString(InteresseProdutoContract.COL_DATA_ULTIMA_MUDANCA));
		
			
		
		json.put("PrecoAnterior",c.getString(InteresseProdutoContract.COL_PRECO_ANTERIOR));
		
			
		
		json.put("DataUltimaVerificacao",c.getString(InteresseProdutoContract.COL_DATA_ULTIMA_VERIFICACAO));
		
			
		
		json.put("IdProdutoClienteRa",c.getString(InteresseProdutoContract.COL_ID_PRODUTO_CLIENTE_RA));
		
			
		
		json.put("IdUsuarioS",c.getString(InteresseProdutoContract.COL_ID_USUARIO_S));
		
		
        return json;
    }
     private JSONObject JSonAtributosSinc(Cursor c) throws JSONException {
        JSONObject json = JSonAtributos(c);
        json.put("OperacaoSinc",c.getString(InteresseProdutoContract.COL_OPERACAO_SINC));
        return json;
    }
    

    private ContentValues ContentValuesAtributos(JSONObject obj) throws JSONException {
        ContentValues valores = new ContentValues();
		valores.put(InteresseProdutoContract.COLUNA_ID_INTERESSE_PRODUTO, obj.getString("IdInteresseProduto"));
		
		valores.put(InteresseProdutoContract.COLUNA_NOTA, obj.getString("Nota"));
		
		valores.put(InteresseProdutoContract.COLUNA_DATA, obj.getLong("Data"));
		
		valores.put(InteresseProdutoContract.COLUNA_VALOR, obj.getString("Valor"));
			
		int valEspera = 0;
		if ("True".equals(obj.getString("Espera"))) {
			valEspera = 1;
		}
		valores.put(InteresseProdutoContract.COLUNA_ESPERA, valEspera);
			
		int valMonitora = 0;
		if ("True".equals(obj.getString("Monitora"))) {
			valMonitora = 1;
		}
		valores.put(InteresseProdutoContract.COLUNA_MONITORA, valMonitora);
			
		int valVisualizacaoConcluida = 0;
		if ("True".equals(obj.getString("VisualizacaoConcluida"))) {
			valVisualizacaoConcluida = 1;
		}
		valores.put(InteresseProdutoContract.COLUNA_VISUALIZACAO_CONCLUIDA, valVisualizacaoConcluida);
		
		valores.put(InteresseProdutoContract.COLUNA_PRECO_MEDIO, obj.getString("PrecoMedio"));
		
		valores.put(InteresseProdutoContract.COLUNA_PRECO_MINIMO, obj.getString("PrecoMinimo"));
			
		int valNovo = 0;
		if ("True".equals(obj.getString("Novo"))) {
			valNovo = 1;
		}
		valores.put(InteresseProdutoContract.COLUNA_NOVO, valNovo);
			
		int valMudanca = 0;
		if ("True".equals(obj.getString("Mudanca"))) {
			valMudanca = 1;
		}
		valores.put(InteresseProdutoContract.COLUNA_MUDANCA, valMudanca);
		
		valores.put(InteresseProdutoContract.COLUNA_DIFERENCA_ANTERIOR, obj.getString("DiferencaAnterior"));
		
		valores.put(InteresseProdutoContract.COLUNA_MINHA_AVALIACAO, obj.getString("MinhaAvaliacao"));
		
		valores.put(InteresseProdutoContract.COLUNA_DATA_ULTIMA_MUDANCA, obj.getLong("DataUltimaMudanca"));
		
		valores.put(InteresseProdutoContract.COLUNA_PRECO_ANTERIOR, obj.getString("PrecoAnterior"));
		
		valores.put(InteresseProdutoContract.COLUNA_DATA_ULTIMA_VERIFICACAO, obj.getLong("DataUltimaVerificacao"));
		
		valores.put(InteresseProdutoContract.COLUNA_ID_PRODUTO_CLIENTE_RA, obj.getString("IdProdutoClienteRa"));
		
		valores.put(InteresseProdutoContract.COLUNA_ID_USUARIO_S, obj.getString("IdUsuarioS"));
		
		
		
        return valores;
    }

}


