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
import repcom.modelo.agregado.ProdutoPedidoFornecedorAgregado;
import repcom.modelo.derivada.impl.ProdutoPedidoFornecedorDerivada;
import repcom.modelo.display.impl.ProdutoPedidoFornecedorDisplay;

public class ProdutoPedidoFornecedorVo implements ProdutoPedidoFornecedor , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idProdutoPedidoFornecedor;
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
  	
  
  	public ProdutoPedidoFornecedorVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdProdutoPedidoFornecedor\" : \"" + idProdutoPedidoFornecedor + "\" "
		+ ", \"IdPedidoFornecedorA\" : \"" + idPedidoFornecedorA + "\" "
		+ ", \"IdProdutoA\" : \"" + idProdutoA + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdProdutoPedidoFornecedor",idProdutoPedidoFornecedor);
			json.put("IdPedidoFornecedorA",idPedidoFornecedorA);
			json.put("IdProdutoA",idProdutoA);
		
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
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdProdutoPedidoFornecedor=" + getIdProdutoPedidoFornecedor() 
		+  " IdPedidoFornecedorA=" + getIdPedidoFornecedorA() 
		+  " IdProdutoA=" + getIdProdutoA() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idProdutoPedidoFornecedor;
 	}
 	public String getNomeClasse() {
 		return "ProdutoPedidoFornecedor";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ProdutoPedidoFornecedorDisplay display = null;
	private ProdutoPedidoFornecedorDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ProdutoPedidoFornecedorDisplay(this);
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
	private ProdutoPedidoFornecedorAgregado agregado = null;
	private ProdutoPedidoFornecedorAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ProdutoPedidoFornecedorAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ProdutoPedidoFornecedorDerivada derivada = null;
	private ProdutoPedidoFornecedorDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ProdutoPedidoFornecedorDerivada(this);
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
  	
		public PedidoFornecedor getPedidoFornecedor_Associada() {
			return getAgregado().getPedidoFornecedor_Associada();
		}
		public void setPedidoFornecedor_Associada(PedidoFornecedor item) {
			getAgregado().setPedidoFornecedor_Associada(item);
		}
		
		public void addListaPedidoFornecedor_Associada(PedidoFornecedor item) {
			getAgregado().addListaPedidoFornecedor_Associada(item);
		}
		public PedidoFornecedor getCorrentePedidoFornecedor_Associada() {
			return getAgregado().getCorrentePedidoFornecedor_Associada();
		}
		
		
		public Produto getProduto_Associada() {
			return getAgregado().getProduto_Associada();
		}
		public void setProduto_Associada(Produto item) {
			getAgregado().setProduto_Associada(item);
		}
		
		public void addListaProduto_Associada(Produto item) {
			getAgregado().addListaProduto_Associada(item);
		}
		public Produto getCorrenteProduto_Associada() {
			return getAgregado().getCorrenteProduto_Associada();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ProdutoPedidoFornecedorVo(JSONObject json) throws JSONException{
		idProdutoPedidoFornecedor =  ConversorJson.getInteger(json, "IdProdutoPedidoFornecedor");
		idPedidoFornecedorA =  ConversorJson.getInteger(json, "IdPedidoFornecedorA");
		idProdutoA =  ConversorJson.getInteger(json, "IdProdutoA");
  	}
  	public String toString() {
  		return "" + idProdutoPedidoFornecedor;
  	}
	private long idProdutoPedidoFornecedor;
	public long getIdProdutoPedidoFornecedor() {
		return idProdutoPedidoFornecedor;
	}
	public void setIdProdutoPedidoFornecedor(long _valor) {
		idProdutoPedidoFornecedor = _valor;
	}

	
	private long idPedidoFornecedorA;
	public long getIdPedidoFornecedorA() {
		// relacionamento
		if (idPedidoFornecedorA==0 && this.getPedidoFornecedor_Associada()!=null) 
			return getPedidoFornecedor_Associada().getId();
		else
			return idPedidoFornecedorA;
	}
	public void setIdPedidoFornecedorA(long _valor) {
		idPedidoFornecedorA = _valor;
	}

	
	private long idProdutoA;
	public long getIdProdutoA() {
		// relacionamento
		if (idProdutoA==0 && this.getProduto_Associada()!=null) 
			return getProduto_Associada().getId();
		else
			return idProdutoA;
	}
	public void setIdProdutoA(long _valor) {
		idProdutoA = _valor;
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
  	
	public long getIdPedidoFornecedorALazyLoader() {
		return idPedidoFornecedorA;
	} 
		
	public long getIdProdutoALazyLoader() {
		return idProdutoA;
	} 
		
}