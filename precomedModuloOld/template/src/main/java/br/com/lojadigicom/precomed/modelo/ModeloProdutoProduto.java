package br.com.lojadigicom.precomed.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.modelo.agregado.ModeloProdutoProdutoAgregadoI;

public interface ModeloProdutoProduto  extends DCIObjetoDominio
	, ModeloProdutoProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdModeloProdutoProduto();
	public void setIdModeloProdutoProduto(long valor);


	public long getIdModeloProdutoRa();
	public void setIdModeloProdutoRa(long valor);


	public long getIdProdutoRa();
	public void setIdProdutoRa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdModeloProdutoRaLazyLoader(); 
		
	public long getIdProdutoRaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}