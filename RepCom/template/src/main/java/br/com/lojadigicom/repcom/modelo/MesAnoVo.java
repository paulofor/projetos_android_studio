
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
import br.com.lojadigicom.repcom.modelo.agregado.MesAnoAgregado;
import br.com.lojadigicom.repcom.data.contract.MesAnoContract;

public class MesAnoVo implements MesAno  {

	public MesAnoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idMesAno;
    }

	 // ----- INICIO AGREGADO -----------------
	private MesAnoAgregado agregado = null;
	private MesAnoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new MesAnoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idMesAno;
	public long getIdMesAno() {
		return idMesAno;
	}
	public void setIdMesAno(long _valor) {
		idMesAno = _valor;
	}


	private long mes;
	public long getMes() {
		return mes;
	}
	public void setMes(long _valor) {
		mes = _valor;
	}


	private long ano;
	public long getAno() {
		return ano;
	}
	public void setAno(long _valor) {
		ano = _valor;
	}


	private String descricaoTela;
	public String getDescricaoTela() {
		return descricaoTela;
	}
	public void setDescricaoTela(String _valor) {
		descricaoTela = _valor;
	}


	private Timestamp dataReferencia;
	public Timestamp getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(Timestamp _valor) {
		dataReferencia = _valor;
	}


	public String getDataReferenciaDDMMAAAA() {
		if (dataReferencia==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataReferencia);
    }
    public void setDataReferenciaDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataReferencia = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataReferenciaDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataReferencia = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
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
	
    	valores.put(MesAnoContract.COLUNA_ID_MES_ANO, idMesAno);
    	valores.put(MesAnoContract.COLUNA_MES, mes);
    	valores.put(MesAnoContract.COLUNA_ANO, ano);
    	valores.put(MesAnoContract.COLUNA_DESCRICAO_TELA, descricaoTela);
    	valores.put(MesAnoContract.COLUNA_DATA_REFERENCIA, UtilDatas.getDataLong(dataReferencia));
		return valores;
  	}
}