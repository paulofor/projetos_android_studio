
package br.com.lojadigicom.capitalexterno.modelo;


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

import br.com.lojadigicom.capitalexterno.framework.util.UtilDatas;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.agregado.UsuarioAgregado;
import br.com.lojadigicom.capitalexterno.data.contract.UsuarioContract;

public class UsuarioVo implements Usuario  {

	public UsuarioVo() {
  	}
  	
  	public long getIdObj()
    {
       return idUsuario;
    }

	 // ----- INICIO AGREGADO -----------------
	private UsuarioAgregado agregado = null;
	private UsuarioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new UsuarioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
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
  	
	
	
	private boolean somenteMemoria = true;

	public boolean getSomenteMemoria() {
		return somenteMemoria;
	}

	public void setSomenteMemoria(boolean dado) {
		somenteMemoria = dado;
	} 
	
	
	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(UsuarioContract.COLUNA_ID_USUARIO, idUsuario);
    	valores.put(UsuarioContract.COLUNA_NOME_USUARIO, nomeUsuario);
		return valores;
  	}
}