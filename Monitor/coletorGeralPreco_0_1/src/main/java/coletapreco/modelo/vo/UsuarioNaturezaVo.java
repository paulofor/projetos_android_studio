package coletapreco.modelo.vo;

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
import coletapreco.modelo.agregado.UsuarioNaturezaAgregado;
import coletapreco.modelo.derivada.impl.UsuarioNaturezaDerivada;

public class UsuarioNaturezaVo implements UsuarioNatureza , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idUsuarioNatureza;
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
  	
  
  	public UsuarioNaturezaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdUsuarioNatureza\" : \"" + idUsuarioNatureza + "\" "
		+ ", \"IdUsuarioPp\" : \"" + idUsuarioPp + "\" "
		+ ", \"IdNaturezaProdutoP\" : \"" + idNaturezaProdutoP + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdUsuarioNatureza",idUsuarioNatureza);
			json.put("IdUsuarioPp",idUsuarioPp);
			json.put("IdNaturezaProdutoP",idNaturezaProdutoP);
		
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}

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
		 " IdUsuarioNatureza=" + getIdUsuarioNatureza() 
		+  " IdUsuarioPp=" + getIdUsuarioPp() 
		+  " IdNaturezaProdutoP=" + getIdNaturezaProdutoP() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idUsuarioNatureza;
 	}
 	public String getNomeClasse() {
 		return "UsuarioNatureza";
 	}
 	// ---------------------------------------------
 
 
 
    // ----- INICIO AGREGADO -----------------
	private UsuarioNaturezaAgregado agregado = null;
	private UsuarioNaturezaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new UsuarioNaturezaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private UsuarioNaturezaDerivada derivada = null;
	private UsuarioNaturezaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new UsuarioNaturezaDerivada(this);
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
  	
		public Usuario getUsuario_PesquisadoPor() {
			return getAgregado().getUsuario_PesquisadoPor();
		}
		public void setUsuario_PesquisadoPor(Usuario item) {
			getAgregado().setUsuario_PesquisadoPor(item);
		}
		
		public void addListaUsuario_PesquisadoPor(Usuario item) {
			getAgregado().addListaUsuario_PesquisadoPor(item);
		}
		public Usuario getCorrenteUsuario_PesquisadoPor() {
			return getAgregado().getCorrenteUsuario_PesquisadoPor();
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
  
  
  	public UsuarioNaturezaVo(JSONObject json) throws JSONException{
		idUsuarioNatureza =  ConversorJson.getInteger(json, "IdUsuarioNatureza");
		idUsuarioPp =  ConversorJson.getInteger(json, "IdUsuarioPp");
		idNaturezaProdutoP =  ConversorJson.getInteger(json, "IdNaturezaProdutoP");
  	}
  	public String toString() {
  		return "" + idUsuarioNatureza;
  	}
	private long idUsuarioNatureza;
	public long getIdUsuarioNatureza() {
		return idUsuarioNatureza;
	}
	public void setIdUsuarioNatureza(long _valor) {
		idUsuarioNatureza = _valor;
	}

	
	private long idUsuarioPp;
	public long getIdUsuarioPp() {
		// relacionamento
		if (idUsuarioPp==0 && this.getUsuario_PesquisadoPor()!=null) 
			return getUsuario_PesquisadoPor().getId();
		else
			return idUsuarioPp;
	}
	public void setIdUsuarioPp(long _valor) {
		idUsuarioPp = _valor;
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
		JSONObject json = JSon();
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
}