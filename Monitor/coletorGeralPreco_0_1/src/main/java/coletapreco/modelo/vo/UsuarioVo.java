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
import coletapreco.modelo.agregado.UsuarioAgregado;
import coletapreco.modelo.derivada.impl.UsuarioDerivada;
import coletapreco.modelo.display.impl.UsuarioDisplay;

public class UsuarioVo implements Usuario , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idUsuario;
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
  	
  
  	public UsuarioVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdUsuario\" : \"" + idUsuario + "\" "
		+ ", \"NomeUsuario\" : \"" + nomeUsuario + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdUsuario",idUsuario);
			json.put("NomeUsuario",nomeUsuario);
		
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
		 " IdUsuario=" + getIdUsuario() 
		+  " NomeUsuario=" + getNomeUsuario() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idUsuario;
 	}
 	public String getNomeClasse() {
 		return "Usuario";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private UsuarioDisplay display = null;
	private UsuarioDisplay getObjetoDisplay() {
		if (display==null) {
			display = new UsuarioDisplay(this);
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
	private UsuarioAgregado agregado = null;
	private UsuarioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new UsuarioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private UsuarioDerivada derivada = null;
	private UsuarioDerivada getDerivada() {
		if (derivada==null) {
			derivada = new UsuarioDerivada(this);
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
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public UsuarioVo(JSONObject json) throws JSONException{
		idUsuario =  ConversorJson.getInteger(json, "IdUsuario");
		nomeUsuario =  ConversorJson.getString(json, "NomeUsuario");
  	}
  	public String toString() {
  		return "" + nomeUsuario;
  	}
	private long idUsuario;
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long _valor) {
		idUsuario = _valor;
	}


	private String nomeUsuario;
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String _valor) {
		nomeUsuario = _valor;
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