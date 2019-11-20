package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.ClienteAgregadoI;

public interface Cliente  extends DCIObjetoDominio
	, ClienteAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

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


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}