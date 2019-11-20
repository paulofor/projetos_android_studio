package br.com.lojadigicom.repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.modelo.agregado.PrecoProdutoAgregadoI;

public interface PrecoProduto  extends DCIObjetoDominio
	, PrecoProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdPrecoProduto();
	public void setIdPrecoProduto(long valor);


	public float getValorPrecoAvista();
	public void setValorPrecoAvista(float valor);
	
	public String getValorPrecoAvistaTela();

	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();	
	public void setDataInclusaoDDMMAAAAComBarra(String valor);
	public void setDataInclusaoDDMMAAAAComTraco(String valor);


	public long getQuantidadeParcela();
	public void setQuantidadeParcela(long valor);


	public float getValorParcela();
	public void setValorParcela(float valor);
	
	public String getValorParcelaTela();

	public Timestamp getDataExclusao();
	public void setDataExclusao(Timestamp valor);
	public String getDataExclusaoDDMMAAAA();	
	public void setDataExclusaoDDMMAAAAComBarra(String valor);
	public void setDataExclusaoDDMMAAAAComTraco(String valor);


	public long getIdProdutoPa();
	public void setIdProdutoPa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoPaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}