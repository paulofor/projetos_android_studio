package br.com.lojadigicom.capitalexterno.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.agregado.CustoMensalAgregadoI;

public interface CustoMensal  extends DCIObjetoDominio
	, CustoMensalAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdCustoMensal();
	public void setIdCustoMensal(long valor);


	public String getDescricao();
	public void setDescricao(String valor);


	public float getValorMedio();
	public void setValorMedio(float valor);
	
	public String getValorMedioTela();

	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}