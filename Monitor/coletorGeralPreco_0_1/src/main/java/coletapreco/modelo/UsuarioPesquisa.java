package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.UsuarioPesquisaDerivadaI;
import coletapreco.modelo.agregado.UsuarioPesquisaAgregadoI;


public interface UsuarioPesquisa extends DCIObjetoDominio
		, UsuarioPesquisaDerivadaI, UsuarioPesquisaAgregadoI, DisplayI<UsuarioPesquisa>{ 
  
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
	public long getIdUsuarioPesquisa();
	public void setIdUsuarioPesquisa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public long getIdNaturezaProdutoP();
	public void setIdNaturezaProdutoP(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	public long getIdNaturezaProdutoPLazyLoader(); 
		
}