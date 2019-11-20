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
import repcom.modelo.agregado.ProdutoVendaAgregado;
import repcom.modelo.derivada.impl.ProdutoVendaDerivada;
import repcom.modelo.display.impl.ProdutoVendaDisplay;

public class ProdutoVendaVo implements ProdutoVenda , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idProdutoVenda;
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
  	
  
  	public ProdutoVendaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdProdutoVenda\" : \"" + idProdutoVenda + "\" "
		+ ", \"Quantidade\" : \"" + quantidade + "\" "
		+ ", \"ValorTotal\" : \"" + valorTotal + "\" "
		+ ", \"ValorItem\" : \"" + valorItem + "\" "
		+ ", \"IdProdutoA\" : \"" + idProdutoA + "\" "
		+ ", \"IdVendaA\" : \"" + idVendaA + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdProdutoVenda",idProdutoVenda);
			json.put("Quantidade",quantidade);
			json.put("ValorTotal",valorTotal);
			json.put("ValorItem",valorItem);
			json.put("IdProdutoA",idProdutoA);
			json.put("IdVendaA",idVendaA);
		
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
		 " IdProdutoVenda=" + getIdProdutoVenda() 
		+  " Quantidade=" + getQuantidade() 
		+  " ValorTotal=" + getValorTotal() 
		+  " ValorItem=" + getValorItem() 
		+  " IdProdutoA=" + getIdProdutoA() 
		+  " IdVendaA=" + getIdVendaA() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idProdutoVenda;
 	}
 	public String getNomeClasse() {
 		return "ProdutoVenda";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ProdutoVendaDisplay display = null;
	private ProdutoVendaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ProdutoVendaDisplay(this);
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
	private ProdutoVendaAgregado agregado = null;
	private ProdutoVendaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ProdutoVendaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ProdutoVendaDerivada derivada = null;
	private ProdutoVendaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ProdutoVendaDerivada(this);
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
		
		
		public Venda getVenda_Associada() {
			return getAgregado().getVenda_Associada();
		}
		public void setVenda_Associada(Venda item) {
			getAgregado().setVenda_Associada(item);
		}
		
		public void addListaVenda_Associada(Venda item) {
			getAgregado().addListaVenda_Associada(item);
		}
		public Venda getCorrenteVenda_Associada() {
			return getAgregado().getCorrenteVenda_Associada();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ProdutoVendaVo(JSONObject json) throws JSONException{
		idProdutoVenda =  ConversorJson.getInteger(json, "IdProdutoVenda");
		quantidade =  ConversorJson.getInteger(json, "Quantidade");
		valorTotal =  ConversorJson.getFloat(json, "ValorTotal");
		valorItem =  ConversorJson.getFloat(json, "ValorItem");
		idProdutoA =  ConversorJson.getInteger(json, "IdProdutoA");
		idVendaA =  ConversorJson.getInteger(json, "IdVendaA");
  	}
  	public String toString() {
  		return "" + idProdutoVenda;
  	}
	private long idProdutoVenda;
	public long getIdProdutoVenda() {
		return idProdutoVenda;
	}
	public void setIdProdutoVenda(long _valor) {
		idProdutoVenda = _valor;
	}


	private long quantidade;
	public long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(long _valor) {
		quantidade = _valor;
	}


	private float valorTotal;
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float _valor) {
		valorTotal = _valor;
	}
	
	public String getValorTotalTela() {
		return String.format("%.2f",valorTotal).replace(".", ",");
	}

	private float valorItem;
	public float getValorItem() {
		return valorItem;
	}
	public void setValorItem(float _valor) {
		valorItem = _valor;
	}
	
	public String getValorItemTela() {
		return String.format("%.2f",valorItem).replace(".", ",");
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

	
	private long idVendaA;
	public long getIdVendaA() {
		// relacionamento
		if (idVendaA==0 && this.getVenda_Associada()!=null) 
			return getVenda_Associada().getId();
		else
			return idVendaA;
	}
	public void setIdVendaA(long _valor) {
		idVendaA = _valor;
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
  	
	public long getIdProdutoALazyLoader() {
		return idProdutoA;
	} 
		
	public long getIdVendaALazyLoader() {
		return idVendaA;
	} 
		
}