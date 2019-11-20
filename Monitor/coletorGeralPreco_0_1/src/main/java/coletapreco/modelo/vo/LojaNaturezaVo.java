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
import coletapreco.modelo.agregado.LojaNaturezaAgregado;
import coletapreco.modelo.derivada.impl.LojaNaturezaDerivada;
import coletapreco.modelo.display.impl.LojaNaturezaDisplay;

public class LojaNaturezaVo implements LojaNatureza , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idLojaNatureza;
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
  	
  
  	public LojaNaturezaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdLojaNatureza\" : \"" + idLojaNatureza + "\" "
		+ ", \"UrlInicial\" : \"" + urlInicial + "\" "
		+ ", \"ParseCategoria\" : \"" + parseCategoria + "\" "
		+ ", \"IdLojaVirtualRa\" : \"" + idLojaVirtualRa + "\" "
		+ ", \"IdNaturezaProdutoRa\" : \"" + idNaturezaProdutoRa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdLojaNatureza",idLojaNatureza);
			json.put("UrlInicial",urlInicial);
			json.put("ParseCategoria",parseCategoria);
			json.put("IdLojaVirtualRa",idLojaVirtualRa);
			json.put("IdNaturezaProdutoRa",idNaturezaProdutoRa);
		
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
		 " IdLojaNatureza=" + getIdLojaNatureza() 
		+  " UrlInicial=" + getUrlInicial() 
		+  " ParseCategoria=" + getParseCategoria() 
		+  " IdLojaVirtualRa=" + getIdLojaVirtualRa() 
		+  " IdNaturezaProdutoRa=" + getIdNaturezaProdutoRa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idLojaNatureza;
 	}
 	public String getNomeClasse() {
 		return "LojaNatureza";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private LojaNaturezaDisplay display = null;
	private LojaNaturezaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new LojaNaturezaDisplay(this);
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
	private LojaNaturezaAgregado agregado = null;
	private LojaNaturezaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new LojaNaturezaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private LojaNaturezaDerivada derivada = null;
	private LojaNaturezaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new LojaNaturezaDerivada(this);
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
  	
		public LojaVirtual getLojaVirtual_ReferenteA() {
			return getAgregado().getLojaVirtual_ReferenteA();
		}
		public void setLojaVirtual_ReferenteA(LojaVirtual item) {
			getAgregado().setLojaVirtual_ReferenteA(item);
		}
		
		public void addListaLojaVirtual_ReferenteA(LojaVirtual item) {
			getAgregado().addListaLojaVirtual_ReferenteA(item);
		}
		public LojaVirtual getCorrenteLojaVirtual_ReferenteA() {
			return getAgregado().getCorrenteLojaVirtual_ReferenteA();
		}
		
		
		public NaturezaProduto getNaturezaProduto_ReferenteA() {
			return getAgregado().getNaturezaProduto_ReferenteA();
		}
		public void setNaturezaProduto_ReferenteA(NaturezaProduto item) {
			getAgregado().setNaturezaProduto_ReferenteA(item);
		}
		
		public void addListaNaturezaProduto_ReferenteA(NaturezaProduto item) {
			getAgregado().addListaNaturezaProduto_ReferenteA(item);
		}
		public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA() {
			return getAgregado().getCorrenteNaturezaProduto_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public LojaNaturezaVo(JSONObject json) throws JSONException{
		idLojaNatureza =  ConversorJson.getInteger(json, "IdLojaNatureza");
		urlInicial =  ConversorJson.getString(json, "UrlInicial");
		parseCategoria =  ConversorJson.getLogic(json, "ParseCategoria");
		idLojaVirtualRa =  ConversorJson.getInteger(json, "IdLojaVirtualRa");
		idNaturezaProdutoRa =  ConversorJson.getInteger(json, "IdNaturezaProdutoRa");
  	}
  	public String toString() {
  		return "" + urlInicial;
  	}
	private long idLojaNatureza;
	public long getIdLojaNatureza() {
		return idLojaNatureza;
	}
	public void setIdLojaNatureza(long _valor) {
		idLojaNatureza = _valor;
	}


	private String urlInicial;
	public String getUrlInicial() {
		return urlInicial;
	}
	public void setUrlInicial(String _valor) {
		urlInicial = _valor;
	}


	private boolean parseCategoria;
	public boolean getParseCategoria() {
		return parseCategoria;
	}
	public void setParseCategoria(boolean _valor) {
		parseCategoria = _valor;
	}

	
	private long idLojaVirtualRa;
	public long getIdLojaVirtualRa() {
		// relacionamento
		if (idLojaVirtualRa==0 && this.getLojaVirtual_ReferenteA()!=null) 
			return getLojaVirtual_ReferenteA().getId();
		else
			return idLojaVirtualRa;
	}
	public void setIdLojaVirtualRa(long _valor) {
		idLojaVirtualRa = _valor;
	}

	
	private long idNaturezaProdutoRa;
	public long getIdNaturezaProdutoRa() {
		// relacionamento
		if (idNaturezaProdutoRa==0 && this.getNaturezaProduto_ReferenteA()!=null) 
			return getNaturezaProduto_ReferenteA().getId();
		else
			return idNaturezaProdutoRa;
	}
	public void setIdNaturezaProdutoRa(long _valor) {
		idNaturezaProdutoRa = _valor;
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
  	
	public long getIdLojaVirtualRaLazyLoader() {
		return idLojaVirtualRa;
	} 
		
	public long getIdNaturezaProdutoRaLazyLoader() {
		return idNaturezaProdutoRa;
	} 
		
}