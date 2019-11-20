package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.LojaNaturezaDerivadaI;
import coletapreco.modelo.agregado.LojaNaturezaAgregadoI;


public interface LojaNatureza extends DCIObjetoDominio
		, LojaNaturezaDerivadaI, LojaNaturezaAgregadoI, DisplayI<LojaNatureza>{ 
  
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
	public long getIdLojaNatureza();
	public void setIdLojaNatureza(long valor);


	public String getUrlInicial();
	public void setUrlInicial(String valor);


	public boolean getParseCategoria();
	public void setParseCategoria(boolean valor);


	public long getIdLojaVirtualRa();
	public void setIdLojaVirtualRa(long valor);


	public long getIdNaturezaProdutoRa();
	public void setIdNaturezaProdutoRa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdLojaVirtualRaLazyLoader(); 
		
	public long getIdNaturezaProdutoRaLazyLoader(); 
		
}