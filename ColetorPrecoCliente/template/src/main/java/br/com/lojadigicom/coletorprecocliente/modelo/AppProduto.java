package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.AppProdutoAgregadoI;

public interface AppProduto  extends DCIObjetoDominio
	, AppProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdAppProduto();
	public void setIdAppProduto(long valor);


	public String getNome();
	public void setNome(String valor);


	public String getUrlInstalacao();
	public void setUrlInstalacao(String valor);


	public boolean getPossuiVitrine();
	public void setPossuiVitrine(boolean valor);


	public boolean getAtivo();
	public void setAtivo(boolean valor);


	public String getStatus();
	public void setStatus(String valor);


	public long getLimitePosicionador();
	public void setLimitePosicionador(long valor);


	public boolean getPossuiPalavraChave();
	public void setPossuiPalavraChave(boolean valor);


	public String getCodigoHash();
	public void setCodigoHash(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}