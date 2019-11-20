package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.CategoriaProdutoDerivadaI;
import repcom.modelo.agregado.CategoriaProdutoAgregadoI;


public interface CategoriaProduto extends DCIObjetoDominio
		, CategoriaProdutoDerivadaI, CategoriaProdutoAgregadoI, DisplayI<CategoriaProduto>{ 
  
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
	public long getIdCategoriaProduto();
	public void setIdCategoriaProduto(long valor);


	public String getNome();
	public void setNome(String valor);


	public String getUrl();
	public void setUrl(String valor);


	public long getCodigoLineId();
	public void setCodigoLineId(long valor);


	public Timestamp getDataInclusao();
	public void setDataInclusao(Timestamp valor);
	public String getDataInclusaoDDMMAAAA();	
	public void setDataInclusaoDDMMAAAAComBarra(String valor);


	public long getIdCategoriaProdutoP();
	public void setIdCategoriaProdutoP(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdCategoriaProdutoPLazyLoader(); 
		
}