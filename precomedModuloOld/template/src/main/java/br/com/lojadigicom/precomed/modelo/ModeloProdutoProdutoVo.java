
package br.com.lojadigicom.precomed.modelo;


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

import br.com.lojadigicom.precomed.framework.util.UtilDatas;
import br.com.lojadigicom.precomed.framework.log.DCLog;
import br.com.lojadigicom.precomed.modelo.agregado.ModeloProdutoProdutoAgregado;
import br.com.lojadigicom.precomed.data.contract.ModeloProdutoProdutoContract;

public class ModeloProdutoProdutoVo implements ModeloProdutoProduto  {

	public ModeloProdutoProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idModeloProdutoProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private ModeloProdutoProdutoAgregado agregado = null;
	private ModeloProdutoProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ModeloProdutoProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idModeloProdutoProduto;
	public long getIdModeloProdutoProduto() {
		return idModeloProdutoProduto;
	}
	public void setIdModeloProdutoProduto(long _valor) {
		idModeloProdutoProduto = _valor;
	}

	
	private long idModeloProdutoRa;
	public long getIdModeloProdutoRa() {
		// relacionamento
		//if (idModeloProdutoRa==0 && this.getModeloProduto_ReferenteA()!=null) 
		//	return getModeloProduto_ReferenteA().getId();
		//else
			return idModeloProdutoRa;
	}
	public void setIdModeloProdutoRa(long _valor) {
		idModeloProdutoRa = _valor;
	}

	
	private long idProdutoRa;
	public long getIdProdutoRa() {
		// relacionamento
		//if (idProdutoRa==0 && this.getProduto_ReferenteA()!=null) 
		//	return getProduto_ReferenteA().getId();
		//else
			return idProdutoRa;
	}
	public void setIdProdutoRa(long _valor) {
		idProdutoRa = _valor;
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
  	
	public long getIdModeloProdutoRaLazyLoader() {
		return idModeloProdutoRa;
	} 
		
	public long getIdProdutoRaLazyLoader() {
		return idProdutoRa;
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
  	
		public ModeloProduto getModeloProduto_ReferenteA() {
			return getAgregado().getModeloProduto_ReferenteA();
		}
		public void setModeloProduto_ReferenteA(ModeloProduto item) {
			getAgregado().setModeloProduto_ReferenteA(item);
		}
		
		public void addListaModeloProduto_ReferenteA(ModeloProduto item) {
			getAgregado().addListaModeloProduto_ReferenteA(item);
		}
		public ModeloProduto getCorrenteModeloProduto_ReferenteA() {
			return getAgregado().getCorrenteModeloProduto_ReferenteA();
		}
		
		
		public Produto getProduto_ReferenteA() {
			return getAgregado().getProduto_ReferenteA();
		}
		public void setProduto_ReferenteA(Produto item) {
			getAgregado().setProduto_ReferenteA(item);
		}
		
		public void addListaProduto_ReferenteA(Produto item) {
			getAgregado().addListaProduto_ReferenteA(item);
		}
		public Produto getCorrenteProduto_ReferenteA() {
			return getAgregado().getCorrenteProduto_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ModeloProdutoProdutoContract.COLUNA_ID_MODELO_PRODUTO_PRODUTO, idModeloProdutoProduto);
		valores.put(ModeloProdutoProdutoContract.COLUNA_ID_MODELO_PRODUTO_RA, idModeloProdutoRa);
	
		valores.put(ModeloProdutoProdutoContract.COLUNA_ID_PRODUTO_RA, idProdutoRa);
	
		return valores;
  	}
}