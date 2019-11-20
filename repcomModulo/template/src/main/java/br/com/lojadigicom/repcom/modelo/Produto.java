package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.ProdutoAgregadoI;

public interface Produto  extends DCIObjetoDominio
	, ProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdProduto();
	public void setIdProduto(long valor);


	public String getNome();
	public void setNome(String valor);


	public String getUrl();
	public void setUrl(String valor);


	public String getImagem();
	public void setImagem(String valor);


	public long getTamanhoImagem();
	public void setTamanhoImagem(long valor);


	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();	
	public void setDataInclusaoDDMMAAAAComBarra(String valor);
	public void setDataInclusaoDDMMAAAAComTraco(String valor);


	public Timestamp getDataExclusao();
	public void setDataExclusao(Timestamp valor);
	public String getDataExclusaoDDMMAAAA();	
	public void setDataExclusaoDDMMAAAAComBarra(String valor);
	public void setDataExclusaoDDMMAAAAComTraco(String valor);


	public long getIdLinhaProdutoEe();
	public void setIdLinhaProdutoEe(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdLinhaProdutoEeLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}