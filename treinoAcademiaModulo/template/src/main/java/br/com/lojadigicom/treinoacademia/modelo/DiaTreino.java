package br.com.lojadigicom.treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.treinoacademia.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.treinoacademia.modelo.agregado.DiaTreinoAgregadoI;

public interface DiaTreino  extends DCIObjetoDominio
	, DiaTreinoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdDiaTreino();
	public void setIdDiaTreino(long valor);


	public Timestamp getData();
	public void setData(Timestamp valor);
	public String getDataDDMMAAAA();	
	public void setDataDDMMAAAAComBarra(String valor);
	public void setDataDDMMAAAAComTraco(String valor);


	public boolean getConcluido();
	public void setConcluido(boolean valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public long getIdSerieTreinoSd();
	public void setIdSerieTreinoSd(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	public long getIdSerieTreinoSdLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}