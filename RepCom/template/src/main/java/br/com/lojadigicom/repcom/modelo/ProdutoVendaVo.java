
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
import br.com.lojadigicom.repcom.modelo.agregado.ProdutoVendaAgregado;
import br.com.lojadigicom.repcom.data.contract.ProdutoVendaContract;

public class ProdutoVendaVo implements ProdutoVenda  {

	public ProdutoVendaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idProdutoVenda;
    }

	 // ----- INICIO AGREGADO -----------------
	private ProdutoVendaAgregado agregado = null;
	private ProdutoVendaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ProdutoVendaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idProdutoVenda;
	public long getIdProdutoVenda() {
		return idProdutoVenda;
	}
	public void setIdProdutoVenda(long _valor) {
		idProdutoVenda = _valor;
	}


	private long quantidade;
	public long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(long _valor) {
		quantidade = _valor;
	}


	private float valorTotal;
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float _valor) {
		valorTotal = _valor;
	}
	
	public String getValorTotalTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valorTotal);
		return saida;
	}

	private float valorItem;
	public float getValorItem() {
		return valorItem;
	}
	public void setValorItem(float _valor) {
		valorItem = _valor;
	}
	
	public String getValorItemTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valorItem);
		return saida;
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

	
	private long idVendaA;
	public long getIdVendaA() {
		// relacionamento
		//if (idVendaA==0 && this.getVenda_Associada()!=null) 
		//	return getVenda_Associada().getId();
		//else
			return idVendaA;
	}
	public void setIdVendaA(long _valor) {
		idVendaA = _valor;
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
  	
	public long getIdProdutoALazyLoader() {
		return idProdutoA;
	} 
		
	public long getIdVendaALazyLoader() {
		return idVendaA;
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
		
		
		public Venda getVenda_Associada() {
			return getAgregado().getVenda_Associada();
		}
		public void setVenda_Associada(Venda item) {
			getAgregado().setVenda_Associada(item);
		}
		
		public void addListaVenda_Associada(Venda item) {
			getAgregado().addListaVenda_Associada(item);
		}
		public Venda getCorrenteVenda_Associada() {
			return getAgregado().getCorrenteVenda_Associada();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ProdutoVendaContract.COLUNA_ID_PRODUTO_VENDA, idProdutoVenda);
    	valores.put(ProdutoVendaContract.COLUNA_QUANTIDADE, quantidade);
    	valores.put(ProdutoVendaContract.COLUNA_VALOR_TOTAL, valorTotal);
    	valores.put(ProdutoVendaContract.COLUNA_VALOR_ITEM, valorItem);
		valores.put(ProdutoVendaContract.COLUNA_ID_PRODUTO_A, idProdutoA);
	
		valores.put(ProdutoVendaContract.COLUNA_ID_VENDA_A, idVendaA);
	
		return valores;
  	}
}