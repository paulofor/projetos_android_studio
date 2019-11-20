
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
import br.com.lojadigicom.capitalexterno.modelo.agregado.PrecoVendaProdutoAgregado;
import br.com.lojadigicom.capitalexterno.data.contract.PrecoVendaProdutoContract;

public class PrecoVendaProdutoVo implements PrecoVendaProduto  {

	public PrecoVendaProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idPrecoVendaProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private PrecoVendaProdutoAgregado agregado = null;
	private PrecoVendaProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PrecoVendaProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idPrecoVendaProduto;
	public long getIdPrecoVendaProduto() {
		return idPrecoVendaProduto;
	}
	public void setIdPrecoVendaProduto(long _valor) {
		idPrecoVendaProduto = _valor;
	}


	private float valor;
	public float getValor() {
		return valor;
	}
	public void setValor(float _valor) {
		valor = _valor;
	}
	
	public String getValorTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valor);
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
	
    	valores.put(PrecoVendaProdutoContract.COLUNA_ID_PRECO_VENDA_PRODUTO, idPrecoVendaProduto);
    	valores.put(PrecoVendaProdutoContract.COLUNA_VALOR, valor);
		return valores;
  	}
}