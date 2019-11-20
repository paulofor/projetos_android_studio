package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.MesAnoAgregadoI;

public interface MesAno  extends DCIObjetoDominio
	, MesAnoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdMesAno();
	public void setIdMesAno(long valor);


	public long getMes();
	public void setMes(long valor);


	public long getAno();
	public void setAno(long valor);


	public String getDescricaoTela();
	public void setDescricaoTela(String valor);


	public Timestamp getDataReferencia();
	public void setDataReferencia(Timestamp valor);
	public String getDataReferenciaDDMMAAAA();	
	public void setDataReferenciaDDMMAAAAComBarra(String valor);
	public void setDataReferenciaDDMMAAAAComTraco(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}