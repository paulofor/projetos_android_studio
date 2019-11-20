
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
import br.com.lojadigicom.repcom.modelo.agregado.CategoriaProdutoProdutoAgregado;
import br.com.lojadigicom.repcom.data.contract.CategoriaProdutoProdutoContract;

public class CategoriaProdutoProdutoVo implements CategoriaProdutoProduto  {

	public CategoriaProdutoProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idCategoriaProdutoProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private CategoriaProdutoProdutoAgregado agregado = null;
	private CategoriaProdutoProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CategoriaProdutoProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idCategoriaProdutoProduto;
	public long getIdCategoriaProdutoProduto() {
		return idCategoriaProdutoProduto;
	}
	public void setIdCategoriaProdutoProduto(long _valor) {
		idCategoriaProdutoProduto = _valor;
	}


	private Timestamp dataInclusao;
	public Timestamp getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Timestamp _valor) {
		dataInclusao = _valor;
	}


	public String getDataInclusaoDDMMAAAA() {
		if (dataInclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataInclusao);
    }
    public void setDataInclusaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInclusao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataInclusaoDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInclusao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }



	
	private long idCategoriaProdutoRa;
	public long getIdCategoriaProdutoRa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getCategoriaProduto_ReferenteA()!=null) 
		//	return getCategoriaProduto_ReferenteA().getIdObj();
		//else
			return idCategoriaProdutoRa;
	}
	public void setIdCategoriaProdutoRa(long _valor) {
		idCategoriaProdutoRa = _valor;
	}

	
	private long idProdutoRa;
	public long getIdProdutoRa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getProduto_ReferenteA()!=null) 
		//	return getProduto_ReferenteA().getIdObj();
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
  	
	public long getIdCategoriaProdutoRaLazyLoader() {
		return idCategoriaProdutoRa;
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
  	
		public CategoriaProduto getCategoriaProduto_ReferenteA() {
			return getAgregado().getCategoriaProduto_ReferenteA();
		}
		public void setCategoriaProduto_ReferenteA(CategoriaProduto item) {
			// Coloquei em 10-11-2016
			idCategoriaProdutoRa = item.getIdObj();
			getAgregado().setCategoriaProduto_ReferenteA(item);
		}
		
		public void addListaCategoriaProduto_ReferenteA(CategoriaProduto item) {
			getAgregado().addListaCategoriaProduto_ReferenteA(item);
		}
		public CategoriaProduto getCorrenteCategoriaProduto_ReferenteA() {
			return getAgregado().getCorrenteCategoriaProduto_ReferenteA();
		}
		
		
		public Produto getProduto_ReferenteA() {
			return getAgregado().getProduto_ReferenteA();
		}
		public void setProduto_ReferenteA(Produto item) {
			// Coloquei em 10-11-2016
			idProdutoRa = item.getIdObj();
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
	
    	valores.put(CategoriaProdutoProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_PRODUTO, idCategoriaProdutoProduto);
    	valores.put(CategoriaProdutoProdutoContract.COLUNA_DATA_INCLUSAO, UtilDatas.getDataLong(dataInclusao));
		valores.put(CategoriaProdutoProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_RA, idCategoriaProdutoRa);
	
		valores.put(CategoriaProdutoProdutoContract.COLUNA_ID_PRODUTO_RA, idProdutoRa);
	
		return valores;
  	}
}