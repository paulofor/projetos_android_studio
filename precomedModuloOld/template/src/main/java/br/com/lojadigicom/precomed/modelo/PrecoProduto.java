package br.com.lojadigicom.precomed.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.modelo.agregado.PrecoProdutoAgregadoI;

public interface PrecoProduto  extends DCIObjetoDominio
	, PrecoProdutoAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdPrecoProduto();
	public void setIdPrecoProduto(long valor);


	public float getPrecoVenda();
	public void setPrecoVenda(float valor);
	
	public String getPrecoVendaTela();

	public Timestamp getDataVisitaInicial();
	public void setDataVisitaInicial(Timestamp valor);
	public String getDataVisitaInicialDDMMAAAA();	
	public void setDataVisitaInicialDDMMAAAAComBarra(String valor);
	public void setDataVisitaInicialDDMMAAAAComTraco(String valor);


	public long getQuantidadeParcela();
	public void setQuantidadeParcela(long valor);


	public float getPrecoParcela();
	public void setPrecoParcela(float valor);
	
	public String getPrecoParcelaTela();

	public float getPrecoBoleto();
	public void setPrecoBoleto(float valor);
	
	public String getPrecoBoletoTela();

	public float getPrecoRegular();
	public void setPrecoRegular(float valor);
	
	public String getPrecoRegularTela();

	public Timestamp getDataUltimaVisita();
	public void setDataUltimaVisita(Timestamp valor);
	public String getDataUltimaVisitaDDMMAAAA();	
	public void setDataUltimaVisitaDDMMAAAAComBarra(String valor);
	public void setDataUltimaVisitaDDMMAAAAComTraco(String valor);


	public float getPercentualAjuste();
	public void setPercentualAjuste(float valor);
	
	public String getPercentualAjusteTela();

	public float getPrecoQuantidadeDesconto();
	public void setPrecoQuantidadeDesconto(float valor);
	
	public String getPrecoQuantidadeDescontoTela();

	public long getQuantidadeDesconto();
	public void setQuantidadeDesconto(long valor);


	public long getIdProdutoPa();
	public void setIdProdutoPa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoPaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}