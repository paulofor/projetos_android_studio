
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
import br.com.lojadigicom.capitalexterno.modelo.agregado.MesAnoAgregado;
import br.com.lojadigicom.capitalexterno.data.contract.MesAnoContract;

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


	private Timestamp dataMesAno;
	public Timestamp getDataMesAno() {
		return dataMesAno;
	}
	public void setDataMesAno(Timestamp _valor) {
		dataMesAno = _valor;
	}


	public String getDataMesAnoDDMMAAAA() {
		if (dataMesAno==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataMesAno);
    }
    public void setDataMesAnoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataMesAno = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataMesAnoDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataMesAno = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private String descricao;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String _valor) {
		descricao = _valor;
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
    	valores.put(MesAnoContract.COLUNA_DATA_MES_ANO, UtilDatas.getDataLong(dataMesAno));
    	valores.put(MesAnoContract.COLUNA_DESCRICAO, descricao);
		return valores;
  	}
}