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
import coletapreco.modelo.agregado.ModeloProdutoAgregado;
import coletapreco.modelo.derivada.impl.ModeloProdutoDerivada;
import coletapreco.modelo.display.impl.ModeloProdutoDisplay;

public class ModeloProdutoVo implements ModeloProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idModeloProduto;
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
  	
  
  	public ModeloProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdModeloProduto\" : \"" + idModeloProduto + "\" "
		+ ", \"NomeModeloProduto\" : \"" + nomeModeloProduto + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdModeloProduto",idModeloProduto);
			json.put("NomeModeloProduto",nomeModeloProduto);
		
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
		JSONArray listaModeloProdutoProduto_ReferenteA = JSonListaModeloProdutoProduto_ReferenteA();
		if (listaModeloProdutoProduto_ReferenteA!=null) {
			json.put("ListaModeloProdutoProdutoVo_ReferenteA",listaModeloProdutoProduto_ReferenteA);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaModeloProdutoProduto_ReferenteA() throws JSONException{
		if (getListaModeloProdutoProduto_ReferenteA()==null) return null;
		JSONArray lista = new JSONArray();
		for (ModeloProdutoProduto item:this.getListaModeloProdutoProduto_ReferenteAOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdModeloProduto=" + getIdModeloProduto() 
		+  " NomeModeloProduto=" + getNomeModeloProduto() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idModeloProduto;
 	}
 	public String getNomeClasse() {
 		return "ModeloProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ModeloProdutoDisplay display = null;
	private ModeloProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ModeloProdutoDisplay(this);
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
	private ModeloProdutoAgregado agregado = null;
	private ModeloProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ModeloProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ModeloProdutoDerivada derivada = null;
	private ModeloProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ModeloProdutoDerivada(this);
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
	
		public ModeloProdutoProduto getCorrenteModeloProdutoProduto_ReferenteA() {
			return getAgregado().getCorrenteModeloProdutoProduto_ReferenteA();
		}
		public void addListaModeloProdutoProduto_ReferenteA(ModeloProdutoProduto item) {
			getAgregado().addListaModeloProdutoProduto_ReferenteA(item);
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA() {
			return getAgregado().getListaModeloProdutoProduto_ReferenteA();
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal() {
			return getAgregado().getListaModeloProdutoProduto_ReferenteAOriginal();
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde) {
			return getAgregado().getListaModeloProdutoProduto_ReferenteA(qtde);
		}
		public void setListaModeloProdutoProduto_ReferenteA(List<ModeloProdutoProduto> lista) {
			getAgregado().setListaModeloProdutoProduto_ReferenteA(lista);
		}
		public void setListaModeloProdutoProduto_ReferenteAByDao(List<ModeloProdutoProduto> lista) {
			getAgregado().setListaModeloProdutoProduto_ReferenteAByDao(lista);
		}
		public void criaVaziaListaModeloProdutoProduto_ReferenteA() {
			getAgregado().criaVaziaListaModeloProdutoProduto_ReferenteA();
		}
		
		public boolean existeListaModeloProdutoProduto_ReferenteA() {
			return getAgregado().existeListaModeloProdutoProduto_ReferenteA();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ModeloProdutoVo(JSONObject json) throws JSONException{
		idModeloProduto =  ConversorJson.getInteger(json, "IdModeloProduto");
		nomeModeloProduto =  ConversorJson.getString(json, "NomeModeloProduto");
  	}
  	public String toString() {
  		return "" + nomeModeloProduto;
  	}
	private long idModeloProduto;
	public long getIdModeloProduto() {
		return idModeloProduto;
	}
	public void setIdModeloProduto(long _valor) {
		idModeloProduto = _valor;
	}


	private String nomeModeloProduto;
	public String getNomeModeloProduto() {
		return nomeModeloProduto;
	}
	public void setNomeModeloProduto(String _valor) {
		nomeModeloProduto = _valor;
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