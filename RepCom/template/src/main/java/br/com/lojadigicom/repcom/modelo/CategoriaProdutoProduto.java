package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.CategoriaProdutoProdutoAgregadoI;

public interface CategoriaProdutoProduto  extends DCIObjetoDominio
	, CategoriaProdutoProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdCategoriaProdutoProduto();
	public void setIdCategoriaProdutoProduto(long valor);


	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();	
	public void setDataInclusaoDDMMAAAAComBarra(String valor);
	public void setDataInclusaoDDMMAAAAComTraco(String valor);


	public long getIdCategoriaProdutoRa();
	public void setIdCategoriaProdutoRa(long valor);


	public long getIdProdutoRa();
	public void setIdProdutoRa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdCategoriaProdutoRaLazyLoader(); 
		
	public long getIdProdutoRaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}