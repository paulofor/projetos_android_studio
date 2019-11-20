package br.com.digicom.log.erro;

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

import br.com.digicom.modelo.DCIObjetoDominio;


public class ErroExceptionVo implements ErroException , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idErroException;
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
  	
  
  	public ErroExceptionVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdErroException\" : \"" + idErroException + "\" "
		+ ", \"Stack\" : \"" + stack + "\" "
		+ ", \"Aplicacao\" : \"" + aplicacao + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdErroException",idErroException);
			json.put("Stack",stack);
			json.put("Aplicacao",aplicacao);
			json.put("IdUsuarioS",idUsuarioS);
		
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
		 " IdErroException=" + getIdErroException() 
		+  " Stack=" + getStack() 
		+  " Aplicacao=" + getAplicacao() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		
	;
	}
 
 
 
 
   
 
   
	
	
	
 
  
  
  
  
  
  	public ErroExceptionVo(JSONObject json) throws JSONException{
		idErroException =  ConversorJson.getInteger(json, "IdErroException");
		stack =  ConversorJson.getString(json, "Stack");
		aplicacao =  ConversorJson.getString(json, "Aplicacao");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
  	}
  	public String toString() {
  		return "" + stack;
  	}
	private long idErroException;
	public long getIdErroException() {
		return idErroException;
	}
	public void setIdErroException(long _valor) {
		idErroException = _valor;
	}


	private String stack;
	public String getStack() {
		return stack;
	}
	public void setStack(String _valor) {
		stack = _valor;
	}


	private String aplicacao;
	public String getAplicacao() {
		return aplicacao;
	}
	public void setAplicacao(String _valor) {
		aplicacao = _valor;
	}

	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento
		return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
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
		
	
	
	private boolean somenteMemoria = true;
	@Override
	public boolean getSomenteMemoria() {
		return somenteMemoria;
	}
	@Override
	public void setSomenteMemoria(boolean dado) {
		somenteMemoria = dado;
	}
	@Override
	public String getNomeClasse() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getTituloTela() {
		// TODO Auto-generated method stub
		return null;
	} 
}