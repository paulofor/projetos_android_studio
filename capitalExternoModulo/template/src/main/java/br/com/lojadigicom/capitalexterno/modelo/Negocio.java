package br.com.lojadigicom.capitalexterno.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.agregado.NegocioAgregadoI;

public interface Negocio  extends DCIObjetoDominio
	, NegocioAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdNegocio();
	public void setIdNegocio(long valor);


	public String getDescricao();
	public void setDescricao(String valor);


	public Timestamp getDataCriacao();
	public void setDataCriacao(Timestamp valor);
	public String getDataCriacaoDDMMAAAA();


	public String getDataCriacaoHHMM();
	public String getDataCriacaoHHMMSS();
	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}