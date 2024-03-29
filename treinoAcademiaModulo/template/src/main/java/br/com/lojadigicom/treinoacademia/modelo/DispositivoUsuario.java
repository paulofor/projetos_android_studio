package br.com.lojadigicom.treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.treinoacademia.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.treinoacademia.modelo.agregado.DispositivoUsuarioAgregadoI;

public interface DispositivoUsuario  extends DCIObjetoDominio
	, DispositivoUsuarioAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdDispositivoUsuario();
	public void setIdDispositivoUsuario(long valor);


	public String getNumeroCelular();
	public void setNumeroCelular(String valor);


	public String getCodigoDispositivo();
	public void setCodigoDispositivo(String valor);


	public String getTipoAcesso();
	public void setTipoAcesso(String valor);


	public String getMelhorPath();
	public void setMelhorPath(String valor);


	public long getIdUsuarioRa();
	public void setIdUsuarioRa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioRaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}