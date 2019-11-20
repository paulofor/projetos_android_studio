package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.NaturezaProdutoAgregadoI;

public interface NaturezaProduto  extends DCIObjetoDominio
	, NaturezaProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	public long getQtdeOportunidadeDia();
	public void setQtdeOportunidadeDia(long valor); 
  	
  	public boolean getAtivo();
	public void setAtivo(boolean valor); 
  	
  	

  	public String toString();
	public long getIdNaturezaProduto();
	public void setIdNaturezaProduto(long valor);


	public String getNomeNaturezaProduto();
	public void setNomeNaturezaProduto(String valor);


	public long getIdAppProdutoAp();
	public void setIdAppProdutoAp(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdAppProdutoApLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}