package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.ProdutoPedidoFornecedorDerivadaI;
import repcom.modelo.agregado.ProdutoPedidoFornecedorAgregadoI;


public interface ProdutoPedidoFornecedor extends DCIObjetoDominio
		, ProdutoPedidoFornecedorDerivadaI, ProdutoPedidoFornecedorAgregadoI, DisplayI<ProdutoPedidoFornecedor>{ 
  
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
	public long getIdProdutoPedidoFornecedor();
	public void setIdProdutoPedidoFornecedor(long valor);


	public long getIdPedidoFornecedorA();
	public void setIdPedidoFornecedorA(long valor);


	public long getIdProdutoA();
	public void setIdProdutoA(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdPedidoFornecedorALazyLoader(); 
		
	public long getIdProdutoALazyLoader(); 
		
}