package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.ClienteDerivadaI;
import repcom.modelo.agregado.ClienteAgregadoI;


public interface Cliente extends DCIObjetoDominio
		, ClienteDerivadaI, ClienteAgregadoI, DisplayI<Cliente>{ 
  
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
	public long getIdCliente();
	public void setIdCliente(long valor);


	public String getEnderecoRua();
	public void setEnderecoRua(String valor);


	public String getEnderecoNumero();
	public void setEnderecoNumero(String valor);


	public String getEnderecoComplemento();
	public void setEnderecoComplemento(String valor);


	public String getEnderecoCep();
	public void setEnderecoCep(String valor);


	public String getEnderecoBairro();
	public void setEnderecoBairro(String valor);


	public String getEnderecoCidade();
	public void setEnderecoCidade(String valor);


	public String getEnderecoUf();
	public void setEnderecoUf(String valor);


	public String getObservacoes();
	public void setObservacoes(String valor);


	public String getCodigoListaContato();
	public void setCodigoListaContato(String valor);


	public String getNome();
	public void setNome(String valor);


	public boolean getAtivo();
	public void setAtivo(boolean valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
}