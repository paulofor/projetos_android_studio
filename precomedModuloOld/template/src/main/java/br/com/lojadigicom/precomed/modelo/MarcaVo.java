
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
import br.com.lojadigicom.precomed.modelo.agregado.MarcaAgregado;
import br.com.lojadigicom.precomed.data.contract.MarcaContract;

public class MarcaVo implements Marca  {

	public MarcaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idMarca;
    }

	 // ----- INICIO AGREGADO -----------------
	private MarcaAgregado agregado = null;
	private MarcaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new MarcaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idMarca;
	public long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(long _valor) {
		idMarca = _valor;
	}


	private String nomeMarca;
	public String getNomeMarca() {
		return nomeMarca;
	}
	public void setNomeMarca(String _valor) {
		nomeMarca = _valor;
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
	
		public Produto getCorrenteProduto_ReferenteA() {
			return getAgregado().getCorrenteProduto_ReferenteA();
		}
		public void addListaProduto_ReferenteA(Produto item) {
			getAgregado().addListaProduto_ReferenteA(item);
		}
		public List<Produto> getListaProduto_ReferenteA() {
			return getAgregado().getListaProduto_ReferenteA();
		}
		public List<Produto> getListaProduto_ReferenteAOriginal() {
			return getAgregado().getListaProduto_ReferenteAOriginal();
		}
		public List<Produto> getListaProduto_ReferenteA(int qtde) {
			return getAgregado().getListaProduto_ReferenteA(qtde);
		}
		public void setListaProduto_ReferenteA(List<Produto> lista) {
			getAgregado().setListaProduto_ReferenteA(lista);
		}
		public void setListaProduto_ReferenteAByDao(List<Produto> lista) {
			getAgregado().setListaProduto_ReferenteAByDao(lista);
		}
		public void criaVaziaListaProduto_ReferenteA() {
			getAgregado().criaVaziaListaProduto_ReferenteA();
		}
		
		public boolean existeListaProduto_ReferenteA() {
			return getAgregado().existeListaProduto_ReferenteA();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(MarcaContract.COLUNA_ID_MARCA, idMarca);
    	valores.put(MarcaContract.COLUNA_NOME_MARCA, nomeMarca);
		return valores;
  	}
}