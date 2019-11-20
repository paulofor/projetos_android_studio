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
import coletapreco.modelo.agregado.PrecoProdutoAgregado;
import coletapreco.modelo.derivada.impl.PrecoProdutoDerivada;
import coletapreco.modelo.display.impl.PrecoProdutoDisplay;

public class PrecoProdutoVo implements PrecoProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idPrecoProduto;
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
  	
  
  	public PrecoProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdPrecoProduto\" : \"" + idPrecoProduto + "\" "
		+ ", \"PrecoBoleto\" : \"" + precoBoleto + "\" "
		+ ", \"DataVisitaInicial\" : \"" + dataVisitaInicial + "\" "
		+ ", \"QuantidadeParcela\" : \"" + quantidadeParcela + "\" "
		+ ", \"PrecoParcela\" : \"" + precoParcela + "\" "
		+ ", \"PrecoVenda\" : \"" + precoVenda + "\" "
		+ ", \"PrecoRegular\" : \"" + precoRegular + "\" "
		+ ", \"DataUltimaVisita\" : \"" + dataUltimaVisita + "\" "
		+ ", \"PercentualAjuste\" : \"" + percentualAjuste + "\" "
		+ ", \"IdProdutoPa\" : \"" + idProdutoPa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdPrecoProduto",idPrecoProduto);
			json.put("PrecoBoleto",precoBoleto);
			json.put("DataVisitaInicial",dataVisitaInicial);
			json.put("QuantidadeParcela",quantidadeParcela);
			json.put("PrecoParcela",precoParcela);
			json.put("PrecoVenda",precoVenda);
			json.put("PrecoRegular",precoRegular);
			json.put("DataUltimaVisita",dataUltimaVisita);
			json.put("PercentualAjuste",percentualAjuste);
			json.put("IdProdutoPa",idProdutoPa);
		
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
		 " IdPrecoProduto=" + getIdPrecoProduto() 
		+  " PrecoBoleto=" + getPrecoBoleto() 
		+  " DataVisitaInicial=" + getDataVisitaInicial() 
		+  " QuantidadeParcela=" + getQuantidadeParcela() 
		+  " PrecoParcela=" + getPrecoParcela() 
		+  " PrecoVenda=" + getPrecoVenda() 
		+  " PrecoRegular=" + getPrecoRegular() 
		+  " DataUltimaVisita=" + getDataUltimaVisita() 
		+  " PercentualAjuste=" + getPercentualAjuste() 
		+  " IdProdutoPa=" + getIdProdutoPa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idPrecoProduto;
 	}
 	public String getNomeClasse() {
 		return "PrecoProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private PrecoProdutoDisplay display = null;
	private PrecoProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new PrecoProdutoDisplay(this);
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
	private PrecoProdutoAgregado agregado = null;
	private PrecoProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PrecoProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private PrecoProdutoDerivada derivada = null;
	private PrecoProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new PrecoProdutoDerivada(this);
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
  	
		public Produto getProduto_PertenceA() {
			return getAgregado().getProduto_PertenceA();
		}
		public void setProduto_PertenceA(Produto item) {
			getAgregado().setProduto_PertenceA(item);
		}
		
		public void addListaProduto_PertenceA(Produto item) {
			getAgregado().addListaProduto_PertenceA(item);
		}
		public Produto getCorrenteProduto_PertenceA() {
			return getAgregado().getCorrenteProduto_PertenceA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public PrecoProdutoVo(JSONObject json) throws JSONException{
		idPrecoProduto =  ConversorJson.getInteger(json, "IdPrecoProduto");
		precoBoleto =  ConversorJson.getFloat(json, "PrecoBoleto");
		dataVisitaInicial =  ConversorJson.getTimestampData(json, "DataVisitaInicial");
		quantidadeParcela =  ConversorJson.getInteger(json, "QuantidadeParcela");
		precoParcela =  ConversorJson.getFloat(json, "PrecoParcela");
		precoVenda =  ConversorJson.getFloat(json, "PrecoVenda");
		precoRegular =  ConversorJson.getFloat(json, "PrecoRegular");
		dataUltimaVisita =  ConversorJson.getTimestampData(json, "DataUltimaVisita");
		percentualAjuste =  ConversorJson.getFloat(json, "PercentualAjuste");
		idProdutoPa =  ConversorJson.getInteger(json, "IdProdutoPa");
  	}
  	public String toString() {
  		return "" + precoRegular;
  	}
	private long idPrecoProduto;
	public long getIdPrecoProduto() {
		return idPrecoProduto;
	}
	public void setIdPrecoProduto(long _valor) {
		idPrecoProduto = _valor;
	}


	private float precoBoleto;
	public float getPrecoBoleto() {
		return precoBoleto;
	}
	public void setPrecoBoleto(float _valor) {
		precoBoleto = _valor;
	}
	
	public String getPrecoBoletoTela() {
		return String.format("%.2f",precoBoleto).replace(".", ",");
	}

	private Timestamp dataVisitaInicial;
	public Timestamp getDataVisitaInicial() {
		return dataVisitaInicial;
	}
	public void setDataVisitaInicial(Timestamp _valor) {
		dataVisitaInicial = _valor;
	}


	public String getDataVisitaInicialDDMMAAAA() {
		if (dataVisitaInicial==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataVisitaInicial);
    }
    public void setDataVisitaInicialDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataVisitaInicial = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private long quantidadeParcela;
	public long getQuantidadeParcela() {
		return quantidadeParcela;
	}
	public void setQuantidadeParcela(long _valor) {
		quantidadeParcela = _valor;
	}


	private float precoParcela;
	public float getPrecoParcela() {
		return precoParcela;
	}
	public void setPrecoParcela(float _valor) {
		precoParcela = _valor;
	}
	
	public String getPrecoParcelaTela() {
		return String.format("%.2f",precoParcela).replace(".", ",");
	}

	private float precoVenda;
	public float getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(float _valor) {
		precoVenda = _valor;
	}
	
	public String getPrecoVendaTela() {
		return String.format("%.2f",precoVenda).replace(".", ",");
	}

	private float precoRegular;
	public float getPrecoRegular() {
		return precoRegular;
	}
	public void setPrecoRegular(float _valor) {
		precoRegular = _valor;
	}
	
	public String getPrecoRegularTela() {
		return String.format("%.2f",precoRegular).replace(".", ",");
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




	private float percentualAjuste;
	public float getPercentualAjuste() {
		return percentualAjuste;
	}
	public void setPercentualAjuste(float _valor) {
		percentualAjuste = _valor;
	}
	
	public String getPercentualAjusteTela() {
		return String.format("%.2f",percentualAjuste).replace(".", ",");
	}
	
	private long idProdutoPa;
	public long getIdProdutoPa() {
		// relacionamento
		if (idProdutoPa==0 && this.getProduto_PertenceA()!=null) 
			return getProduto_PertenceA().getId();
		else
			return idProdutoPa;
	}
	public void setIdProdutoPa(long _valor) {
		idProdutoPa = _valor;
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
  	
	public long getIdProdutoPaLazyLoader() {
		return idProdutoPa;
	} 
		
}