package br.com.lojadigicom.capitalexterno.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.modelo.agregado.ItemCustoProdutoAgregadoI;

public interface ItemCustoProduto  extends DCIObjetoDominio
	, ItemCustoProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdItemCustoProduto();
	public void setIdItemCustoProduto(long valor);


	public float getValor();
	public void setValor(float valor);
	
	public String getValorTela();

	public String getDescricao();
	public void setDescricao(String valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}