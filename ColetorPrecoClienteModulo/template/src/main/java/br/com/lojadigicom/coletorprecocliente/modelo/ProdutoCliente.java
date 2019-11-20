package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.ProdutoClienteAgregadoI;

public interface ProdutoCliente  extends DCIObjetoDominio
	, ProdutoClienteAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdProdutoCliente();
	public void setIdProdutoCliente(long valor);


	public String getNome();
	public void setNome(String valor);


	public long getPosicaoProduto();
	public void setPosicaoProduto(long valor);


	public String getImagem();
	public void setImagem(String valor);


	public float getPrecoAtual();
	public void setPrecoAtual(float valor);
	
	public String getPrecoAtualTela();

	public String getMarca();
	public void setMarca(String valor);


	public String getLoja();
	public void setLoja(String valor);


	public Timestamp getData();
	public void setData(Timestamp valor);
	public String getDataDDMMAAAA();	
	public void setDataDDMMAAAAComBarra(String valor);
	public void setDataDDMMAAAAComTraco(String valor);


	public String getUrl();
	public void setUrl(String valor);


	public String getDetalhe();
	public void setDetalhe(String valor);


	public long getIdNaturezaProdutoRa();
	public void setIdNaturezaProdutoRa(long valor);


	public long getIdUsuarioS();
	public void setIdUsuarioS(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdNaturezaProdutoRaLazyLoader(); 
		
	public long getIdUsuarioSLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}