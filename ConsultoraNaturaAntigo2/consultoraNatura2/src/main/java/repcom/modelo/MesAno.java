package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.MesAnoDerivadaI;
import repcom.modelo.agregado.MesAnoAgregadoI;


public interface MesAno extends DCIObjetoDominio
		, MesAnoDerivadaI, MesAnoAgregadoI, DisplayI<MesAno>{ 
  
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
	public long getIdMesAno();
	public void setIdMesAno(long valor);


	public long getMes();
	public void setMes(long valor);


	public long getAno();
	public void setAno(long valor);


	public String getDescricaoTela();
	public void setDescricaoTela(String valor);


	public Timestamp getDataReferencia();
	public void setDataReferencia(Timestamp valor);
	public String getDataReferenciaDDMMAAAA();	
	public void setDataReferenciaDDMMAAAAComBarra(String valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
}