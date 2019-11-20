package br.com.lojadigicom.precomed.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.modelo.agregado.LojaVirtualAgregadoI;

public interface LojaVirtual  extends DCIObjetoDominio
	, LojaVirtualAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdLojaVirtual();
	public void setIdLojaVirtual(long valor);


	public String getNomeLojaVirtual();
	public void setNomeLojaVirtual(String valor);


	public String getUrlPrincipal();
	public void setUrlPrincipal(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}