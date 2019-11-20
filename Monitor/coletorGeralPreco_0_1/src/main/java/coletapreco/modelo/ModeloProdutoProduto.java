package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.ModeloProdutoProdutoDerivadaI;
import coletapreco.modelo.agregado.ModeloProdutoProdutoAgregadoI;


public interface ModeloProdutoProduto extends DCIObjetoDominio
		, ModeloProdutoProdutoDerivadaI, ModeloProdutoProdutoAgregadoI, DisplayI<ModeloProdutoProduto>{ 
  
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
	public long getIdModeloProdutoProduto();
	public void setIdModeloProdutoProduto(long valor);


	public long getIdModeloProdutoRa();
	public void setIdModeloProdutoRa(long valor);


	public long getIdProdutoRa();
	public void setIdProdutoRa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdModeloProdutoRaLazyLoader(); 
		
	public long getIdProdutoRaLazyLoader(); 
		
}