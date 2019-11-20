package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.LinhaProdutoAgregadoI;

public interface LinhaProduto  extends DCIObjetoDominio
	, LinhaProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdLinhaProduto();
	public void setIdLinhaProduto(long valor);


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


	
	
	
	// ComChaveEstrangeira
  	
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}