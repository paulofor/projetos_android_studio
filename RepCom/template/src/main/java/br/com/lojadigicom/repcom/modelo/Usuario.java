package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.UsuarioAgregadoI;

public interface Usuario  extends DCIObjetoDominio
	, UsuarioAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdUsuario();
	public void setIdUsuario(long valor);


	public String getNome();
	public void setNome(String valor);


	public String getNumeroCelular();
	public void setNumeroCelular(String valor);


	public String getMelhorPath();
	public void setMelhorPath(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}