package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.InteresseProdutoAgregadoI;

public interface InteresseProduto  extends DCIObjetoDominio
	, InteresseProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdInteresseProduto();
	public void setIdInteresseProduto(long valor);


	public long getNota();
	public void setNota(long valor);


	public Timestamp getData();
	public void setData(Timestamp valor);
	public String getDataDDMMAAAA();


	public String getDataHHMM();
	public String getDataHHMMSS();
	public float getValor();
	public void setValor(float valor);
	
	public String getValorTela();

	public long getIdProdutoClienteRa();
	public void setIdProdutoClienteRa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoClienteRaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}