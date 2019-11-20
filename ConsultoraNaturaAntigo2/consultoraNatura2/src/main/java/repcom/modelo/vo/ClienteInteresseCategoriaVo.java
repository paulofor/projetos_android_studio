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
import repcom.modelo.agregado.ClienteInteresseCategoriaAgregado;
import repcom.modelo.derivada.impl.ClienteInteresseCategoriaDerivada;
import repcom.modelo.display.impl.ClienteInteresseCategoriaDisplay;

public class ClienteInteresseCategoriaVo implements ClienteInteresseCategoria , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idClienteInteresseCategoria;
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
  	
  
  	public ClienteInteresseCategoriaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdClienteInteresseCategoria\" : \"" + idClienteInteresseCategoria + "\" "
		+ ", \"IdClienteA\" : \"" + idClienteA + "\" "
		+ ", \"IdCategoriaProdutoA\" : \"" + idCategoriaProdutoA + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdClienteInteresseCategoria",idClienteInteresseCategoria);
			json.put("IdClienteA",idClienteA);
			json.put("IdCategoriaProdutoA",idCategoriaProdutoA);
		
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
		 " IdClienteInteresseCategoria=" + getIdClienteInteresseCategoria() 
		+  " IdClienteA=" + getIdClienteA() 
		+  " IdCategoriaProdutoA=" + getIdCategoriaProdutoA() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idClienteInteresseCategoria;
 	}
 	public String getNomeClasse() {
 		return "ClienteInteresseCategoria";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ClienteInteresseCategoriaDisplay display = null;
	private ClienteInteresseCategoriaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ClienteInteresseCategoriaDisplay(this);
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
	private ClienteInteresseCategoriaAgregado agregado = null;
	private ClienteInteresseCategoriaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ClienteInteresseCategoriaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ClienteInteresseCategoriaDerivada derivada = null;
	private ClienteInteresseCategoriaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ClienteInteresseCategoriaDerivada(this);
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
  	
		public Cliente getCliente_Associada() {
			return getAgregado().getCliente_Associada();
		}
		public void setCliente_Associada(Cliente item) {
			getAgregado().setCliente_Associada(item);
		}
		
		public void addListaCliente_Associada(Cliente item) {
			getAgregado().addListaCliente_Associada(item);
		}
		public Cliente getCorrenteCliente_Associada() {
			return getAgregado().getCorrenteCliente_Associada();
		}
		
		
		public CategoriaProduto getCategoriaProduto_Associada() {
			return getAgregado().getCategoriaProduto_Associada();
		}
		public void setCategoriaProduto_Associada(CategoriaProduto item) {
			getAgregado().setCategoriaProduto_Associada(item);
		}
		
		public void addListaCategoriaProduto_Associada(CategoriaProduto item) {
			getAgregado().addListaCategoriaProduto_Associada(item);
		}
		public CategoriaProduto getCorrenteCategoriaProduto_Associada() {
			return getAgregado().getCorrenteCategoriaProduto_Associada();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ClienteInteresseCategoriaVo(JSONObject json) throws JSONException{
		idClienteInteresseCategoria =  ConversorJson.getInteger(json, "IdClienteInteresseCategoria");
		idClienteA =  ConversorJson.getInteger(json, "IdClienteA");
		idCategoriaProdutoA =  ConversorJson.getInteger(json, "IdCategoriaProdutoA");
  	}
  	public String toString() {
  		return "" + idClienteInteresseCategoria;
  	}
	private long idClienteInteresseCategoria;
	public long getIdClienteInteresseCategoria() {
		return idClienteInteresseCategoria;
	}
	public void setIdClienteInteresseCategoria(long _valor) {
		idClienteInteresseCategoria = _valor;
	}

	
	private long idClienteA;
	public long getIdClienteA() {
		// relacionamento
		if (idClienteA==0 && this.getCliente_Associada()!=null) 
			return getCliente_Associada().getId();
		else
			return idClienteA;
	}
	public void setIdClienteA(long _valor) {
		idClienteA = _valor;
	}

	
	private long idCategoriaProdutoA;
	public long getIdCategoriaProdutoA() {
		// relacionamento
		if (idCategoriaProdutoA==0 && this.getCategoriaProduto_Associada()!=null) 
			return getCategoriaProduto_Associada().getId();
		else
			return idCategoriaProdutoA;
	}
	public void setIdCategoriaProdutoA(long _valor) {
		idCategoriaProdutoA = _valor;
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
  	
	public long getIdClienteALazyLoader() {
		return idClienteA;
	} 
		
	public long getIdCategoriaProdutoALazyLoader() {
		return idCategoriaProdutoA;
	} 
		
}