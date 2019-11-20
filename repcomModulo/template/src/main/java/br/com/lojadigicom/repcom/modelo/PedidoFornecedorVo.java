
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
import br.com.lojadigicom.repcom.modelo.agregado.PedidoFornecedorAgregado;
import br.com.lojadigicom.repcom.data.contract.PedidoFornecedorContract;

public class PedidoFornecedorVo implements PedidoFornecedor  {

	public PedidoFornecedorVo() {
  	}
  	
  	public long getIdObj()
    {
       return idPedidoFornecedor;
    }

	 // ----- INICIO AGREGADO -----------------
	private PedidoFornecedorAgregado agregado = null;
	private PedidoFornecedorAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PedidoFornecedorAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idPedidoFornecedor;
	public long getIdPedidoFornecedor() {
		return idPedidoFornecedor;
	}
	public void setIdPedidoFornecedor(long _valor) {
		idPedidoFornecedor = _valor;
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
	
		public ProdutoPedidoFornecedor getCorrenteProdutoPedidoFornecedor_Associada() {
			return getAgregado().getCorrenteProdutoPedidoFornecedor_Associada();
		}
		public void addListaProdutoPedidoFornecedor_Associada(ProdutoPedidoFornecedor item) {
			getAgregado().addListaProdutoPedidoFornecedor_Associada(item);
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada() {
			return getAgregado().getListaProdutoPedidoFornecedor_Associada();
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_AssociadaOriginal() {
			return getAgregado().getListaProdutoPedidoFornecedor_AssociadaOriginal();
		}
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada(int qtde) {
			return getAgregado().getListaProdutoPedidoFornecedor_Associada(qtde);
		}
		public void setListaProdutoPedidoFornecedor_Associada(List<ProdutoPedidoFornecedor> lista) {
			getAgregado().setListaProdutoPedidoFornecedor_Associada(lista);
		}
		public void setListaProdutoPedidoFornecedor_AssociadaByDao(List<ProdutoPedidoFornecedor> lista) {
			getAgregado().setListaProdutoPedidoFornecedor_AssociadaByDao(lista);
		}
		public void criaVaziaListaProdutoPedidoFornecedor_Associada() {
			getAgregado().criaVaziaListaProdutoPedidoFornecedor_Associada();
		}
		
		public boolean existeListaProdutoPedidoFornecedor_Associada() {
			return getAgregado().existeListaProdutoPedidoFornecedor_Associada();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(PedidoFornecedorContract.COLUNA_ID_PEDIDO_FORNECEDOR, idPedidoFornecedor);
		return valores;
  	}
}