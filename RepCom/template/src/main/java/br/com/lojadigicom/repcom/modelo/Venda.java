package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.VendaAgregadoI;

public interface Venda  extends DCIObjetoDominio
	, VendaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdVenda();
	public void setIdVenda(long valor);


	public Timestamp getData();
	public void setData(Timestamp valor);
	public String getDataDDMMAAAA();	
	public void setDataDDMMAAAAComBarra(String valor);
	public void setDataDDMMAAAAComTraco(String valor);


	public float getValorTotal();
	public void setValorTotal(float valor);
	
	public String getValorTotalTela();

	public boolean getVendaFechada();
	public void setVendaFechada(boolean valor);


	public long getIdClienteFp();
	public void setIdClienteFp(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdClienteFpLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}