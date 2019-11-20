package  br.com.lojadigicom.repcom.remoto;


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

import br.com.lojadigicom.repcom.data.contract.ClienteContract;
import br.com.lojadigicom.repcom.framework.util.UtilDatas;

import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.framework.telefonia.Telefone;

public class ClienteRemote {
	
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
        String objeto = "Cliente";
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
                contexto.getContentResolver().delete(ClienteContract.buildDeleteAllRecreate(), null, null);
            }
            contexto.getContentResolver().bulkInsert(ClienteContract.getContentUri(), vetor);

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
        	json.put("IdCliente",c.getString(ClienteContract.COL_ID_CLIENTE));
			json.put("EnderecoRua",c.getString(ClienteContract.COL_ENDERECO_RUA));
			json.put("EnderecoNumero",c.getString(ClienteContract.COL_ENDERECO_NUMERO));
			json.put("EnderecoComplemento",c.getString(ClienteContract.COL_ENDERECO_COMPLEMENTO));
			json.put("EnderecoCep",c.getString(ClienteContract.COL_ENDERECO_CEP));
			json.put("EnderecoBairro",c.getString(ClienteContract.COL_ENDERECO_BAIRRO));
			json.put("EnderecoCidade",c.getString(ClienteContract.COL_ENDERECO_CIDADE));
			json.put("EnderecoUf",c.getString(ClienteContract.COL_ENDERECO_UF));
			json.put("Observacoes",c.getString(ClienteContract.COL_OBSERVACOES));
			json.put("CodigoListaContato",c.getString(ClienteContract.COL_CODIGO_LISTA_CONTATO));
			json.put("Nome",c.getString(ClienteContract.COL_NOME));
			json.put("Ativo",c.getString(ClienteContract.COL_ATIVO));
			json.put("IdUsuarioS",c.getString(ClienteContract.COL_ID_USUARIO_S));
		
        return json;
    }
     private JSONObject JSonAtributosSinc(Cursor c) throws JSONException {
        JSONObject json = JSonAtributos(c);
        json.put("OperacaoSinc",c.getString(ClienteContract.COL_OPERACAO_SINC));
        return json;
    }
    

    private ContentValues ContentValuesAtributos(JSONObject obj) throws JSONException {
        ContentValues valores = new ContentValues();
		valores.put(ClienteContract.COLUNA_ID_CLIENTE, obj.getString("IdCliente"));
		
		valores.put(ClienteContract.COLUNA_ENDERECO_RUA, obj.getString("EnderecoRua"));
		
		valores.put(ClienteContract.COLUNA_ENDERECO_NUMERO, obj.getString("EnderecoNumero"));
		
		valores.put(ClienteContract.COLUNA_ENDERECO_COMPLEMENTO, obj.getString("EnderecoComplemento"));
		
		valores.put(ClienteContract.COLUNA_ENDERECO_CEP, obj.getString("EnderecoCep"));
		
		valores.put(ClienteContract.COLUNA_ENDERECO_BAIRRO, obj.getString("EnderecoBairro"));
		
		valores.put(ClienteContract.COLUNA_ENDERECO_CIDADE, obj.getString("EnderecoCidade"));
		
		valores.put(ClienteContract.COLUNA_ENDERECO_UF, obj.getString("EnderecoUf"));
		
		valores.put(ClienteContract.COLUNA_OBSERVACOES, obj.getString("Observacoes"));
		
		valores.put(ClienteContract.COLUNA_CODIGO_LISTA_CONTATO, obj.getString("CodigoListaContato"));
		
		valores.put(ClienteContract.COLUNA_NOME, obj.getString("Nome"));
			
		int valCliente = 0;
		if ("True".equals(obj.getString("Ativo"))) {
			valCliente = 1;
		}
		valores.put(ClienteContract.COLUNA_ATIVO, valCliente);
		
		valores.put(ClienteContract.COLUNA_ID_USUARIO_S, obj.getString("IdUsuarioS"));
		
		
		
        return valores;
    }

}


