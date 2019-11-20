package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.ProdutoDerivadaI;
import coletapreco.modelo.agregado.ProdutoAgregadoI;


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


	public long getIdLojaVirtualLe();
	public void setIdLojaVirtualLe(long valor);


	public long getIdMarcaP();
	public void setIdMarcaP(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdLojaVirtualLeLazyLoader(); 
		
	public long getIdMarcaPLazyLoader(); 
		
}