package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.LojaVirtualDerivadaI;
import coletapreco.modelo.agregado.LojaVirtualAgregadoI;


public interface LojaVirtual extends DCIObjetoDominio
		, LojaVirtualDerivadaI, LojaVirtualAgregadoI, DisplayI<LojaVirtual>{ 
  
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
	public long getIdLojaVirtual();
	public void setIdLojaVirtual(long valor);


	public String getNomeLojaVirtual();
	public void setNomeLojaVirtual(String valor);


	public String getUrlInicialBrinquedo();
	public void setUrlInicialBrinquedo(String valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
}