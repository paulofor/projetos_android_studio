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
import coletapreco.modelo.agregado.LojaVirtualAgregado;
import coletapreco.modelo.derivada.impl.LojaVirtualDerivada;
import coletapreco.modelo.display.impl.LojaVirtualDisplay;

public class LojaVirtualVo implements LojaVirtual , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idLojaVirtual;
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
  	
  
  	public LojaVirtualVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdLojaVirtual\" : \"" + idLojaVirtual + "\" "
		+ ", \"NomeLojaVirtual\" : \"" + nomeLojaVirtual + "\" "
		+ ", \"UrlInicialBrinquedo\" : \"" + urlInicialBrinquedo + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdLojaVirtual",idLojaVirtual);
			json.put("NomeLojaVirtual",nomeLojaVirtual);
			json.put("UrlInicialBrinquedo",urlInicialBrinquedo);
		
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
		JSONArray listaCategoriaLoja_Possui = JSonListaCategoriaLoja_Possui();
		if (listaCategoriaLoja_Possui!=null) {
			json.put("ListaCategoriaLojaVo_Possui",listaCategoriaLoja_Possui);
		} 
		JSONArray listaLojaNatureza_Oferece = JSonListaLojaNatureza_Oferece();
		if (listaLojaNatureza_Oferece!=null) {
			json.put("ListaLojaNaturezaVo_Oferece",listaLojaNatureza_Oferece);
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
	
	private JSONArray JSonListaCategoriaLoja_Possui() throws JSONException{
		if (getListaCategoriaLoja_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (CategoriaLoja item:this.getListaCategoriaLoja_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaLojaNatureza_Oferece() throws JSONException{
		if (getListaLojaNatureza_Oferece()==null) return null;
		JSONArray lista = new JSONArray();
		for (LojaNatureza item:this.getListaLojaNatureza_OfereceOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdLojaVirtual=" + getIdLojaVirtual() 
		+  " NomeLojaVirtual=" + getNomeLojaVirtual() 
		+  " UrlInicialBrinquedo=" + getUrlInicialBrinquedo() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idLojaVirtual;
 	}
 	public String getNomeClasse() {
 		return "LojaVirtual";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private LojaVirtualDisplay display = null;
	private LojaVirtualDisplay getObjetoDisplay() {
		if (display==null) {
			display = new LojaVirtualDisplay(this);
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
	private LojaVirtualAgregado agregado = null;
	private LojaVirtualAgregado getAgregado() {
		if (agregado==null) {
			agregado = new LojaVirtualAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private LojaVirtualDerivada derivada = null;
	private LojaVirtualDerivada getDerivada() {
		if (derivada==null) {
			derivada = new LojaVirtualDerivada(this);
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
 		
		public CategoriaLoja getCorrenteCategoriaLoja_Possui() {
			return getAgregado().getCorrenteCategoriaLoja_Possui();
		}
		public void addListaCategoriaLoja_Possui(CategoriaLoja item) {
			getAgregado().addListaCategoriaLoja_Possui(item);
		}
		public List<CategoriaLoja> getListaCategoriaLoja_Possui() {
			return getAgregado().getListaCategoriaLoja_Possui();
		}
		public List<CategoriaLoja> getListaCategoriaLoja_PossuiOriginal() {
			return getAgregado().getListaCategoriaLoja_PossuiOriginal();
		}
		public List<CategoriaLoja> getListaCategoriaLoja_Possui(int qtde) {
			return getAgregado().getListaCategoriaLoja_Possui(qtde);
		}
		public void setListaCategoriaLoja_Possui(List<CategoriaLoja> lista) {
			getAgregado().setListaCategoriaLoja_Possui(lista);
		}
		public void setListaCategoriaLoja_PossuiByDao(List<CategoriaLoja> lista) {
			getAgregado().setListaCategoriaLoja_PossuiByDao(lista);
		}
		public void criaVaziaListaCategoriaLoja_Possui() {
			getAgregado().criaVaziaListaCategoriaLoja_Possui();
		}
		
		public boolean existeListaCategoriaLoja_Possui() {
			return getAgregado().existeListaCategoriaLoja_Possui();
		}
 		
		public LojaNatureza getCorrenteLojaNatureza_Oferece() {
			return getAgregado().getCorrenteLojaNatureza_Oferece();
		}
		public void addListaLojaNatureza_Oferece(LojaNatureza item) {
			getAgregado().addListaLojaNatureza_Oferece(item);
		}
		public List<LojaNatureza> getListaLojaNatureza_Oferece() {
			return getAgregado().getListaLojaNatureza_Oferece();
		}
		public List<LojaNatureza> getListaLojaNatureza_OfereceOriginal() {
			return getAgregado().getListaLojaNatureza_OfereceOriginal();
		}
		public List<LojaNatureza> getListaLojaNatureza_Oferece(int qtde) {
			return getAgregado().getListaLojaNatureza_Oferece(qtde);
		}
		public void setListaLojaNatureza_Oferece(List<LojaNatureza> lista) {
			getAgregado().setListaLojaNatureza_Oferece(lista);
		}
		public void setListaLojaNatureza_OfereceByDao(List<LojaNatureza> lista) {
			getAgregado().setListaLojaNatureza_OfereceByDao(lista);
		}
		public void criaVaziaListaLojaNatureza_Oferece() {
			getAgregado().criaVaziaListaLojaNatureza_Oferece();
		}
		
		public boolean existeListaLojaNatureza_Oferece() {
			return getAgregado().existeListaLojaNatureza_Oferece();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public LojaVirtualVo(JSONObject json) throws JSONException{
		idLojaVirtual =  ConversorJson.getInteger(json, "IdLojaVirtual");
		nomeLojaVirtual =  ConversorJson.getString(json, "NomeLojaVirtual");
		urlInicialBrinquedo =  ConversorJson.getString(json, "UrlInicialBrinquedo");
  	}
  	public String toString() {
  		return "" + nomeLojaVirtual;
  	}
	private long idLojaVirtual;
	public long getIdLojaVirtual() {
		return idLojaVirtual;
	}
	public void setIdLojaVirtual(long _valor) {
		idLojaVirtual = _valor;
	}


	private String nomeLojaVirtual;
	public String getNomeLojaVirtual() {
		return nomeLojaVirtual;
	}
	public void setNomeLojaVirtual(String _valor) {
		nomeLojaVirtual = _valor;
	}


	private String urlInicialBrinquedo;
	public String getUrlInicialBrinquedo() {
		return urlInicialBrinquedo;
	}
	public void setUrlInicialBrinquedo(String _valor) {
		urlInicialBrinquedo = _valor;
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