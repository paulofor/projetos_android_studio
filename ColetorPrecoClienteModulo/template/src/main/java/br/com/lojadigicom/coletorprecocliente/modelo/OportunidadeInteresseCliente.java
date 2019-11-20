package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.OportunidadeInteresseClienteAgregadoI;

public interface OportunidadeInteresseCliente  extends DCIObjetoDominio
	, OportunidadeInteresseClienteAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdOportunidadeInteresseCliente();
	public void setIdOportunidadeInteresseCliente(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}