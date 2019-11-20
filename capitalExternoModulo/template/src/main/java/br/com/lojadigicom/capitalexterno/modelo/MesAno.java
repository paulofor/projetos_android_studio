package br.com.lojadigicom.capitalexterno.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.agregado.MesAnoAgregadoI;

public interface MesAno  extends DCIObjetoDominio
	, MesAnoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdMesAno();
	public void setIdMesAno(long valor);


	public Timestamp getDataMesAno();
	public void setDataMesAno(Timestamp valor);
	public String getDataMesAnoDDMMAAAA();	
	public void setDataMesAnoDDMMAAAAComBarra(String valor);
	public void setDataMesAnoDDMMAAAAComTraco(String valor);


	public String getDescricao();
	public void setDescricao(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}