package br.com.lojadigicom.capitalexterno.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.agregado.ProdutoAgregadoI;

public interface Produto  extends DCIObjetoDominio
	, ProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdProduto();
	public void setIdProduto(long valor);


	public String getNome();
	public void setNome(String valor);


	public long getIdLinhaProdutoPa();
	public void setIdLinhaProdutoPa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdLinhaProdutoPaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}