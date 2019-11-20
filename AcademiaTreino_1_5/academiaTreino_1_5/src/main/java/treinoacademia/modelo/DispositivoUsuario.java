package treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import treinoacademia.modelo.derivada.DispositivoUsuarioDerivadaI;
import treinoacademia.modelo.agregado.DispositivoUsuarioAgregadoI;


public interface DispositivoUsuario extends DCIObjetoDominio
		, DispositivoUsuarioDerivadaI, DispositivoUsuarioAgregadoI, DisplayI<DispositivoUsuario>{ 
  
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
	public long getIdDispositivoUsuario();
	public void setIdDispositivoUsuario(long valor);


	public String getNumeroCelular();
	public void setNumeroCelular(String valor);


	public String getCodigoDispositivo();
	public void setCodigoDispositivo(String valor);


	public String getTipoAcesso();
	public void setTipoAcesso(String valor);


	public String getMelhorPath();
	public void setMelhorPath(String valor);


	public long getIdUsuarioRa();
	public void setIdUsuarioRa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioRaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}