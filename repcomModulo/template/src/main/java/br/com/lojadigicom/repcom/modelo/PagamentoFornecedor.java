package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.PagamentoFornecedorAgregadoI;

public interface PagamentoFornecedor  extends DCIObjetoDominio
	, PagamentoFornecedorAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdPagamentoFornecedor();
	public void setIdPagamentoFornecedor(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}