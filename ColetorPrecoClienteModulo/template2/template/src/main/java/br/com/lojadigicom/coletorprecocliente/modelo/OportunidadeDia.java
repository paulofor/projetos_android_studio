package br.com.lojadigicom.coletorprecocliente.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.OportunidadeDiaAgregadoI;

public interface OportunidadeDia  extends DCIObjetoDominio
	, OportunidadeDiaAgregadoI {

  	//public JSONObject JSonSimples() throws JSONException;
  	//public String debug();
  	
  	
  	
  	

  	public String toString();
	public long getIdOportunidadeDia();
	public void setIdOportunidadeDia(long valor);


	public String getNomeProduto();
	public void setNomeProduto(String valor);


	public String getUrlProduto();
	public void setUrlProduto(String valor);


	public String getUrlImagem();
	public void setUrlImagem(String valor);


	public float getPrecoVendaAtual();
	public void setPrecoVendaAtual(float valor);
	
	public String getPrecoVendaAtualTela();

	public float getPrecoVendaAnterior();
	public void setPrecoVendaAnterior(float valor);
	
	public String getPrecoVendaAnteriorTela();

	public float getPercentualAjusteVenda();
	public void setPercentualAjusteVenda(float valor);
	
	public String getPercentualAjusteVendaTela();

	public long getQuantidadeParcelaAtual();
	public void setQuantidadeParcelaAtual(long valor);


	public float getPrecoParcelaAtual();
	public void setPrecoParcelaAtual(float valor);
	
	public String getPrecoParcelaAtualTela();

	public float getPrecoParcelaAnterior();
	public void setPrecoParcelaAnterior(float valor);
	
	public String getPrecoParcelaAnteriorTela();

	public long getQuantidadeParcelaAnterior();
	public void setQuantidadeParcelaAnterior(long valor);


	public String getNomeLojaVirtual();
	public void setNomeLojaVirtual(String valor);


	public Timestamp getDataUltimaPrecoAnterior();
	public void setDataUltimaPrecoAnterior(Timestamp valor);
	public String getDataUltimaPrecoAnteriorDDMMAAAA();	
	public void setDataUltimaPrecoAnteriorDDMMAAAAComBarra(String valor);
	public void setDataUltimaPrecoAnteriorDDMMAAAAComTraco(String valor);


	public Timestamp getDataInicioPrecoAtual();
	public void setDataInicioPrecoAtual(Timestamp valor);
	public String getDataInicioPrecoAtualDDMMAAAA();	
	public void setDataInicioPrecoAtualDDMMAAAAComBarra(String valor);
	public void setDataInicioPrecoAtualDDMMAAAAComTraco(String valor);


	public float getPrecoMedio();
	public void setPrecoMedio(float valor);
	
	public String getPrecoMedioTela();

	public float getPrecoMinimo();
	public void setPrecoMinimo(float valor);
	
	public String getPrecoMinimoTela();

	public long getIdNaturezaProdutoPa();
	public void setIdNaturezaProdutoPa(long valor);


	
	
	
	// ComChaveEstrangeira
  	
	public long getIdNaturezaProdutoPaLazyLoader(); 
		
	
	public boolean getSomenteMemoria();
	public void setSomenteMemoria(boolean dado);
}