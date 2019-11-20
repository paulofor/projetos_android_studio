
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
import br.com.lojadigicom.repcom.modelo.agregado.DispositivoUsuarioAgregado;
import br.com.lojadigicom.repcom.data.contract.DispositivoUsuarioContract;

public class DispositivoUsuarioVo implements DispositivoUsuario  {

	public DispositivoUsuarioVo() {
  	}
  	
  	public long getIdObj()
    {
       return idDispositivoUsuario;
    }

	 // ----- INICIO AGREGADO -----------------
	private DispositivoUsuarioAgregado agregado = null;
	private DispositivoUsuarioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new DispositivoUsuarioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idDispositivoUsuario;
	public long getIdDispositivoUsuario() {
		return idDispositivoUsuario;
	}
	public void setIdDispositivoUsuario(long _valor) {
		idDispositivoUsuario = _valor;
	}


	private String numeroCelular;
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String _valor) {
		numeroCelular = _valor;
	}


	private String codigoDispositivo;
	public String getCodigoDispositivo() {
		return codigoDispositivo;
	}
	public void setCodigoDispositivo(String _valor) {
		codigoDispositivo = _valor;
	}


	private String tipoAcesso;
	public String getTipoAcesso() {
		return tipoAcesso;
	}
	public void setTipoAcesso(String _valor) {
		tipoAcesso = _valor;
	}


	private String melhorPath;
	public String getMelhorPath() {
		return melhorPath;
	}
	public void setMelhorPath(String _valor) {
		melhorPath = _valor;
	}

	
	private long idUsuarioRa;
	public long getIdUsuarioRa() {
		// relacionamento
		//if (idUsuarioRa==0 && this.getUsuario_ReferenteA()!=null) 
		//	return getUsuario_ReferenteA().getId();
		//else
			return idUsuarioRa;
	}
	public void setIdUsuarioRa(long _valor) {
		idUsuarioRa = _valor;
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
  	
	public long getIdUsuarioRaLazyLoader() {
		return idUsuarioRa;
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
  	
		public Usuario getUsuario_ReferenteA() {
			return getAgregado().getUsuario_ReferenteA();
		}
		public void setUsuario_ReferenteA(Usuario item) {
			getAgregado().setUsuario_ReferenteA(item);
		}
		
		public void addListaUsuario_ReferenteA(Usuario item) {
			getAgregado().addListaUsuario_ReferenteA(item);
		}
		public Usuario getCorrenteUsuario_ReferenteA() {
			return getAgregado().getCorrenteUsuario_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(DispositivoUsuarioContract.COLUNA_ID_DISPOSITIVO_USUARIO, idDispositivoUsuario);
    	valores.put(DispositivoUsuarioContract.COLUNA_NUMERO_CELULAR, numeroCelular);
    	valores.put(DispositivoUsuarioContract.COLUNA_CODIGO_DISPOSITIVO, codigoDispositivo);
    	valores.put(DispositivoUsuarioContract.COLUNA_TIPO_ACESSO, tipoAcesso);
    	valores.put(DispositivoUsuarioContract.COLUNA_MELHOR_PATH, melhorPath);
		valores.put(DispositivoUsuarioContract.COLUNA_ID_USUARIO_RA, idUsuarioRa);
	
		return valores;
  	}
}