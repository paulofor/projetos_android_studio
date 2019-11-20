package br.com.lojadigicom.precomed.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.modelo.agregado.ProdutoPesquisaAgregadoI;

public interface ProdutoPesquisa  extends DCIObjetoDominio
	, ProdutoPesquisaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdProdutoPesquisa();
	public void setIdProdutoPesquisa(long valor);


	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();


	public String getDataInclusaoHHMM();
	public String getDataInclusaoHHMMSS();
	public boolean getAtivo();
	public void setAtivo(boolean valor);


	public String getNomeProdutoPesquisa();
	public void setNomeProdutoPesquisa(String valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	public long getIdModeloProdutoRa();
	public void setIdModeloProdutoRa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader(); 
		
	public long getIdModeloProdutoRaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}