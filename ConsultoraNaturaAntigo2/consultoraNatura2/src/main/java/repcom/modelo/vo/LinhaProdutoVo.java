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
import repcom.modelo.agregado.LinhaProdutoAgregado;
import repcom.modelo.derivada.impl.LinhaProdutoDerivada;
import repcom.modelo.display.impl.LinhaProdutoDisplay;

public class LinhaProdutoVo implements LinhaProduto , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idLinhaProduto;
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
  	
  
  	public LinhaProdutoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdLinhaProduto\" : \"" + idLinhaProduto + "\" "
		+ ", \"Nome\" : \"" + nome + "\" "
		+ ", \"Url\" : \"" + url + "\" "
		+ ", \"CodigoLineId\" : \"" + codigoLineId + "\" "
		+ ", \"DataInclusao\" : \"" + dataInclusao + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdLinhaProduto",idLinhaProduto);
			json.put("Nome",nome);
			json.put("Url",url);
			json.put("CodigoLineId",codigoLineId);
			json.put("DataInclusao",dataInclusao);
		
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
		JSONArray listaProduto_Possui = JSonListaProduto_Possui();
		if (listaProduto_Possui!=null) {
			json.put("ListaProdutoVo_Possui",listaProduto_Possui);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaProduto_Possui() throws JSONException{
		if (getListaProduto_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (Produto item:this.getListaProduto_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdLinhaProduto=" + getIdLinhaProduto() 
		+  " Nome=" + getNome() 
		+  " Url=" + getUrl() 
		+  " CodigoLineId=" + getCodigoLineId() 
		+  " DataInclusao=" + getDataInclusao() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idLinhaProduto;
 	}
 	public String getNomeClasse() {
 		return "LinhaProduto";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private LinhaProdutoDisplay display = null;
	private LinhaProdutoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new LinhaProdutoDisplay(this);
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
	private LinhaProdutoAgregado agregado = null;
	private LinhaProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new LinhaProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private LinhaProdutoDerivada derivada = null;
	private LinhaProdutoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new LinhaProdutoDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	public long getQtdeProdutos()
	{
		return getDerivada().getQtdeProdutos(); 
	} 
	public void setQtdeProdutos(long value)
	{
		getDerivada().setQtdeProdutos(value); 
	} 
	
	public long getQtdeCategorias()
	{
		return getDerivada().getQtdeCategorias(); 
	} 
	public void setQtdeCategorias(long value)
	{
		getDerivada().setQtdeCategorias(value); 
	} 
	
	
	// ----- FINAL DERIVADA -----------------
	
	
	
 
  
  
  	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public Produto getCorrenteProduto_Possui() {
			return getAgregado().getCorrenteProduto_Possui();
		}
		public void addListaProduto_Possui(Produto item) {
			getAgregado().addListaProduto_Possui(item);
		}
		public List<Produto> getListaProduto_Possui() {
			return getAgregado().getListaProduto_Possui();
		}
		public List<Produto> getListaProduto_PossuiOriginal() {
			return getAgregado().getListaProduto_PossuiOriginal();
		}
		public List<Produto> getListaProduto_Possui(int qtde) {
			return getAgregado().getListaProduto_Possui(qtde);
		}
		public void setListaProduto_Possui(List<Produto> lista) {
			getAgregado().setListaProduto_Possui(lista);
		}
		public void setListaProduto_PossuiByDao(List<Produto> lista) {
			getAgregado().setListaProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaProduto_Possui() {
			getAgregado().criaVaziaListaProduto_Possui();
		}
		
		public boolean existeListaProduto_Possui() {
			return getAgregado().existeListaProduto_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public LinhaProdutoVo(JSONObject json) throws JSONException{
		idLinhaProduto =  ConversorJson.getInteger(json, "IdLinhaProduto");
		nome =  ConversorJson.getString(json, "Nome");
		url =  ConversorJson.getString(json, "Url");
		codigoLineId =  ConversorJson.getInteger(json, "CodigoLineId");
		dataInclusao =  ConversorJson.getTimestampData(json, "DataInclusao");
  	}
  	public String toString() {
  		return "" + nome;
  	}
	private long idLinhaProduto;
	public long getIdLinhaProduto() {
		return idLinhaProduto;
	}
	public void setIdLinhaProduto(long _valor) {
		idLinhaProduto = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}


	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String _valor) {
		url = _valor;
	}


	private long codigoLineId;
	public long getCodigoLineId() {
		return codigoLineId;
	}
	public void setCodigoLineId(long _valor) {
		codigoLineId = _valor;
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