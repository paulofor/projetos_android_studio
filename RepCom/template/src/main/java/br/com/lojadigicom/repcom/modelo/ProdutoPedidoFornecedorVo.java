
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
import br.com.lojadigicom.repcom.modelo.agregado.ProdutoPedidoFornecedorAgregado;
import br.com.lojadigicom.repcom.data.contract.ProdutoPedidoFornecedorContract;

public class ProdutoPedidoFornecedorVo implements ProdutoPedidoFornecedor  {

	public ProdutoPedidoFornecedorVo() {
  	}
  	
  	public long getIdObj()
    {
       return idProdutoPedidoFornecedor;
    }

	 // ----- INICIO AGREGADO -----------------
	private ProdutoPedidoFornecedorAgregado agregado = null;
	private ProdutoPedidoFornecedorAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ProdutoPedidoFornecedorAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idProdutoPedidoFornecedor;
	public long getIdProdutoPedidoFornecedor() {
		return idProdutoPedidoFornecedor;
	}
	public void setIdProdutoPedidoFornecedor(long _valor) {
		idProdutoPedidoFornecedor = _valor;
	}

	
	private long idPedidoFornecedorA;
	public long getIdPedidoFornecedorA() {
		// relacionamento
		//if (idPedidoFornecedorA==0 && this.getPedidoFornecedor_Associada()!=null) 
		//	return getPedidoFornecedor_Associada().getId();
		//else
			return idPedidoFornecedorA;
	}
	public void setIdPedidoFornecedorA(long _valor) {
		idPedidoFornecedorA = _valor;
	}

	
	private long idProdutoA;
	public long getIdProdutoA() {
		// relacionamento
		//if (idProdutoA==0 && this.getProduto_Associada()!=null) 
		//	return getProduto_Associada().getId();
		//else
			return idProdutoA;
	}
	public void setIdProdutoA(long _valor) {
		idProdutoA = _valor;
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
  	
	public long getIdPedidoFornecedorALazyLoader() {
		return idPedidoFornecedorA;
	} 
		
	public long getIdProdutoALazyLoader() {
		return idProdutoA;
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
  	
		public PedidoFornecedor getPedidoFornecedor_Associada() {
			return getAgregado().getPedidoFornecedor_Associada();
		}
		public void setPedidoFornecedor_Associada(PedidoFornecedor item) {
			getAgregado().setPedidoFornecedor_Associada(item);
		}
		
		public void addListaPedidoFornecedor_Associada(PedidoFornecedor item) {
			getAgregado().addListaPedidoFornecedor_Associada(item);
		}
		public PedidoFornecedor getCorrentePedidoFornecedor_Associada() {
			return getAgregado().getCorrentePedidoFornecedor_Associada();
		}
		
		
		public Produto getProduto_Associada() {
			return getAgregado().getProduto_Associada();
		}
		public void setProduto_Associada(Produto item) {
			getAgregado().setProduto_Associada(item);
		}
		
		public void addListaProduto_Associada(Produto item) {
			getAgregado().addListaProduto_Associada(item);
		}
		public Produto getCorrenteProduto_Associada() {
			return getAgregado().getCorrenteProduto_Associada();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ProdutoPedidoFornecedorContract.COLUNA_ID_PRODUTO_PEDIDO_FORNECEDOR, idProdutoPedidoFornecedor);
		valores.put(ProdutoPedidoFornecedorContract.COLUNA_ID_PEDIDO_FORNECEDOR_A, idPedidoFornecedorA);
	
		valores.put(ProdutoPedidoFornecedorContract.COLUNA_ID_PRODUTO_A, idProdutoA);
	
		return valores;
  	}
}