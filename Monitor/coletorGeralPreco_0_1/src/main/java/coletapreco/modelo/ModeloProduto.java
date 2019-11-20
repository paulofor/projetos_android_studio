package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.ModeloProdutoDerivadaI;
import coletapreco.modelo.agregado.ModeloProdutoAgregadoI;


public interface ModeloProduto extends DCIObjetoDominio
		, ModeloProdutoDerivadaI, ModeloProdutoAgregadoI, DisplayI<ModeloProduto>{ 
  
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
	public long getIdModeloProduto();
	public void setIdModeloProduto(long valor);


	public String getNomeModeloProduto();
	public void setNomeModeloProduto(String valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
}