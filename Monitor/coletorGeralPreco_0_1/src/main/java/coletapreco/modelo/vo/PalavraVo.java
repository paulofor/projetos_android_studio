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
import coletapreco.modelo.agregado.PalavraAgregado;
import coletapreco.modelo.derivada.impl.PalavraDerivada;
import coletapreco.modelo.display.impl.PalavraDisplay;

public class PalavraVo implements Palavra , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idPalavra;
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
  	
  
  	public PalavraVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdPalavra\" : \"" + idPalavra + "\" "
		+ ", \"Descricao\" : \"" + descricao + "\" "
		+ ", \"Comum\" : \"" + comum + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdPalavra",idPalavra);
			json.put("Descricao",descricao);
			json.put("Comum",comum);
		
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
		JSONArray listaPalavraProduto_Possui = JSonListaPalavraProduto_Possui();
		if (listaPalavraProduto_Possui!=null) {
			json.put("ListaPalavraProdutoVo_Possui",listaPalavraProduto_Possui);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaPalavraProduto_Possui() throws JSONException{
		if (getListaPalavraProduto_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (PalavraProduto item:this.getListaPalavraProduto_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdPalavra=" + getIdPalavra() 
		+  " Descricao=" + getDescricao() 
		+  " Comum=" + getComum() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idPalavra;
 	}
 	public String getNomeClasse() {
 		return "Palavra";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private PalavraDisplay display = null;
	private PalavraDisplay getObjetoDisplay() {
		if (display==null) {
			display = new PalavraDisplay(this);
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
	private PalavraAgregado agregado = null;
	private PalavraAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PalavraAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private PalavraDerivada derivada = null;
	private PalavraDerivada getDerivada() {
		if (derivada==null) {
			derivada = new PalavraDerivada(this);
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
	
		public PalavraProduto getCorrentePalavraProduto_Possui() {
			return getAgregado().getCorrentePalavraProduto_Possui();
		}
		public void addListaPalavraProduto_Possui(PalavraProduto item) {
			getAgregado().addListaPalavraProduto_Possui(item);
		}
		public List<PalavraProduto> getListaPalavraProduto_Possui() {
			return getAgregado().getListaPalavraProduto_Possui();
		}
		public List<PalavraProduto> getListaPalavraProduto_PossuiOriginal() {
			return getAgregado().getListaPalavraProduto_PossuiOriginal();
		}
		public List<PalavraProduto> getListaPalavraProduto_Possui(int qtde) {
			return getAgregado().getListaPalavraProduto_Possui(qtde);
		}
		public void setListaPalavraProduto_Possui(List<PalavraProduto> lista) {
			getAgregado().setListaPalavraProduto_Possui(lista);
		}
		public void setListaPalavraProduto_PossuiByDao(List<PalavraProduto> lista) {
			getAgregado().setListaPalavraProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaPalavraProduto_Possui() {
			getAgregado().criaVaziaListaPalavraProduto_Possui();
		}
		
		public boolean existeListaPalavraProduto_Possui() {
			return getAgregado().existeListaPalavraProduto_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public PalavraVo(JSONObject json) throws JSONException{
		idPalavra =  ConversorJson.getInteger(json, "IdPalavra");
		descricao =  ConversorJson.getString(json, "Descricao");
		comum =  ConversorJson.getLogic(json, "Comum");
  	}
  	public String toString() {
  		return "" + descricao;
  	}
	private long idPalavra;
	public long getIdPalavra() {
		return idPalavra;
	}
	public void setIdPalavra(long _valor) {
		idPalavra = _valor;
	}


	private String descricao;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String _valor) {
		descricao = _valor;
	}


	private boolean comum;
	public boolean getComum() {
		return comum;
	}
	public void setComum(boolean _valor) {
		comum = _valor;
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