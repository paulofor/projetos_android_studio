package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.PrecoProdutoDerivadaI;
import coletapreco.modelo.agregado.PrecoProdutoAgregadoI;


public interface PrecoProduto extends DCIObjetoDominio
		, PrecoProdutoDerivadaI, PrecoProdutoAgregadoI, DisplayI<PrecoProduto>{ 
  
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
	public long getIdPrecoProduto();
	public void setIdPrecoProduto(long valor);


	public float getPrecoBoleto();
	public void setPrecoBoleto(float valor);
	
	public String getPrecoBoletoTela();

	public Timestamp getDataVisitaInicial();
	public void setDataVisitaInicial(Timestamp valor);
	public String getDataVisitaInicialDDMMAAAA();	
	public void setDataVisitaInicialDDMMAAAAComBarra(String valor);


	public long getQuantidadeParcela();
	public void setQuantidadeParcela(long valor);


	public float getPrecoParcela();
	public void setPrecoParcela(float valor);
	
	public String getPrecoParcelaTela();

	public float getPrecoVenda();
	public void setPrecoVenda(float valor);
	
	public String getPrecoVendaTela();

	public float getPrecoRegular();
	public void setPrecoRegular(float valor);
	
	public String getPrecoRegularTela();

	public Timestamp getDataUltimaVisita();
	public void setDataUltimaVisita(Timestamp valor);
	public String getDataUltimaVisitaDDMMAAAA();	
	public void setDataUltimaVisitaDDMMAAAAComBarra(String valor);


	public float getPercentualAjuste();
	public void setPercentualAjuste(float valor);
	
	public String getPercentualAjusteTela();

	public long getIdProdutoPa();
	public void setIdProdutoPa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoPaLazyLoader(); 
		
}