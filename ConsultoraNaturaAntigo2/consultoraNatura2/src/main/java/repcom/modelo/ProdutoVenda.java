package repcom.modelo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.json.JSONException;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.modelo.DisplayI;
import repcom.modelo.derivada.ProdutoVendaDerivadaI;
import repcom.modelo.agregado.ProdutoVendaAgregadoI;


public interface ProdutoVenda extends DCIObjetoDominio
		, ProdutoVendaDerivadaI, ProdutoVendaAgregadoI, DisplayI<ProdutoVenda>{ 
  
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
	public long getIdProdutoVenda();
	public void setIdProdutoVenda(long valor);


	public long getQuantidade();
	public void setQuantidade(long valor);


	public float getValorTotal();
	public void setValorTotal(float valor);
	
	public String getValorTotalTela();

	public float getValorItem();
	public void setValorItem(float valor);
	
	public String getValorItemTela();

	public long getIdProdutoA();
	public void setIdProdutoA(long valor);


	public long getIdVendaA();
	public void setIdVendaA(long valor);


	public String getTituloTela();
	
	
	// ComChaveEstrangeira
  	
	public long getIdProdutoALazyLoader(); 
		
	public long getIdVendaALazyLoader(); 
		
}