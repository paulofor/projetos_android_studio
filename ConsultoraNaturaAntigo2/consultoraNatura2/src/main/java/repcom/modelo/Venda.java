package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.VendaDerivadaI;
import repcom.modelo.agregado.VendaAgregadoI;


public interface Venda extends DCIObjetoDominio
		, VendaDerivadaI, VendaAgregadoI, DisplayI<Venda>{ 
  
  	/**
 	* @deprecated  Substituir por JSonSimples()
 	*/
	//@Deprecated
  	//public JSONObject JSon() throws JSONException;
  	public JSONObject JSonSimples() throws JSONException;
  	public String debug();
  	public DigicomContexto getContexto();
  	public void setContexto(DigicomContexto ctx);
  	public String toString();
	public long getIdVenda();
	public void setIdVenda(long valor);


	public Timestamp getData();
	public void setData(Timestamp valor);
	public String getDataDDMMAAAA();	
	public void setDataDDMMAAAAComBarra(String valor);


	public float getValorTotal();
	public void setValorTotal(float valor);
	
	public String getValorTotalTela();

	public long getIdClienteFp();
	public void setIdClienteFp(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdClienteFpLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
}