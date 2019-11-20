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
import coletapreco.modelo.agregado.CategoriaLojaProdutoAgregado;
import coletapreco.modelo.derivada.impl.CategoriaLojaProdutoDerivada;
import coletapreco.modelo.display.impl.CategoriaLojaProdutoDisplay;

public class CategoriaLojaProdutoVo implements CategoriaLojaProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idCategoriaLojaProduto;
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
  	
  
  	public CategoriaLojaProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdCategoriaLojaProduto\" : \"" + idCategoriaLojaProduto + "\" "
		+ ", \"DataUltimaVisita\" : \"" + dataUltimaVisita + "\" "
		+ ", \"IdCategoriaLojaRa\" : \"" + idCategoriaLojaRa + "\" "
		+ ", \"IdProdutoRa\" : \"" + idProdutoRa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdCategoriaLojaProduto",idCategoriaLojaProduto);
			json.put("DataUltimaVisita",dataUltimaVisita);
			json.put("IdCategoriaLojaRa",idCategoriaLojaRa);
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
		 " IdCategoriaLojaProduto=" + getIdCategoriaLojaProduto() 
		+  " DataUltimaVisita=" + getDataUltimaVisita() 
		+  " IdCategoriaLojaRa=" + getIdCategoriaLojaRa() 
		+  " IdProdutoRa=" + getIdProdutoRa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idCategoriaLojaProduto;
 	}
 	public String getNomeClasse() {
 		return "CategoriaLojaProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private CategoriaLojaProdutoDisplay display = null;
	private CategoriaLojaProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new CategoriaLojaProdutoDisplay(this);
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
	private CategoriaLojaProdutoAgregado agregado = null;
	private CategoriaLojaProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CategoriaLojaProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private CategoriaLojaProdutoDerivada derivada = null;
	private CategoriaLojaProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new CategoriaLojaProdutoDerivada(this);
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
  	
		public CategoriaLoja getCategoriaLoja_ReferenteA() {
			return getAgregado().getCategoriaLoja_ReferenteA();
		}
		public void setCategoriaLoja_ReferenteA(CategoriaLoja item) {
			getAgregado().setCategoriaLoja_ReferenteA(item);
		}
		
		public void addListaCategoriaLoja_ReferenteA(CategoriaLoja item) {
			getAgregado().addListaCategoriaLoja_ReferenteA(item);
		}
		public CategoriaLoja getCorrenteCategoriaLoja_ReferenteA() {
			return getAgregado().getCorrenteCategoriaLoja_ReferenteA();
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
  
  
  	public CategoriaLojaProdutoVo(JSONObject json) throws JSONException{
		idCategoriaLojaProduto =  ConversorJson.getInteger(json, "IdCategoriaLojaProduto");
		dataUltimaVisita =  ConversorJson.getTimestampData(json, "DataUltimaVisita");
		idCategoriaLojaRa =  ConversorJson.getInteger(json, "IdCategoriaLojaRa");
		idProdutoRa =  ConversorJson.getInteger(json, "IdProdutoRa");
  	}
  	public String toString() {
  		return "" + idCategoriaLojaProduto;
  	}
	private long idCategoriaLojaProduto;
	public long getIdCategoriaLojaProduto() {
		return idCategoriaLojaProduto;
	}
	public void setIdCategoriaLojaProduto(long _valor) {
		idCategoriaLojaProduto = _valor;
	}


	private Timestamp dataUltimaVisita;
	public Timestamp getDataUltimaVisita() {
		return dataUltimaVisita;
	}
	public void setDataUltimaVisita(Timestamp _valor) {
		dataUltimaVisita = _valor;
	}


	public String getDataUltimaVisitaDDMMAAAA() {
		if (dataUltimaVisita==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataUltimaVisita);
    }
    public void setDataUltimaVisitaDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaVisita = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }



	
	private long idCategoriaLojaRa;
	public long getIdCategoriaLojaRa() {
		// relacionamento
		if (idCategoriaLojaRa==0 && this.getCategoriaLoja_ReferenteA()!=null) 
			return getCategoriaLoja_ReferenteA().getId();
		else
			return idCategoriaLojaRa;
	}
	public void setIdCategoriaLojaRa(long _valor) {
		idCategoriaLojaRa = _valor;
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
  	
	public long getIdCategoriaLojaRaLazyLoader() {
		return idCategoriaLojaRa;
	} 
		
	public long getIdProdutoRaLazyLoader() {
		return idProdutoRa;
	} 
		
}