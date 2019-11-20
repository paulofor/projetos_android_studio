package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.derivada.UsuarioNaturezaDerivadaI;
import coletapreco.modelo.agregado.UsuarioNaturezaAgregadoI;


public interface UsuarioNatureza extends DCIObjetoDominio
		, UsuarioNaturezaDerivadaI, UsuarioNaturezaAgregadoI{ 
  
  	
  	public JSONObject JSon() throws JSONException;
  	public String debug();
  	public DigicomContexto getContexto();
  	public void setContexto(DigicomContexto ctx);
  	public String toString();
	public long getIdUsuarioNatureza();
	public void setIdUsuarioNatureza(long valor);


	public long getIdUsuarioPp();
	public void setIdUsuarioPp(long valor);


	public long getIdNaturezaProdutoP();
	public void setIdNaturezaProdutoP(long valor);


	public String getTituloTela();
}