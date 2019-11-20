package br.com.lojadigicom.treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.treinoacademia.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.treinoacademia.modelo.agregado.SerieTreinoAgregadoI;

public interface SerieTreino  extends DCIObjetoDominio
	, SerieTreinoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdSerieTreino();
	public void setIdSerieTreino(long valor);


	public long getQtdeExecucao();
	public void setQtdeExecucao(long valor);


	public boolean getAtiva();
	public void setAtiva(boolean valor);


	public Timestamp getDataCriacao();
	public void setDataCriacao(Timestamp valor);
	public String getDataCriacaoDDMMAAAA();	
	public void setDataCriacaoDDMMAAAAComBarra(String valor);
	public void setDataCriacaoDDMMAAAAComTraco(String valor);


	public Timestamp getDataUltimaExecucao();
	public void setDataUltimaExecucao(Timestamp valor);
	public String getDataUltimaExecucaoDDMMAAAA();	
	public void setDataUltimaExecucaoDDMMAAAAComBarra(String valor);
	public void setDataUltimaExecucaoDDMMAAAAComTraco(String valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}