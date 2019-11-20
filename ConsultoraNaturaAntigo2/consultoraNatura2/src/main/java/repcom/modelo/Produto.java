package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.ProdutoDerivadaI;
import repcom.modelo.agregado.ProdutoAgregadoI;


public interface Produto extends DCIObjetoDominio
		, ProdutoDerivadaI, ProdutoAgregadoI, DisplayI<Produto>{ 
  
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
	public long getIdProduto();
	public void setIdProduto(long valor);


	public String getNome();
	public void setNome(String valor);


	public String getUrl();
	public void setUrl(String valor);


	public String getImagem();
	public void setImagem(String valor);


	public long getTamanhoImagem();
	public void setTamanhoImagem(long valor);


	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();	
	public void setDataInclusaoDDMMAAAAComBarra(String valor);


	public Timestamp getDataExclusao();
	public void setDataExclusao(Timestamp valor);
	public String getDataExclusaoDDMMAAAA();	
	public void setDataExclusaoDDMMAAAAComBarra(String valor);


	public long getIdLinhaProdutoEe();
	public void setIdLinhaProdutoEe(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdLinhaProdutoEeLazyLoader(); 
		
}