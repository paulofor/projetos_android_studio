
package br.com.lojadigicom.coletorprecocliente.modelo;


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

import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.UsuarioAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioContract;

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


	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String _valor) {
		email = _valor;
	}


	private boolean plano01;
	public boolean getPlano01() {
		return plano01;
	}
	public void setPlano01(boolean _valor) {
		plano01 = _valor;
	}


	private boolean plano02;
	public boolean getPlano02() {
		return plano02;
	}
	public void setPlano02(boolean _valor) {
		plano02 = _valor;
	}


	private boolean plano03;
	public boolean getPlano03() {
		return plano03;
	}
	public void setPlano03(boolean _valor) {
		plano03 = _valor;
	}


	private boolean plano04;
	public boolean getPlano04() {
		return plano04;
	}
	public void setPlano04(boolean _valor) {
		plano04 = _valor;
	}


	private boolean plano05;
	public boolean getPlano05() {
		return plano05;
	}
	public void setPlano05(boolean _valor) {
		plano05 = _valor;
	}


	private Timestamp dataUltimaAlteracao;
	public Timestamp getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Timestamp _valor) {
		dataUltimaAlteracao = _valor;
	}


	public String getDataUltimaAlteracaoDDMMAAAA() {
		if (dataUltimaAlteracao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataUltimaAlteracao);
    }




	public String getDataUltimaAlteracaoHHMM() {
		if (dataUltimaAlteracao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataUltimaAlteracao);
    }
    public String getDataUltimaAlteracaoHHMMSS() {
		if (dataUltimaAlteracao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataUltimaAlteracao);
    }
	private boolean permiteSincronizar;
	public boolean getPermiteSincronizar() {
		return permiteSincronizar;
	}
	public void setPermiteSincronizar(boolean _valor) {
		permiteSincronizar = _valor;
	}


	private String codigoExterno;
	public String getCodigoExterno() {
		return codigoExterno;
	}
	public void setCodigoExterno(String _valor) {
		codigoExterno = _valor;
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
    	valores.put(UsuarioContract.COLUNA_EMAIL, email);
    	valores.put(UsuarioContract.COLUNA_PLANO01, plano01);
    	valores.put(UsuarioContract.COLUNA_PLANO02, plano02);
    	valores.put(UsuarioContract.COLUNA_PLANO03, plano03);
    	valores.put(UsuarioContract.COLUNA_PLANO04, plano04);
    	valores.put(UsuarioContract.COLUNA_PLANO05, plano05);
    	valores.put(UsuarioContract.COLUNA_DATA_ULTIMA_ALTERACAO, UtilDatas.getDataHoraLong(dataUltimaAlteracao));
    	valores.put(UsuarioContract.COLUNA_PERMITE_SINCRONIZAR, permiteSincronizar);
    	valores.put(UsuarioContract.COLUNA_CODIGO_EXTERNO, codigoExterno);
		return valores;
  	}
}