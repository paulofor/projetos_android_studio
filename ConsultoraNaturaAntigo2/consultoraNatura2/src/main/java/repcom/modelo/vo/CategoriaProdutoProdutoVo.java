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
import repcom.modelo.agregado.CategoriaProdutoProdutoAgregado;
import repcom.modelo.derivada.impl.CategoriaProdutoProdutoDerivada;
import repcom.modelo.display.impl.CategoriaProdutoProdutoDisplay;

public class CategoriaProdutoProdutoVo implements CategoriaProdutoProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idCategoriaProdutoProduto;
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
  	
  
  	public CategoriaProdutoProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdCategoriaProdutoProduto\" : \"" + idCategoriaProdutoProduto + "\" "
		+ ", \"DataInclusao\" : \"" + dataInclusao + "\" "
		+ ", \"IdCategoriaProdutoRa\" : \"" + idCategoriaProdutoRa + "\" "
		+ ", \"IdProdutoRa\" : \"" + idProdutoRa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdCategoriaProdutoProduto",idCategoriaProdutoProduto);
			json.put("DataInclusao",dataInclusao);
			json.put("IdCategoriaProdutoRa",idCategoriaProdutoRa);
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
		 " IdCategoriaProdutoProduto=" + getIdCategoriaProdutoProduto() 
		+  " DataInclusao=" + getDataInclusao() 
		+  " IdCategoriaProdutoRa=" + getIdCategoriaProdutoRa() 
		+  " IdProdutoRa=" + getIdProdutoRa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idCategoriaProdutoProduto;
 	}
 	public String getNomeClasse() {
 		return "CategoriaProdutoProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private CategoriaProdutoProdutoDisplay display = null;
	private CategoriaProdutoProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new CategoriaProdutoProdutoDisplay(this);
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
	private CategoriaProdutoProdutoAgregado agregado = null;
	private CategoriaProdutoProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CategoriaProdutoProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private CategoriaProdutoProdutoDerivada derivada = null;
	private CategoriaProdutoProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new CategoriaProdutoProdutoDerivada(this);
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
  	
		public CategoriaProduto getCategoriaProduto_ReferenteA() {
			return getAgregado().getCategoriaProduto_ReferenteA();
		}
		public void setCategoriaProduto_ReferenteA(CategoriaProduto item) {
			getAgregado().setCategoriaProduto_ReferenteA(item);
		}
		
		public void addListaCategoriaProduto_ReferenteA(CategoriaProduto item) {
			getAgregado().addListaCategoriaProduto_ReferenteA(item);
		}
		public CategoriaProduto getCorrenteCategoriaProduto_ReferenteA() {
			return getAgregado().getCorrenteCategoriaProduto_ReferenteA();
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
  
  
  	public CategoriaProdutoProdutoVo(JSONObject json) throws JSONException{
		idCategoriaProdutoProduto =  ConversorJson.getInteger(json, "IdCategoriaProdutoProduto");
		dataInclusao =  ConversorJson.getTimestampData(json, "DataInclusao");
		idCategoriaProdutoRa =  ConversorJson.getInteger(json, "IdCategoriaProdutoRa");
		idProdutoRa =  ConversorJson.getInteger(json, "IdProdutoRa");
  	}
  	public String toString() {
  		return "" + idCategoriaProdutoProduto;
  	}
	private long idCategoriaProdutoProduto;
	public long getIdCategoriaProdutoProduto() {
		return idCategoriaProdutoProduto;
	}
	public void setIdCategoriaProdutoProduto(long _valor) {
		idCategoriaProdutoProduto = _valor;
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



	
	private long idCategoriaProdutoRa;
	public long getIdCategoriaProdutoRa() {
		// relacionamento
		if (idCategoriaProdutoRa==0 && this.getCategoriaProduto_ReferenteA()!=null) 
			return getCategoriaProduto_ReferenteA().getId();
		else
			return idCategoriaProdutoRa;
	}
	public void setIdCategoriaProdutoRa(long _valor) {
		idCategoriaProdutoRa = _valor;
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
  	
	public long getIdCategoriaProdutoRaLazyLoader() {
		return idCategoriaProdutoRa;
	} 
		
	public long getIdProdutoRaLazyLoader() {
		return idProdutoRa;
	} 
		
}