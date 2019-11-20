package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.OportunidadeDiaDerivadaI;
import coletapreco.modelo.agregado.OportunidadeDiaAgregadoI;


public interface OportunidadeDia extends DCIObjetoDominio
		, OportunidadeDiaDerivadaI, OportunidadeDiaAgregadoI, DisplayI<OportunidadeDia>{ 
  
  	/**
 	* @deprecated  Substituir por JSonSimples()
 	*/
	//@Deprecated
  	//public JSONObject JSon() throws JSONException;
  	public JSONObject JSonSimples() throws JSONException;
  	public String debug();
  	public DigicomContexto getContexto();
  	public void setContexto(DigicomContexto ctx);
  	public String toString();
	public long getIdOportunidadeDia();
	public void setIdOportunidadeDia(long valor);


	public String getUrlProduto();
	public void setUrlProduto(String valor);


	public String getNomeProduto();
	public void setNomeProduto(String valor);


	public Timestamp getDataInicioPrecoAtual();
	public void setDataInicioPrecoAtual(Timestamp valor);
	public String getDataInicioPrecoAtualDDMMAAAA();	
	public void setDataInicioPrecoAtualDDMMAAAAComBarra(String valor);


	public String getNomeMarca();
	public void setNomeMarca(String valor);


	public String getUrlAfiliado();
	public void setUrlAfiliado(String valor);


	public Timestamp getDataUltimaPrecoAnterior();
	public void setDataUltimaPrecoAnterior(Timestamp valor);
	public String getDataUltimaPrecoAnteriorDDMMAAAA();	
	public void setDataUltimaPrecoAnteriorDDMMAAAAComBarra(String valor);


	public String getImagemLocal();
	public void setImagemLocal(String valor);


	public String getUrlImagem();
	public void setUrlImagem(String valor);


	public long getPosicaoProduto();
	public void setPosicaoProduto(long valor);


	public float getPrecoVendaAnterior();
	public void setPrecoVendaAnterior(float valor);
	
	public String getPrecoVendaAnteriorTela();

	public float getPrecoVendaAtual();
	public void setPrecoVendaAtual(float valor);
	
	public String getPrecoVendaAtualTela();

	public float getPrecoBoletoAnterior();
	public void setPrecoBoletoAnterior(float valor);
	
	public String getPrecoBoletoAnteriorTela();

	public float getPrecoBoletoAtual();
	public void setPrecoBoletoAtual(float valor);
	
	public String getPrecoBoletoAtualTela();

	public float getPrecoParcelaAnterior();
	public void setPrecoParcelaAnterior(float valor);
	
	public String getPrecoParcelaAnteriorTela();

	public float getPrecoParcelaAtual();
	public void setPrecoParcelaAtual(float valor);
	
	public String getPrecoParcelaAtualTela();

	public long getQuantidadeParcelaAnterior();
	public void setQuantidadeParcelaAnterior(long valor);


	public long getQuantidadeParcelaAtual();
	public void setQuantidadeParcelaAtual(long valor);


	public float getPercentualAjusteVenda();
	public void setPercentualAjusteVenda(float valor);
	
	public String getPercentualAjusteVendaTela();

	public float getPercentualAjusteBoleto();
	public void setPercentualAjusteBoleto(float valor);
	
	public String getPercentualAjusteBoletoTela();

	public String getNomeLojaVirtual();
	public void setNomeLojaVirtual(String valor);


	public long getIdProdutoRa();
	public void setIdProdutoRa(long valor);


	public long getIdNaturezaProdutoPa();
	public void setIdNaturezaProdutoPa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoRaLazyLoader(); 
		
	public long getIdNaturezaProdutoPaLazyLoader(); 
		
}