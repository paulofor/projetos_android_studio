package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.PrecoDiarioClienteAgregadoI;

public interface PrecoDiarioCliente  extends DCIObjetoDominio
	, PrecoDiarioClienteAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdPrecoDiarioClientte();
	public void setIdPrecoDiarioClientte(long valor);


	public Timestamp getDataHora();
	public void setDataHora(Timestamp valor);
	public String getDataHoraDDMMAAAA();


	public String getDataHoraHHMM();
	public String getDataHoraHHMMSS();
	public float getPrecoVenda();
	public void setPrecoVenda(float valor);
	
	public String getPrecoVendaTela();

	public long getIdProdutoClientePa();
	public void setIdProdutoClientePa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoClientePaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}