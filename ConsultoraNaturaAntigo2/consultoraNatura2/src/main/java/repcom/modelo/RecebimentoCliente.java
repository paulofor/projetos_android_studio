package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import repcom.modelo.derivada.RecebimentoClienteDerivadaI;
import repcom.modelo.agregado.RecebimentoClienteAgregadoI;


public interface RecebimentoCliente extends DCIObjetoDominio
		, RecebimentoClienteDerivadaI, RecebimentoClienteAgregadoI{ 
  
  	
  	public JSONObject JSon() throws JSONException;
  	public String debug();
  	public DigicomContexto getContexto();
  	public void setContexto(DigicomContexto ctx);
  	public String toString();
	public long getIdRecebimentoCliente();
	public void setIdRecebimentoCliente(long valor);


}