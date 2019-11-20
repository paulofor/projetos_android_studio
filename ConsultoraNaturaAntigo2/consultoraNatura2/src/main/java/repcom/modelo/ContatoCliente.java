package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.ContatoClienteDerivadaI;
import repcom.modelo.agregado.ContatoClienteAgregadoI;


public interface ContatoCliente extends DCIObjetoDominio
		, ContatoClienteDerivadaI, ContatoClienteAgregadoI, DisplayI<ContatoCliente>{ 
  
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
	public long getIdContatoCliente();
	public void setIdContatoCliente(long valor);


	public Timestamp getDataContato();
	public void setDataContato(Timestamp valor);
	public String getDataContatoDDMMAAAA();	
	public void setDataContatoDDMMAAAAComBarra(String valor);


	public long getIdClienteRa();
	public void setIdClienteRa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdClienteRaLazyLoader(); 
		
}