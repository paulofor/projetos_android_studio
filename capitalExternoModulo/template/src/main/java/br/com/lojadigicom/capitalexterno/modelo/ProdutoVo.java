
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
import br.com.lojadigicom.capitalexterno.modelo.agregado.ProdutoAgregado;
import br.com.lojadigicom.capitalexterno.data.contract.ProdutoContract;

public class ProdutoVo implements Produto  {

	public ProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private ProdutoAgregado agregado = null;
	private ProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idProduto;
	public long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(long _valor) {
		idProduto = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}

	
	private long idLinhaProdutoPa;
	public long getIdLinhaProdutoPa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getLinhaProduto_PertenceA()!=null) 
		//	return getLinhaProduto_PertenceA().getIdObj();
		//else
			return idLinhaProdutoPa;
	}
	public void setIdLinhaProdutoPa(long _valor) {
		idLinhaProdutoPa = _valor;
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
  	
	public long getIdLinhaProdutoPaLazyLoader() {
		return idLinhaProdutoPa;
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
  	
		public LinhaProduto getLinhaProduto_PertenceA() {
			return getAgregado().getLinhaProduto_PertenceA();
		}
		public void setLinhaProduto_PertenceA(LinhaProduto item) {
			// Coloquei em 10-11-2016
			idLinhaProdutoPa = item.getIdObj();
			getAgregado().setLinhaProduto_PertenceA(item);
		}
		
		public void addListaLinhaProduto_PertenceA(LinhaProduto item) {
			getAgregado().addListaLinhaProduto_PertenceA(item);
		}
		public LinhaProduto getCorrenteLinhaProduto_PertenceA() {
			return getAgregado().getCorrenteLinhaProduto_PertenceA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ProdutoContract.COLUNA_ID_PRODUTO, idProduto);
    	valores.put(ProdutoContract.COLUNA_NOME, nome);
		valores.put(ProdutoContract.COLUNA_ID_LINHA_PRODUTO_PA, idLinhaProdutoPa);
	
		return valores;
  	}
}