package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.PalavraChavePesquisaAgregadoI;

public interface PalavraChavePesquisa  extends DCIObjetoDominio
	, PalavraChavePesquisaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdPalavraChavePesquisa();
	public void setIdPalavraChavePesquisa(long valor);


	public String getTextoBusca();
	public void setTextoBusca(String valor);


	public Timestamp getData();
	public void setData(Timestamp valor);
	public String getDataDDMMAAAA();	
	public void setDataDDMMAAAAComBarra(String valor);
	public void setDataDDMMAAAAComTraco(String valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public long getIdNaturezaProdutoRa();
	public void setIdNaturezaProdutoRa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	public long getIdNaturezaProdutoRaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}