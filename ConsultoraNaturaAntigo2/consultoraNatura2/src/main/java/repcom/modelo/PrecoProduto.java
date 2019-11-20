package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.PrecoProdutoDerivadaI;
import repcom.modelo.agregado.PrecoProdutoAgregadoI;


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


	public float getValorPrecoAvista();
	public void setValorPrecoAvista(float valor);
	
	public String getValorPrecoAvistaTela();

	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();	
	public void setDataInclusaoDDMMAAAAComBarra(String valor);


	public long getQuantidadeParcela();
	public void setQuantidadeParcela(long valor);


	public float getValorParcela();
	public void setValorParcela(float valor);
	
	public String getValorParcelaTela();

	public Timestamp getDataExclusao();
	public void setDataExclusao(Timestamp valor);
	public String getDataExclusaoDDMMAAAA();	
	public void setDataExclusaoDDMMAAAAComBarra(String valor);


	public long getIdProdutoPa();
	public void setIdProdutoPa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoPaLazyLoader(); 
		
}