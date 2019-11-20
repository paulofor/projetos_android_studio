package coletapreco.modelo.vo;

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
import coletapreco.modelo.*;
import coletapreco.modelo.agregado.ModeloProdutoProdutoAgregado;
import coletapreco.modelo.derivada.impl.ModeloProdutoProdutoDerivada;
import coletapreco.modelo.display.impl.ModeloProdutoProdutoDisplay;

public class ModeloProdutoProdutoVo implements ModeloProdutoProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idModeloProdutoProduto;
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
  	
  
  	public ModeloProdutoProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdModeloProdutoProduto\" : \"" + idModeloProdutoProduto + "\" "
		+ ", \"IdModeloProdutoRa\" : \"" + idModeloProdutoRa + "\" "
		+ ", \"IdProdutoRa\" : \"" + idProdutoRa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdModeloProdutoProduto",idModeloProdutoProduto);
			json.put("IdModeloProdutoRa",idModeloProdutoRa);
			json.put("IdProdutoRa",idProdutoRa);
		
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
		 " IdModeloProdutoProduto=" + getIdModeloProdutoProduto() 
		+  " IdModeloProdutoRa=" + getIdModeloProdutoRa() 
		+  " IdProdutoRa=" + getIdProdutoRa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idModeloProdutoProduto;
 	}
 	public String getNomeClasse() {
 		return "ModeloProdutoProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ModeloProdutoProdutoDisplay display = null;
	private ModeloProdutoProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ModeloProdutoProdutoDisplay(this);
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
	private ModeloProdutoProdutoAgregado agregado = null;
	private ModeloProdutoProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ModeloProdutoProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ModeloProdutoProdutoDerivada derivada = null;
	private ModeloProdutoProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ModeloProdutoProdutoDerivada(this);
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
  	
		public ModeloProduto getModeloProduto_ReferenteA() {
			return getAgregado().getModeloProduto_ReferenteA();
		}
		public void setModeloProduto_ReferenteA(ModeloProduto item) {
			getAgregado().setModeloProduto_ReferenteA(item);
		}
		
		public void addListaModeloProduto_ReferenteA(ModeloProduto item) {
			getAgregado().addListaModeloProduto_ReferenteA(item);
		}
		public ModeloProduto getCorrenteModeloProduto_ReferenteA() {
			return getAgregado().getCorrenteModeloProduto_ReferenteA();
		}
		
		
		public Produto getProduto_ReferenteA() {
			return getAgregado().getProduto_ReferenteA();
		}
		public void setProduto_ReferenteA(Produto item) {
			getAgregado().setProduto_ReferenteA(item);
		}
		
		public void addListaProduto_ReferenteA(Produto item) {
			getAgregado().addListaProduto_ReferenteA(item);
		}
		public Produto getCorrenteProduto_ReferenteA() {
			return getAgregado().getCorrenteProduto_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ModeloProdutoProdutoVo(JSONObject json) throws JSONException{
		idModeloProdutoProduto =  ConversorJson.getInteger(json, "IdModeloProdutoProduto");
		idModeloProdutoRa =  ConversorJson.getInteger(json, "IdModeloProdutoRa");
		idProdutoRa =  ConversorJson.getInteger(json, "IdProdutoRa");
  	}
  	public String toString() {
  		return "" + idModeloProdutoProduto;
  	}
	private long idModeloProdutoProduto;
	public long getIdModeloProdutoProduto() {
		return idModeloProdutoProduto;
	}
	public void setIdModeloProdutoProduto(long _valor) {
		idModeloProdutoProduto = _valor;
	}

	
	private long idModeloProdutoRa;
	public long getIdModeloProdutoRa() {
		// relacionamento
		if (idModeloProdutoRa==0 && this.getModeloProduto_ReferenteA()!=null) 
			return getModeloProduto_ReferenteA().getId();
		else
			return idModeloProdutoRa;
	}
	public void setIdModeloProdutoRa(long _valor) {
		idModeloProdutoRa = _valor;
	}

	
	private long idProdutoRa;
	public long getIdProdutoRa() {
		// relacionamento
		if (idProdutoRa==0 && this.getProduto_ReferenteA()!=null) 
			return getProduto_ReferenteA().getId();
		else
			return idProdutoRa;
	}
	public void setIdProdutoRa(long _valor) {
		idProdutoRa = _valor;
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
  	
	public long getIdModeloProdutoRaLazyLoader() {
		return idModeloProdutoRa;
	} 
		
	public long getIdProdutoRaLazyLoader() {
		return idProdutoRa;
	} 
		
}