package coletapreco.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import coletapreco.modelo.derivada.CategoriaLojaProdutoDerivadaI;
import coletapreco.modelo.agregado.CategoriaLojaProdutoAgregadoI;


public interface CategoriaLojaProduto extends DCIObjetoDominio
		, CategoriaLojaProdutoDerivadaI, CategoriaLojaProdutoAgregadoI, DisplayI<CategoriaLojaProduto>{ 
  
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
	public long getIdCategoriaLojaProduto();
	public void setIdCategoriaLojaProduto(long valor);


	public Timestamp getDataUltimaVisita();
	public void setDataUltimaVisita(Timestamp valor);
	public String getDataUltimaVisitaDDMMAAAA();	
	public void setDataUltimaVisitaDDMMAAAAComBarra(String valor);


	public long getIdCategoriaLojaRa();
	public void setIdCategoriaLojaRa(long valor);


	public long getIdProdutoRa();
	public void setIdProdutoRa(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdCategoriaLojaRaLazyLoader(); 
		
	public long getIdProdutoRaLazyLoader(); 
		
}