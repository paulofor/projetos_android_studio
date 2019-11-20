package treinoacademia.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import treinoacademia.modelo.derivada.UsuarioDerivadaI;
import treinoacademia.modelo.agregado.UsuarioAgregadoI;


public interface Usuario extends DCIObjetoDominio
		, UsuarioDerivadaI, UsuarioAgregadoI, DisplayI<Usuario>{ 
  
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
	public long getIdUsuario();
	public void setIdUsuario(long valor);


	public String getNomeUsuario();
	public void setNomeUsuario(String valor);


	public String getNumeroCelular();
	public void setNumeroCelular(String valor);


	public String getMelhorPath();
	public void setMelhorPath(String valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}