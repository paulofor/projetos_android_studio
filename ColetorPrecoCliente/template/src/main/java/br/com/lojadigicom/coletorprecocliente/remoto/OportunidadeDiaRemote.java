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

import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeDiaContract;
import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;

import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.telefonia.Telefone;

public class OportunidadeDiaRemote {
	
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
        String objeto = "OportunidadeDia";
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
                contexto.getContentResolver().delete(OportunidadeDiaContract.buildDeleteAllRecreate(), null, null);
            }
            contexto.getContentResolver().bulkInsert(OportunidadeDiaContract.getContentUri(), vetor);

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
                contexto.getContentResolver().delete(OportunidadeDiaContract.buildDeleteAllRecreate(), null, null);
            }
            contexto.getContentResolver().bulkInsert(OportunidadeDiaContract.getContentUri(), vetor);
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
        	json.put("IdOportunidadeDia",c.getString(OportunidadeDiaContract.COL_ID_OPORTUNIDADE_DIA));
			json.put("NomeProduto",c.getString(OportunidadeDiaContract.COL_NOME_PRODUTO));
			json.put("UrlProduto",c.getString(OportunidadeDiaContract.COL_URL_PRODUTO));
			json.put("UrlImagem",c.getString(OportunidadeDiaContract.COL_URL_IMAGEM));
			json.put("PrecoVendaAtual",c.getString(OportunidadeDiaContract.COL_PRECO_VENDA_ATUAL));
			json.put("PrecoVendaAnterior",c.getString(OportunidadeDiaContract.COL_PRECO_VENDA_ANTERIOR));
			json.put("PercentualAjusteVenda",c.getString(OportunidadeDiaContract.COL_PERCENTUAL_AJUSTE_VENDA));
			json.put("QuantidadeParcelaAtual",c.getString(OportunidadeDiaContract.COL_QUANTIDADE_PARCELA_ATUAL));
			json.put("PrecoParcelaAtual",c.getString(OportunidadeDiaContract.COL_PRECO_PARCELA_ATUAL));
			json.put("PrecoParcelaAnterior",c.getString(OportunidadeDiaContract.COL_PRECO_PARCELA_ANTERIOR));
			json.put("QuantidadeParcelaAnterior",c.getString(OportunidadeDiaContract.COL_QUANTIDADE_PARCELA_ANTERIOR));
			json.put("NomeLojaVirtual",c.getString(OportunidadeDiaContract.COL_NOME_LOJA_VIRTUAL));
			json.put("DataUltimaPrecoAnterior",c.getString(OportunidadeDiaContract.COL_DATA_ULTIMA_PRECO_ANTERIOR));
			json.put("DataInicioPrecoAtual",c.getString(OportunidadeDiaContract.COL_DATA_INICIO_PRECO_ATUAL));
			json.put("PrecoMedio",c.getString(OportunidadeDiaContract.COL_PRECO_MEDIO));
			json.put("PrecoMinimo",c.getString(OportunidadeDiaContract.COL_PRECO_MINIMO));
			json.put("IdNaturezaProdutoPa",c.getString(OportunidadeDiaContract.COL_ID_NATUREZA_PRODUTO_PA));
		
        return json;
    }
     private JSONObject JSonAtributosSinc(Cursor c) throws JSONException {
        JSONObject json = JSonAtributos(c);
        json.put("OperacaoSinc",c.getString(OportunidadeDiaContract.COL_OPERACAO_SINC));
        return json;
    }
    

    private ContentValues ContentValuesAtributos(JSONObject obj) throws JSONException {
        ContentValues valores = new ContentValues();
		valores.put(OportunidadeDiaContract.COLUNA_ID_OPORTUNIDADE_DIA, obj.getString("IdOportunidadeDia"));
		
		valores.put(OportunidadeDiaContract.COLUNA_NOME_PRODUTO, obj.getString("NomeProduto"));
		
		valores.put(OportunidadeDiaContract.COLUNA_URL_PRODUTO, obj.getString("UrlProduto"));
		
		valores.put(OportunidadeDiaContract.COLUNA_URL_IMAGEM, obj.getString("UrlImagem"));
		
		valores.put(OportunidadeDiaContract.COLUNA_PRECO_VENDA_ATUAL, obj.getString("PrecoVendaAtual"));
		
		valores.put(OportunidadeDiaContract.COLUNA_PRECO_VENDA_ANTERIOR, obj.getString("PrecoVendaAnterior"));
		
		valores.put(OportunidadeDiaContract.COLUNA_PERCENTUAL_AJUSTE_VENDA, obj.getString("PercentualAjusteVenda"));
		
		valores.put(OportunidadeDiaContract.COLUNA_QUANTIDADE_PARCELA_ATUAL, obj.getString("QuantidadeParcelaAtual"));
		
		valores.put(OportunidadeDiaContract.COLUNA_PRECO_PARCELA_ATUAL, obj.getString("PrecoParcelaAtual"));
		
		valores.put(OportunidadeDiaContract.COLUNA_PRECO_PARCELA_ANTERIOR, obj.getString("PrecoParcelaAnterior"));
		
		valores.put(OportunidadeDiaContract.COLUNA_QUANTIDADE_PARCELA_ANTERIOR, obj.getString("QuantidadeParcelaAnterior"));
		
		valores.put(OportunidadeDiaContract.COLUNA_NOME_LOJA_VIRTUAL, obj.getString("NomeLojaVirtual"));
		
		valores.put(OportunidadeDiaContract.COLUNA_DATA_ULTIMA_PRECO_ANTERIOR, UtilDatas.getDataLong(obj.getString("DataUltimaPrecoAnterior")));
		
		valores.put(OportunidadeDiaContract.COLUNA_DATA_INICIO_PRECO_ATUAL, UtilDatas.getDataLong(obj.getString("DataInicioPrecoAtual")));
		
		valores.put(OportunidadeDiaContract.COLUNA_PRECO_MEDIO, obj.getString("PrecoMedio"));
		
		valores.put(OportunidadeDiaContract.COLUNA_PRECO_MINIMO, obj.getString("PrecoMinimo"));
		
		valores.put(OportunidadeDiaContract.COLUNA_ID_NATUREZA_PRODUTO_PA, obj.getString("IdNaturezaProdutoPa"));
		
		
		
        return valores;
    }

}


