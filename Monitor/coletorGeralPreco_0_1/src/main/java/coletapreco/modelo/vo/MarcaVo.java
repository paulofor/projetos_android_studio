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
import coletapreco.modelo.agregado.MarcaAgregado;
import coletapreco.modelo.derivada.impl.MarcaDerivada;
import coletapreco.modelo.display.impl.MarcaDisplay;

public class MarcaVo implements Marca , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idMarca;
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
  	
  
  	public MarcaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdMarca\" : \"" + idMarca + "\" "
		+ ", \"NomeMarca\" : \"" + nomeMarca + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdMarca",idMarca);
			json.put("NomeMarca",nomeMarca);
		
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
		JSONArray listaProduto_ReferenteA = JSonListaProduto_ReferenteA();
		if (listaProduto_ReferenteA!=null) {
			json.put("ListaProdutoVo_ReferenteA",listaProduto_ReferenteA);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaProduto_ReferenteA() throws JSONException{
		if (getListaProduto_ReferenteA()==null) return null;
		JSONArray lista = new JSONArray();
		for (Produto item:this.getListaProduto_ReferenteAOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdMarca=" + getIdMarca() 
		+  " NomeMarca=" + getNomeMarca() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idMarca;
 	}
 	public String getNomeClasse() {
 		return "Marca";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private MarcaDisplay display = null;
	private MarcaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new MarcaDisplay(this);
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
	private MarcaAgregado agregado = null;
	private MarcaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new MarcaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private MarcaDerivada derivada = null;
	private MarcaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new MarcaDerivada(this);
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
	
		public Produto getCorrenteProduto_ReferenteA() {
			return getAgregado().getCorrenteProduto_ReferenteA();
		}
		public void addListaProduto_ReferenteA(Produto item) {
			getAgregado().addListaProduto_ReferenteA(item);
		}
		public List<Produto> getListaProduto_ReferenteA() {
			return getAgregado().getListaProduto_ReferenteA();
		}
		public List<Produto> getListaProduto_ReferenteAOriginal() {
			return getAgregado().getListaProduto_ReferenteAOriginal();
		}
		public List<Produto> getListaProduto_ReferenteA(int qtde) {
			return getAgregado().getListaProduto_ReferenteA(qtde);
		}
		public void setListaProduto_ReferenteA(List<Produto> lista) {
			getAgregado().setListaProduto_ReferenteA(lista);
		}
		public void setListaProduto_ReferenteAByDao(List<Produto> lista) {
			getAgregado().setListaProduto_ReferenteAByDao(lista);
		}
		public void criaVaziaListaProduto_ReferenteA() {
			getAgregado().criaVaziaListaProduto_ReferenteA();
		}
		
		public boolean existeListaProduto_ReferenteA() {
			return getAgregado().existeListaProduto_ReferenteA();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public MarcaVo(JSONObject json) throws JSONException{
		idMarca =  ConversorJson.getInteger(json, "IdMarca");
		nomeMarca =  ConversorJson.getString(json, "NomeMarca");
  	}
  	public String toString() {
  		return "" + nomeMarca;
  	}
	private long idMarca;
	public long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(long _valor) {
		idMarca = _valor;
	}


	private String nomeMarca;
	public String getNomeMarca() {
		return nomeMarca;
	}
	public void setNomeMarca(String _valor) {
		nomeMarca = _valor;
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