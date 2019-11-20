
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
import br.com.lojadigicom.repcom.modelo.agregado.PagamentoFornecedorAgregado;
import br.com.lojadigicom.repcom.data.contract.PagamentoFornecedorContract;

public class PagamentoFornecedorVo implements PagamentoFornecedor  {

	public PagamentoFornecedorVo() {
  	}
  	
  	public long getIdObj()
    {
       return idPagamentoFornecedor;
    }

	 // ----- INICIO AGREGADO -----------------
	private PagamentoFornecedorAgregado agregado = null;
	private PagamentoFornecedorAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PagamentoFornecedorAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idPagamentoFornecedor;
	public long getIdPagamentoFornecedor() {
		return idPagamentoFornecedor;
	}
	public void setIdPagamentoFornecedor(long _valor) {
		idPagamentoFornecedor = _valor;
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
	
    	valores.put(PagamentoFornecedorContract.COLUNA_ID_PAGAMENTO_FORNECEDOR, idPagamentoFornecedor);
		return valores;
  	}
}