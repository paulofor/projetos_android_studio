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
import coletapreco.modelo.agregado.UsuarioPesquisaAgregado;
import coletapreco.modelo.derivada.impl.UsuarioPesquisaDerivada;
import coletapreco.modelo.display.impl.UsuarioPesquisaDisplay;

public class UsuarioPesquisaVo implements UsuarioPesquisa , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idUsuarioPesquisa;
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
  	
  
  	public UsuarioPesquisaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdUsuarioPesquisa\" : \"" + idUsuarioPesquisa + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		+ ", \"IdNaturezaProdutoP\" : \"" + idNaturezaProdutoP + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdUsuarioPesquisa",idUsuarioPesquisa);
			json.put("IdUsuarioS",idUsuarioS);
			json.put("IdNaturezaProdutoP",idNaturezaProdutoP);
		
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
		 " IdUsuarioPesquisa=" + getIdUsuarioPesquisa() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		+  " IdNaturezaProdutoP=" + getIdNaturezaProdutoP() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idUsuarioPesquisa;
 	}
 	public String getNomeClasse() {
 		return "UsuarioPesquisa";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private UsuarioPesquisaDisplay display = null;
	private UsuarioPesquisaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new UsuarioPesquisaDisplay(this);
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
	private UsuarioPesquisaAgregado agregado = null;
	private UsuarioPesquisaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new UsuarioPesquisaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private UsuarioPesquisaDerivada derivada = null;
	private UsuarioPesquisaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new UsuarioPesquisaDerivada(this);
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
  	
		public Usuario getUsuario_Sincroniza() {
			return getAgregado().getUsuario_Sincroniza();
		}
		public void setUsuario_Sincroniza(Usuario item) {
			getAgregado().setUsuario_Sincroniza(item);
		}
		
		public void addListaUsuario_Sincroniza(Usuario item) {
			getAgregado().addListaUsuario_Sincroniza(item);
		}
		public Usuario getCorrenteUsuario_Sincroniza() {
			return getAgregado().getCorrenteUsuario_Sincroniza();
		}
		
		
		public NaturezaProduto getNaturezaProduto_Pesquisa() {
			return getAgregado().getNaturezaProduto_Pesquisa();
		}
		public void setNaturezaProduto_Pesquisa(NaturezaProduto item) {
			getAgregado().setNaturezaProduto_Pesquisa(item);
		}
		
		public void addListaNaturezaProduto_Pesquisa(NaturezaProduto item) {
			getAgregado().addListaNaturezaProduto_Pesquisa(item);
		}
		public NaturezaProduto getCorrenteNaturezaProduto_Pesquisa() {
			return getAgregado().getCorrenteNaturezaProduto_Pesquisa();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public UsuarioPesquisaVo(JSONObject json) throws JSONException{
		idUsuarioPesquisa =  ConversorJson.getInteger(json, "IdUsuarioPesquisa");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
		idNaturezaProdutoP =  ConversorJson.getInteger(json, "IdNaturezaProdutoP");
  	}
  	public String toString() {
  		return "" + idUsuarioPesquisa;
  	}
	private long idUsuarioPesquisa;
	public long getIdUsuarioPesquisa() {
		return idUsuarioPesquisa;
	}
	public void setIdUsuarioPesquisa(long _valor) {
		idUsuarioPesquisa = _valor;
	}

	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento
		if (idUsuarioS==0 && this.getUsuario_Sincroniza()!=null) 
			return getUsuario_Sincroniza().getId();
		else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
	}

	
	private long idNaturezaProdutoP;
	public long getIdNaturezaProdutoP() {
		// relacionamento
		if (idNaturezaProdutoP==0 && this.getNaturezaProduto_Pesquisa()!=null) 
			return getNaturezaProduto_Pesquisa().getId();
		else
			return idNaturezaProdutoP;
	}
	public void setIdNaturezaProdutoP(long _valor) {
		idNaturezaProdutoP = _valor;
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
  	
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
	public long getIdNaturezaProdutoPLazyLoader() {
		return idNaturezaProdutoP;
	} 
		
}