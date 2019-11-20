package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.ContatoClienteAgregadoI;

public interface ContatoCliente  extends DCIObjetoDominio
	, ContatoClienteAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdContatoCliente();
	public void setIdContatoCliente(long valor);


	public Timestamp getDataContato();
	public void setDataContato(Timestamp valor);
	public String getDataContatoDDMMAAAA();	
	public void setDataContatoDDMMAAAAComBarra(String valor);
	public void setDataContatoDDMMAAAAComTraco(String valor);


	public long getIdClienteRa();
	public void setIdClienteRa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdClienteRaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}