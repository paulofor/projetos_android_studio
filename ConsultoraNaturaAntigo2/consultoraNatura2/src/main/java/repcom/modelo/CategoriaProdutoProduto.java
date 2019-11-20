package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.CategoriaProdutoProdutoDerivadaI;
import repcom.modelo.agregado.CategoriaProdutoProdutoAgregadoI;


public interface CategoriaProdutoProduto extends DCIObjetoDominio
		, CategoriaProdutoProdutoDerivadaI, CategoriaProdutoProdutoAgregadoI, DisplayI<CategoriaProdutoProduto>{ 
  
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
	public long getIdCategoriaProdutoProduto();
	public void setIdCategoriaProdutoProduto(long valor);


	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();	
	public void setDataInclusaoDDMMAAAAComBarra(String valor);


	public long getIdCategoriaProdutoRa();
	public void setIdCategoriaProdutoRa(long valor);


	public long getIdProdutoRa();
	public void setIdProdutoRa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdCategoriaProdutoRaLazyLoader(); 
		
	public long getIdProdutoRaLazyLoader(); 
		
}