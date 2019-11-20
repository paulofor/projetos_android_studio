package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.NaturezaProdutoDerivadaI;
import coletapreco.modelo.agregado.NaturezaProdutoAgregadoI;


public interface NaturezaProduto extends DCIObjetoDominio
		, NaturezaProdutoDerivadaI, NaturezaProdutoAgregadoI, DisplayI<NaturezaProduto>{ 
  
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
	public long getIdNaturezaProduto();
	public void setIdNaturezaProduto(long valor);


	public String getNomeNaturezaProduto();
	public void setNomeNaturezaProduto(String valor);


	public String getCodigoNaturezaProduto();
	public void setCodigoNaturezaProduto(String valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
}