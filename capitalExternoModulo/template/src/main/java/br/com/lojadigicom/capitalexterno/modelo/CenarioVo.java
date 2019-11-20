
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
import br.com.lojadigicom.capitalexterno.modelo.agregado.CenarioAgregado;
import br.com.lojadigicom.capitalexterno.data.contract.CenarioContract;

public class CenarioVo implements Cenario  {

	public CenarioVo() {
  	}
  	
  	public long getIdObj()
    {
       return idCenario;
    }

	 // ----- INICIO AGREGADO -----------------
	private CenarioAgregado agregado = null;
	private CenarioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CenarioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idCenario;
	public long getIdCenario() {
		return idCenario;
	}
	public void setIdCenario(long _valor) {
		idCenario = _valor;
	}


	private String descricaoCenario;
	public String getDescricaoCenario() {
		return descricaoCenario;
	}
	public void setDescricaoCenario(String _valor) {
		descricaoCenario = _valor;
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
	
    	valores.put(CenarioContract.COLUNA_ID_CENARIO, idCenario);
    	valores.put(CenarioContract.COLUNA_DESCRICAO_CENARIO, descricaoCenario);
		return valores;
  	}
}