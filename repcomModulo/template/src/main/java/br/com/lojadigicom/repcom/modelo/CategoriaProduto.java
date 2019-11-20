package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.CategoriaProdutoAgregadoI;

public interface CategoriaProduto  extends DCIObjetoDominio
	, CategoriaProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdCategoriaProduto();
	public void setIdCategoriaProduto(long valor);


	public String getNome();
	public void setNome(String valor);


	public String getUrl();
	public void setUrl(String valor);


	public long getCodigoLineId();
	public void setCodigoLineId(long valor);


	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();	
	public void setDataInclusaoDDMMAAAAComBarra(String valor);
	public void setDataInclusaoDDMMAAAAComTraco(String valor);


	public long getIdCategoriaProdutoP();
	public void setIdCategoriaProdutoP(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdCategoriaProdutoPLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}