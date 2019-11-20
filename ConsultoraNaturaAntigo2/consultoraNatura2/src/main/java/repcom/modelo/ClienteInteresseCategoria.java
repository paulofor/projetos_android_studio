package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.ClienteInteresseCategoriaDerivadaI;
import repcom.modelo.agregado.ClienteInteresseCategoriaAgregadoI;


public interface ClienteInteresseCategoria extends DCIObjetoDominio
		, ClienteInteresseCategoriaDerivadaI, ClienteInteresseCategoriaAgregadoI, DisplayI<ClienteInteresseCategoria>{ 
  
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
	public long getIdClienteInteresseCategoria();
	public void setIdClienteInteresseCategoria(long valor);


	public long getIdClienteA();
	public void setIdClienteA(long valor);


	public long getIdCategoriaProdutoA();
	public void setIdCategoriaProdutoA(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdClienteALazyLoader(); 
		
	public long getIdCategoriaProdutoALazyLoader(); 
		
}