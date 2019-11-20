package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.EstoqueDerivadaI;
import repcom.modelo.agregado.EstoqueAgregadoI;


public interface Estoque extends DCIObjetoDominio
		, EstoqueDerivadaI, EstoqueAgregadoI, DisplayI<Estoque>{ 
  
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
	public long getIdEstoque();
	public void setIdEstoque(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
}