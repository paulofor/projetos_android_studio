package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.ProdutoPedidoFornecedorAgregadoI;

public interface ProdutoPedidoFornecedor  extends DCIObjetoDominio
	, ProdutoPedidoFornecedorAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdProdutoPedidoFornecedor();
	public void setIdProdutoPedidoFornecedor(long valor);


	public long getIdPedidoFornecedorA();
	public void setIdPedidoFornecedorA(long valor);


	public long getIdProdutoA();
	public void setIdProdutoA(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdPedidoFornecedorALazyLoader(); 
		
	public long getIdProdutoALazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}