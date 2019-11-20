package br.com.lojadigicom.treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.treinoacademia.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.treinoacademia.modelo.agregado.CargaPlanejadaAgregadoI;

public interface CargaPlanejada  extends DCIObjetoDominio
	, CargaPlanejadaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdCargaPlanejada();
	public void setIdCargaPlanejada(long valor);


	public float getValorCarga();
	public void setValorCarga(float valor);
	
	public String getValorCargaTela();

	public Timestamp getDataInicio();
	public void setDataInicio(Timestamp valor);
	public String getDataInicioDDMMAAAA();	
	public void setDataInicioDDMMAAAAComBarra(String valor);
	public void setDataInicioDDMMAAAAComTraco(String valor);


	public Timestamp getDataFinal();
	public void setDataFinal(Timestamp valor);
	public String getDataFinalDDMMAAAA();	
	public void setDataFinalDDMMAAAAComBarra(String valor);
	public void setDataFinalDDMMAAAAComTraco(String valor);


	public boolean getAtiva();
	public void setAtiva(boolean valor);


	public long getQuantidadeRepeticao();
	public void setQuantidadeRepeticao(long valor);


	public long getOrdemRepeticao();
	public void setOrdemRepeticao(long valor);


	public long getIdItemSerieRa();
	public void setIdItemSerieRa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdItemSerieRaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}