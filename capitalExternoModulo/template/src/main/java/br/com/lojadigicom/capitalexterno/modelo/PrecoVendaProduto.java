package br.com.lojadigicom.capitalexterno.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.agregado.PrecoVendaProdutoAgregadoI;

public interface PrecoVendaProduto  extends DCIObjetoDominio
	, PrecoVendaProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdPrecoVendaProduto();
	public void setIdPrecoVendaProduto(long valor);


	public float getValor();
	public void setValor(float valor);
	
	public String getValorTela();

	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}