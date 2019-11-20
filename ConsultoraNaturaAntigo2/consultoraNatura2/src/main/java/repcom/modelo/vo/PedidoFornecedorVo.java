package repcom.modelo.vo;

import android.view.View;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.ObjetoSinc;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.activity.DigicomContexto;
import repcom.modelo.*;
import repcom.modelo.agregado.PedidoFornecedorAgregado;
import repcom.modelo.derivada.impl.PedidoFornecedorDerivada;
import repcom.modelo.display.impl.PedidoFornecedorDisplay;

public class PedidoFornecedorVo implements PedidoFornecedor , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idPedidoFornecedor;
    }
  
  
  	private DigicomContexto _contexto = null;
    public DigicomContexto getContexto() {
    	if (_contexto==null) 
    		throw new RuntimeException("DigicomContexto n?o inicializado");
    	return _contexto;
    }
  	public void setContexto(DigicomContexto ctx) {
  		_contexto = ctx;
  	}
  	
  
  	public PedidoFornecedorVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdPedidoFornecedor\" : \"" + idPedidoFornecedor + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdPedidoFornecedor",idPedidoFornecedor);
		
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}

	public JSONObject JSonSimples() throws JSONException{
		return JSonAtributos();
	}

	// apagar em 04-05-2015
	/**
 	* @deprecated  Substituir por JSonSimples()
 	*/
	@Deprecated
	public JSONObject JSon() throws JSONException{
		JSONObject json = JSonAtributos();
		//try {
		JSONArray listaProdutoPedidoFornecedor_Associada = JSonListaProdutoPedidoFornecedor_Associada();
		if (listaProdutoPedidoFornecedor_Associada!=null) {
			json.put("ListaProdutoPedidoFornecedorVo_Associada",listaProdutoPedidoFornecedor_Associada);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaProdutoPedidoFornecedor_Associada() throws JSONException{
		if (getListaProdutoPedidoFornecedor_Associada()==null) return null;
		JSONArray lista = new JSONArray();
		for (ProdutoPedidoFornecedor item:this.getListaProdutoPedidoFornecedor_AssociadaOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdPedidoFornecedor=" + getIdPedidoFornecedor() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idPedidoFornecedor;
 	}
 	public String getNomeClasse() {
 		return "PedidoFornecedor";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private PedidoFornecedorDisplay display = null;
	private PedidoFornecedorDisplay getObjetoDisplay() {
		if (display==null) {
			display = new PedidoFornecedorDisplay(this);
		}
		return display;
	}
	@Override
	public View getItemListaView() {
		return getObjetoDisplay().getItemListaView();
	}
	@Override
	public String getItemListaTexto() {
		return getObjetoDisplay().getItemListaTexto();
	}
	// ----- FINAL DISPLAY -----------------
 
    // ----- INICIO AGREGADO -----------------
	private PedidoFornecedorAgregado agregado = null;
	private PedidoFornecedorAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PedidoFornecedorAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private PedidoFornecedorDerivada derivada = null;
	private PedidoFornecedorDerivada getDerivada() {
		if (derivada==null) {
			derivada = new PedidoFornecedorDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	
	// ----- FINAL DERIVADA -----------------
	
	
	
 
  
  
  	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public ProdutoPedidoFornecedor getCorrenteProdutoPedidoFornecedor_Associada() {
			return getAgregado().getCorrenteProdutoPedidoFornecedor_Associada();
		}
		public void addListaProdutoPedidoFornecedor_Associada(ProdutoPedidoFornecedor item) {
			getAgregado().addListaProdutoPedidoFornecedor_Associada(item);
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada() {
			return getAgregado().getListaProdutoPedidoFornecedor_Associada();
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_AssociadaOriginal() {
			return getAgregado().getListaProdutoPedidoFornecedor_AssociadaOriginal();
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada(int qtde) {
			return getAgregado().getListaProdutoPedidoFornecedor_Associada(qtde);
		}
		public void setListaProdutoPedidoFornecedor_Associada(List<ProdutoPedidoFornecedor> lista) {
			getAgregado().setListaProdutoPedidoFornecedor_Associada(lista);
		}
		public void setListaProdutoPedidoFornecedor_AssociadaByDao(List<ProdutoPedidoFornecedor> lista) {
			getAgregado().setListaProdutoPedidoFornecedor_AssociadaByDao(lista);
		}
		public void criaVaziaListaProdutoPedidoFornecedor_Associada() {
			getAgregado().criaVaziaListaProdutoPedidoFornecedor_Associada();
		}
		
		public boolean existeListaProdutoPedidoFornecedor_Associada() {
			return getAgregado().existeListaProdutoPedidoFornecedor_Associada();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public PedidoFornecedorVo(JSONObject json) throws JSONException{
		idPedidoFornecedor =  ConversorJson.getInteger(json, "IdPedidoFornecedor");
  	}
  	public String toString() {
  		return "" + idPedidoFornecedor;
  	}
	private long idPedidoFornecedor;
	public long getIdPedidoFornecedor() {
		return idPedidoFornecedor;
	}
	public void setIdPedidoFornecedor(long _valor) {
		idPedidoFornecedor = _valor;
	}





	private String operacaoSinc = null;
	@Override
	public String getOperacaoSinc() {
		return operacaoSinc;
	}
	@Override
	public void setOperacaoSinc(String valor) {
		operacaoSinc = valor;
	}
	@Override
	public JSONObject JSonSinc() throws JSONException {
		JSONObject json = JSonSimples();
		json.put("OperacaoSinc", operacaoSinc);
		return json;
	}
	
	
	private boolean salvaPreliminar = false;
	@Override
	public void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	@Override
	public boolean getSalvaPreliminar() {
		return salvaPreliminar;
	}
	
	// ComChaveEstrangeira
  	
}