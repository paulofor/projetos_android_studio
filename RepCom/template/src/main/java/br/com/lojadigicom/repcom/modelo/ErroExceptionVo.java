
package br.com.lojadigicom.repcom.modelo;


import android.view.View;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.content.ContentValues;

import br.com.lojadigicom.repcom.framework.util.UtilDatas;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.agregado.ErroExceptionAgregado;
import br.com.lojadigicom.repcom.data.contract.ErroExceptionContract;

public class ErroExceptionVo implements ErroException  {

	public ErroExceptionVo() {
  	}
  	
  	public long getIdObj()
    {
       return idErroException;
    }

	 // ----- INICIO AGREGADO -----------------
	private ErroExceptionAgregado agregado = null;
	private ErroExceptionAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ErroExceptionAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
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
		//if (idUsuarioS==0 && this.getUsuario_Sincroniza()!=null) 
		//	return getUsuario_Sincroniza().getId();
		//else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
	}





	private String operacaoSinc = null;

	public String getOperacaoSinc() {
		return operacaoSinc;
	}

	public void setOperacaoSinc(String valor) {
		operacaoSinc = valor;
	}

	/*
	public JSONObject JSonSinc() throws JSONException {
		JSONObject json = JSonSimples();
		json.put("OperacaoSinc", operacaoSinc);
		return json;
	}
	*/
	
	
	private boolean salvaPreliminar = false;

	public void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}

	public boolean getSalvaPreliminar() {
		return salvaPreliminar;
	}
	
	// ComChaveEstrangeira
  	
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
	
	
	private boolean somenteMemoria = true;

	public boolean getSomenteMemoria() {
		return somenteMemoria;
	}

	public void setSomenteMemoria(boolean dado) {
		somenteMemoria = dado;
	} 
	
	
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
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ErroExceptionContract.COLUNA_ID_ERRO_EXCEPTION, idErroException);
    	valores.put(ErroExceptionContract.COLUNA_STACK, stack);
    	valores.put(ErroExceptionContract.COLUNA_APLICACAO, aplicacao);
		valores.put(ErroExceptionContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}