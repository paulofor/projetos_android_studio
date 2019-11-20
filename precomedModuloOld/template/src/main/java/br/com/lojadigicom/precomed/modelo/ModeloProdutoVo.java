
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
import br.com.lojadigicom.precomed.modelo.agregado.ModeloProdutoAgregado;
import br.com.lojadigicom.precomed.data.contract.ModeloProdutoContract;

public class ModeloProdutoVo implements ModeloProduto  {

	public ModeloProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idModeloProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private ModeloProdutoAgregado agregado = null;
	private ModeloProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ModeloProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idModeloProduto;
	public long getIdModeloProduto() {
		return idModeloProduto;
	}
	public void setIdModeloProduto(long _valor) {
		idModeloProduto = _valor;
	}


	private String nomeModeloProduto;
	public String getNomeModeloProduto() {
		return nomeModeloProduto;
	}
	public void setNomeModeloProduto(String _valor) {
		nomeModeloProduto = _valor;
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
	
		public ModeloProdutoProduto getCorrenteModeloProdutoProduto_ReferenteA() {
			return getAgregado().getCorrenteModeloProdutoProduto_ReferenteA();
		}
		public void addListaModeloProdutoProduto_ReferenteA(ModeloProdutoProduto item) {
			getAgregado().addListaModeloProdutoProduto_ReferenteA(item);
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA() {
			return getAgregado().getListaModeloProdutoProduto_ReferenteA();
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal() {
			return getAgregado().getListaModeloProdutoProduto_ReferenteAOriginal();
		}
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde) {
			return getAgregado().getListaModeloProdutoProduto_ReferenteA(qtde);
		}
		public void setListaModeloProdutoProduto_ReferenteA(List<ModeloProdutoProduto> lista) {
			getAgregado().setListaModeloProdutoProduto_ReferenteA(lista);
		}
		public void setListaModeloProdutoProduto_ReferenteAByDao(List<ModeloProdutoProduto> lista) {
			getAgregado().setListaModeloProdutoProduto_ReferenteAByDao(lista);
		}
		public void criaVaziaListaModeloProdutoProduto_ReferenteA() {
			getAgregado().criaVaziaListaModeloProdutoProduto_ReferenteA();
		}
		
		public boolean existeListaModeloProdutoProduto_ReferenteA() {
			return getAgregado().existeListaModeloProdutoProduto_ReferenteA();
		}
 		
		public ProdutoPesquisa getCorrenteProdutoPesquisa_Viabiliza() {
			return getAgregado().getCorrenteProdutoPesquisa_Viabiliza();
		}
		public void addListaProdutoPesquisa_Viabiliza(ProdutoPesquisa item) {
			getAgregado().addListaProdutoPesquisa_Viabiliza(item);
		}
		public List<ProdutoPesquisa> getListaProdutoPesquisa_Viabiliza() {
			return getAgregado().getListaProdutoPesquisa_Viabiliza();
		}
		public List<ProdutoPesquisa> getListaProdutoPesquisa_ViabilizaOriginal() {
			return getAgregado().getListaProdutoPesquisa_ViabilizaOriginal();
		}
		public List<ProdutoPesquisa> getListaProdutoPesquisa_Viabiliza(int qtde) {
			return getAgregado().getListaProdutoPesquisa_Viabiliza(qtde);
		}
		public void setListaProdutoPesquisa_Viabiliza(List<ProdutoPesquisa> lista) {
			getAgregado().setListaProdutoPesquisa_Viabiliza(lista);
		}
		public void setListaProdutoPesquisa_ViabilizaByDao(List<ProdutoPesquisa> lista) {
			getAgregado().setListaProdutoPesquisa_ViabilizaByDao(lista);
		}
		public void criaVaziaListaProdutoPesquisa_Viabiliza() {
			getAgregado().criaVaziaListaProdutoPesquisa_Viabiliza();
		}
		
		public boolean existeListaProdutoPesquisa_Viabiliza() {
			return getAgregado().existeListaProdutoPesquisa_Viabiliza();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ModeloProdutoContract.COLUNA_ID_MODELO_PRODUTO, idModeloProduto);
    	valores.put(ModeloProdutoContract.COLUNA_NOME_MODELO_PRODUTO, nomeModeloProduto);
		return valores;
  	}
}