package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.PalavraDerivadaI;
import coletapreco.modelo.agregado.PalavraAgregadoI;


public interface Palavra extends DCIObjetoDominio
		, PalavraDerivadaI, PalavraAgregadoI, DisplayI<Palavra>{ 
  
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
	public long getIdPalavra();
	public void setIdPalavra(long valor);


	public String getDescricao();
	public void setDescricao(String valor);


	public boolean getComum();
	public void setComum(boolean valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
}