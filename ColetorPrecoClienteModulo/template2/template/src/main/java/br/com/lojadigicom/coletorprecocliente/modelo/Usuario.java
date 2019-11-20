package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.UsuarioAgregadoI;

public interface Usuario  extends DCIObjetoDominio
	, UsuarioAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdUsuario();
	public void setIdUsuario(long valor);


	public String getNomeUsuario();
	public void setNomeUsuario(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}