package br.com.lojadigicom.precomed.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.modelo.agregado.ProdutoAgregadoI;

public interface Produto  extends DCIObjetoDominio
	, ProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdProduto();
	public void setIdProduto(long valor);


	public String getUrlOrigem();
	public void setUrlOrigem(String valor);


	public String getImagemLocal();
	public void setImagemLocal(String valor);


	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();


	public String getDataInclusaoHHMM();
	public String getDataInclusaoHHMMSS();
	public String getUrl();
	public void setUrl(String valor);


	public String getNome();
	public void setNome(String valor);


	public long getPosicaoProduto();
	public void setPosicaoProduto(long valor);


	public String getImagem();
	public void setImagem(String valor);


	public String getCodigoMs();
	public void setCodigoMs(String valor);


	public String getPrincipioAtivo();
	public void setPrincipioAtivo(String valor);


	public boolean getPossuiEstoque();
	public void setPossuiEstoque(boolean valor);


	public long getIdLojaVirtualLe();
	public void setIdLojaVirtualLe(long valor);


	public long getIdMarcaP();
	public void setIdMarcaP(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdLojaVirtualLeLazyLoader(); 
		
	public long getIdMarcaPLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}