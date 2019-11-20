
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
import br.com.lojadigicom.capitalexterno.modelo.agregado.ItemCustoProdutoAgregado;
import br.com.lojadigicom.capitalexterno.data.contract.ItemCustoProdutoContract;

public class ItemCustoProdutoVo implements ItemCustoProduto  {

	public ItemCustoProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idItemCustoProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private ItemCustoProdutoAgregado agregado = null;
	private ItemCustoProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ItemCustoProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idItemCustoProduto;
	public long getIdItemCustoProduto() {
		return idItemCustoProduto;
	}
	public void setIdItemCustoProduto(long _valor) {
		idItemCustoProduto = _valor;
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
	
    	valores.put(ItemCustoProdutoContract.COLUNA_ID_ITEM_CUSTO_PRODUTO, idItemCustoProduto);
    	valores.put(ItemCustoProdutoContract.COLUNA_VALOR, valor);
    	valores.put(ItemCustoProdutoContract.COLUNA_DESCRICAO, descricao);
		return valores;
  	}
}