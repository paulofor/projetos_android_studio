package br.com.lojadigicom.precomed.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.modelo.agregado.ModeloProdutoAgregadoI;

public interface ModeloProduto  extends DCIObjetoDominio
	, ModeloProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdModeloProduto();
	public void setIdModeloProduto(long valor);


	public String getNomeModeloProduto();
	public void setNomeModeloProduto(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}