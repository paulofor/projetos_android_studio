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
import repcom.modelo.agregado.PagamentoFornecedorAgregado;
import repcom.modelo.derivada.impl.PagamentoFornecedorDerivada;
import repcom.modelo.display.impl.PagamentoFornecedorDisplay;

public class PagamentoFornecedorVo implements PagamentoFornecedor , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idPagamentoFornecedor;
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
  	
  
  	public PagamentoFornecedorVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdPagamentoFornecedor\" : \"" + idPagamentoFornecedor + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdPagamentoFornecedor",idPagamentoFornecedor);
		
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
		 " IdPagamentoFornecedor=" + getIdPagamentoFornecedor() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idPagamentoFornecedor;
 	}
 	public String getNomeClasse() {
 		return "PagamentoFornecedor";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private PagamentoFornecedorDisplay display = null;
	private PagamentoFornecedorDisplay getObjetoDisplay() {
		if (display==null) {
			display = new PagamentoFornecedorDisplay(this);
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
	private PagamentoFornecedorAgregado agregado = null;
	private PagamentoFornecedorAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PagamentoFornecedorAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private PagamentoFornecedorDerivada derivada = null;
	private PagamentoFornecedorDerivada getDerivada() {
		if (derivada==null) {
			derivada = new PagamentoFornecedorDerivada(this);
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
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public PagamentoFornecedorVo(JSONObject json) throws JSONException{
		idPagamentoFornecedor =  ConversorJson.getInteger(json, "IdPagamentoFornecedor");
  	}
  	public String toString() {
  		return "" + idPagamentoFornecedor;
  	}
	private long idPagamentoFornecedor;
	public long getIdPagamentoFornecedor() {
		return idPagamentoFornecedor;
	}
	public void setIdPagamentoFornecedor(long _valor) {
		idPagamentoFornecedor = _valor;
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