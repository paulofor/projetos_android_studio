package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.MarcaDerivadaI;
import coletapreco.modelo.agregado.MarcaAgregadoI;


public interface Marca extends DCIObjetoDominio
		, MarcaDerivadaI, MarcaAgregadoI, DisplayI<Marca>{ 
  
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
	public long getIdMarca();
	public void setIdMarca(long valor);


	public String getNomeMarca();
	public void setNomeMarca(String valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
}