package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.UsuarioPesquisaAgregadoI;

public interface UsuarioPesquisa  extends DCIObjetoDominio
	, UsuarioPesquisaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdUsuarioPesquisa();
	public void setIdUsuarioPesquisa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public long getIdNaturezaProdutoP();
	public void setIdNaturezaProdutoP(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	public long getIdNaturezaProdutoPLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}