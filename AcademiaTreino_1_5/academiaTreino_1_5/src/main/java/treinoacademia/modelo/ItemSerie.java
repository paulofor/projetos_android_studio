package treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import treinoacademia.modelo.derivada.ItemSerieDerivadaI;
import treinoacademia.modelo.agregado.ItemSerieAgregadoI;


public interface ItemSerie extends DCIObjetoDominio
		, ItemSerieDerivadaI, ItemSerieAgregadoI, DisplayI<ItemSerie>{ 
  
  	/**
 	* @deprecated  Substituir por JSonSimples()
 	*/
	//@Deprecated
  	//public JSONObject JSon() throws JSONException;
  	public JSONObject JSonSimples() throws JSONException;
  	public String debug();
  	public DigicomContexto getContexto();
  	public void setContexto(DigicomContexto ctx);
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


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdExercicioEdLazyLoader(); 
		
	public long getIdSerieTreinoPaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}