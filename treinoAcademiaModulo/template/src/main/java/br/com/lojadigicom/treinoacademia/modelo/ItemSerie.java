package br.com.lojadigicom.treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.treinoacademia.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.treinoacademia.modelo.agregado.ItemSerieAgregadoI;

public interface ItemSerie  extends DCIObjetoDominio
	, ItemSerieAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdItemSerie();
	public void setIdItemSerie(long valor);


	public long getRepeticoes();
	public void setRepeticoes(long valor);


	public long getSerie();
	public void setSerie(long valor);


	public String getParametros();
	public void setParametros(String valor);


	public long getOrdemExecucao();
	public void setOrdemExecucao(long valor);


	public long getIdExercicioEd();
	public void setIdExercicioEd(long valor);


	public long getIdSerieTreinoPa();
	public void setIdSerieTreinoPa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdExercicioEdLazyLoader(); 
		
	public long getIdSerieTreinoPaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}