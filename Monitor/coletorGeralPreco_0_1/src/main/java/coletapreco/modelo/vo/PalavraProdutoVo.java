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
import coletapreco.modelo.agregado.PalavraProdutoAgregado;
import coletapreco.modelo.derivada.impl.PalavraProdutoDerivada;
import coletapreco.modelo.display.impl.PalavraProdutoDisplay;

public class PalavraProdutoVo implements PalavraProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idPalavraProduto;
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
  	
  
  	public PalavraProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdPalavraProduto\" : \"" + idPalavraProduto + "\" "
		+ ", \"IdPalavraRa\" : \"" + idPalavraRa + "\" "
		+ ", \"IdProdutoRa\" : \"" + idProdutoRa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdPalavraProduto",idPalavraProduto);
			json.put("IdPalavraRa",idPalavraRa);
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
		 " IdPalavraProduto=" + getIdPalavraProduto() 
		+  " IdPalavraRa=" + getIdPalavraRa() 
		+  " IdProdutoRa=" + getIdProdutoRa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idPalavraProduto;
 	}
 	public String getNomeClasse() {
 		return "PalavraProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private PalavraProdutoDisplay display = null;
	private PalavraProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new PalavraProdutoDisplay(this);
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
	private PalavraProdutoAgregado agregado = null;
	private PalavraProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PalavraProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private PalavraProdutoDerivada derivada = null;
	private PalavraProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new PalavraProdutoDerivada(this);
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
  	
		public Palavra getPalavra_RelaciondoA() {
			return getAgregado().getPalavra_RelaciondoA();
		}
		public void setPalavra_RelaciondoA(Palavra item) {
			getAgregado().setPalavra_RelaciondoA(item);
		}
		
		public void addListaPalavra_RelaciondoA(Palavra item) {
			getAgregado().addListaPalavra_RelaciondoA(item);
		}
		public Palavra getCorrentePalavra_RelaciondoA() {
			return getAgregado().getCorrentePalavra_RelaciondoA();
		}
		
		
		public Produto getProduto_RelaciondoA() {
			return getAgregado().getProduto_RelaciondoA();
		}
		public void setProduto_RelaciondoA(Produto item) {
			getAgregado().setProduto_RelaciondoA(item);
		}
		
		public void addListaProduto_RelaciondoA(Produto item) {
			getAgregado().addListaProduto_RelaciondoA(item);
		}
		public Produto getCorrenteProduto_RelaciondoA() {
			return getAgregado().getCorrenteProduto_RelaciondoA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public PalavraProdutoVo(JSONObject json) throws JSONException{
		idPalavraProduto =  ConversorJson.getInteger(json, "IdPalavraProduto");
		idPalavraRa =  ConversorJson.getInteger(json, "IdPalavraRa");
		idProdutoRa =  ConversorJson.getInteger(json, "IdProdutoRa");
  	}
  	public String toString() {
  		return "" + idPalavraProduto;
  	}
	private long idPalavraProduto;
	public long getIdPalavraProduto() {
		return idPalavraProduto;
	}
	public void setIdPalavraProduto(long _valor) {
		idPalavraProduto = _valor;
	}

	
	private long idPalavraRa;
	public long getIdPalavraRa() {
		// relacionamento
		if (idPalavraRa==0 && this.getPalavra_RelaciondoA()!=null) 
			return getPalavra_RelaciondoA().getId();
		else
			return idPalavraRa;
	}
	public void setIdPalavraRa(long _valor) {
		idPalavraRa = _valor;
	}

	
	private long idProdutoRa;
	public long getIdProdutoRa() {
		// relacionamento
		if (idProdutoRa==0 && this.getProduto_RelaciondoA()!=null) 
			return getProduto_RelaciondoA().getId();
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
  	
	public long getIdPalavraRaLazyLoader() {
		return idPalavraRa;
	} 
		
	public long getIdProdutoRaLazyLoader() {
		return idProdutoRa;
	} 
		
}