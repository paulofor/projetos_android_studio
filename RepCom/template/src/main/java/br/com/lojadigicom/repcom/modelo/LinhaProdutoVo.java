
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
import br.com.lojadigicom.repcom.modelo.agregado.LinhaProdutoAgregado;
import br.com.lojadigicom.repcom.data.contract.LinhaProdutoContract;

public class LinhaProdutoVo implements LinhaProduto  {

	public LinhaProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idLinhaProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private LinhaProdutoAgregado agregado = null;
	private LinhaProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new LinhaProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idLinhaProduto;
	public long getIdLinhaProduto() {
		return idLinhaProduto;
	}
	public void setIdLinhaProduto(long _valor) {
		idLinhaProduto = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}


	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String _valor) {
		url = _valor;
	}


	private long codigoLineId;
	public long getCodigoLineId() {
		return codigoLineId;
	}
	public void setCodigoLineId(long _valor) {
		codigoLineId = _valor;
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
	
		public Produto getCorrenteProduto_Possui() {
			return getAgregado().getCorrenteProduto_Possui();
		}
		public void addListaProduto_Possui(Produto item) {
			getAgregado().addListaProduto_Possui(item);
		}
		public List<Produto> getListaProduto_Possui() {
			return getAgregado().getListaProduto_Possui();
		}
		public List<Produto> getListaProduto_PossuiOriginal() {
			return getAgregado().getListaProduto_PossuiOriginal();
		}
		public List<Produto> getListaProduto_Possui(int qtde) {
			return getAgregado().getListaProduto_Possui(qtde);
		}
		public void setListaProduto_Possui(List<Produto> lista) {
			getAgregado().setListaProduto_Possui(lista);
		}
		public void setListaProduto_PossuiByDao(List<Produto> lista) {
			getAgregado().setListaProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaProduto_Possui() {
			getAgregado().criaVaziaListaProduto_Possui();
		}
		
		public boolean existeListaProduto_Possui() {
			return getAgregado().existeListaProduto_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(LinhaProdutoContract.COLUNA_ID_LINHA_PRODUTO, idLinhaProduto);
    	valores.put(LinhaProdutoContract.COLUNA_NOME, nome);
    	valores.put(LinhaProdutoContract.COLUNA_URL, url);
    	valores.put(LinhaProdutoContract.COLUNA_CODIGO_LINE_ID, codigoLineId);
    	valores.put(LinhaProdutoContract.COLUNA_DATA_INCLUSAO, UtilDatas.getDataLong(dataInclusao));
		return valores;
  	}
}