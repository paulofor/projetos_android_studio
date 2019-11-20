package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.CompartilhamentoProdutoAgregadoI;

public interface CompartilhamentoProduto  extends DCIObjetoDominio
	, CompartilhamentoProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdCompartilhamentoProduto();
	public void setIdCompartilhamentoProduto(long valor);


	public long getIdProdutoRa();
	public void setIdProdutoRa(long valor);


	public Timestamp getDataHora();
	public void setDataHora(Timestamp valor);
	public String getDataHoraDDMMAAAA();


	public String getDataHoraHHMM();
	public String getDataHoraHHMMSS();
	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}