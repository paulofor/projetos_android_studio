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
import repcom.modelo.agregado.PrecoProdutoAgregado;
import repcom.modelo.derivada.impl.PrecoProdutoDerivada;
import repcom.modelo.display.impl.PrecoProdutoDisplay;

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
		+ ", \"ValorPrecoAvista\" : \"" + valorPrecoAvista + "\" "
		+ ", \"DataInclusao\" : \"" + dataInclusao + "\" "
		+ ", \"QuantidadeParcela\" : \"" + quantidadeParcela + "\" "
		+ ", \"ValorParcela\" : \"" + valorParcela + "\" "
		+ ", \"DataExclusao\" : \"" + dataExclusao + "\" "
		+ ", \"IdProdutoPa\" : \"" + idProdutoPa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdPrecoProduto",idPrecoProduto);
			json.put("ValorPrecoAvista",valorPrecoAvista);
			json.put("DataInclusao",dataInclusao);
			json.put("QuantidadeParcela",quantidadeParcela);
			json.put("ValorParcela",valorParcela);
			json.put("DataExclusao",dataExclusao);
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
		+  " ValorPrecoAvista=" + getValorPrecoAvista() 
		+  " DataInclusao=" + getDataInclusao() 
		+  " QuantidadeParcela=" + getQuantidadeParcela() 
		+  " ValorParcela=" + getValorParcela() 
		+  " DataExclusao=" + getDataExclusao() 
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
		valorPrecoAvista =  ConversorJson.getFloat(json, "ValorPrecoAvista");
		dataInclusao =  ConversorJson.getTimestampData(json, "DataInclusao");
		quantidadeParcela =  ConversorJson.getInteger(json, "QuantidadeParcela");
		valorParcela =  ConversorJson.getFloat(json, "ValorParcela");
		dataExclusao =  ConversorJson.getTimestampData(json, "DataExclusao");
		idProdutoPa =  ConversorJson.getInteger(json, "IdProdutoPa");
  	}
  	public String toString() {
  		return "" + valorPrecoAvista;
  	}
	private long idPrecoProduto;
	public long getIdPrecoProduto() {
		return idPrecoProduto;
	}
	public void setIdPrecoProduto(long _valor) {
		idPrecoProduto = _valor;
	}


	private float valorPrecoAvista;
	public float getValorPrecoAvista() {
		return valorPrecoAvista;
	}
	public void setValorPrecoAvista(float _valor) {
		valorPrecoAvista = _valor;
	}
	
	public String getValorPrecoAvistaTela() {
		return String.format("%.2f",valorPrecoAvista).replace(".", ",");
	}

	private Timestamp dataInclusao;
	public Timestamp getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Timestamp _valor) {
		dataInclusao = _valor;
	}


	public String getDataInclusaoDDMMAAAA() {
		if (dataInclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataInclusao);
    }
    public void setDataInclusaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInclusao = new Timestamp(date.getTime());
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


	private float valorParcela;
	public float getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(float _valor) {
		valorParcela = _valor;
	}
	
	public String getValorParcelaTela() {
		return String.format("%.2f",valorParcela).replace(".", ",");
	}

	private Timestamp dataExclusao;
	public Timestamp getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(Timestamp _valor) {
		dataExclusao = _valor;
	}


	public String getDataExclusaoDDMMAAAA() {
		if (dataExclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataExclusao);
    }
    public void setDataExclusaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataExclusao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
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