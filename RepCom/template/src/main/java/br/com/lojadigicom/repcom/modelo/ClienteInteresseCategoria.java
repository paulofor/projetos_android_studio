package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.ClienteInteresseCategoriaAgregadoI;

public interface ClienteInteresseCategoria  extends DCIObjetoDominio
	, ClienteInteresseCategoriaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdClienteInteresseCategoria();
	public void setIdClienteInteresseCategoria(long valor);


	public long getIdClienteA();
	public void setIdClienteA(long valor);


	public long getIdCategoriaProdutoA();
	public void setIdCategoriaProdutoA(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdClienteALazyLoader(); 
		
	public long getIdCategoriaProdutoALazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}