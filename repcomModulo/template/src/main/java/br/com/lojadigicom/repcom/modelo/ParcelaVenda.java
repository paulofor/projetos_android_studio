package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.ParcelaVendaAgregadoI;

public interface ParcelaVenda  extends DCIObjetoDominio
	, ParcelaVendaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdParcelaVenda();
	public void setIdParcelaVenda(long valor);


	public float getValorParcela();
	public void setValorParcela(float valor);
	
	public String getValorParcelaTela();

	public Timestamp getDataVencimento();
	public void setDataVencimento(Timestamp valor);
	public String getDataVencimentoDDMMAAAA();	
	public void setDataVencimentoDDMMAAAAComBarra(String valor);
	public void setDataVencimentoDDMMAAAAComTraco(String valor);


	public long getNumeroParcela();
	public void setNumeroParcela(long valor);


	public boolean getPaga();
	public void setPaga(boolean valor);


	public long getIdVendaPa();
	public void setIdVendaPa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdVendaPaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}