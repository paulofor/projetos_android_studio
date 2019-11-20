package br.com.lojadigicom.capitalexterno.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.agregado.CenarioAgregadoI;

public interface Cenario  extends DCIObjetoDominio
	, CenarioAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdCenario();
	public void setIdCenario(long valor);


	public String getDescricaoCenario();
	public void setDescricaoCenario(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}