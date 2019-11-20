package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.ProdutoVendaAgregadoI;

public interface ProdutoVenda  extends DCIObjetoDominio
	, ProdutoVendaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdProdutoVenda();
	public void setIdProdutoVenda(long valor);


	public long getQuantidade();
	public void setQuantidade(long valor);


	public float getValorTotal();
	public void setValorTotal(float valor);
	
	public String getValorTotalTela();

	public float getValorItem();
	public void setValorItem(float valor);
	
	public String getValorItemTela();

	public long getIdProdutoA();
	public void setIdProdutoA(long valor);


	public long getIdVendaA();
	public void setIdVendaA(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoALazyLoader(); 
		
	public long getIdVendaALazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}