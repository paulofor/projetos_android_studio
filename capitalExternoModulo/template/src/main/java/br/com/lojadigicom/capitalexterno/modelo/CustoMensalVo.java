
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
import br.com.lojadigicom.capitalexterno.modelo.agregado.CustoMensalAgregado;
import br.com.lojadigicom.capitalexterno.data.contract.CustoMensalContract;

public class CustoMensalVo implements CustoMensal  {

	public CustoMensalVo() {
  	}
  	
  	public long getIdObj()
    {
       return idCustoMensal;
    }

	 // ----- INICIO AGREGADO -----------------
	private CustoMensalAgregado agregado = null;
	private CustoMensalAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CustoMensalAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idCustoMensal;
	public long getIdCustoMensal() {
		return idCustoMensal;
	}
	public void setIdCustoMensal(long _valor) {
		idCustoMensal = _valor;
	}


	private String descricao;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String _valor) {
		descricao = _valor;
	}


	private float valorMedio;
	public float getValorMedio() {
		return valorMedio;
	}
	public void setValorMedio(float _valor) {
		valorMedio = _valor;
	}
	
	public String getValorMedioTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valorMedio);
		return saida;
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
	
    	valores.put(CustoMensalContract.COLUNA_ID_CUSTO_MENSAL, idCustoMensal);
    	valores.put(CustoMensalContract.COLUNA_DESCRICAO, descricao);
    	valores.put(CustoMensalContract.COLUNA_VALOR_MEDIO, valorMedio);
		return valores;
  	}
}