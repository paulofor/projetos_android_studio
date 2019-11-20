
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
import br.com.lojadigicom.repcom.modelo.agregado.PrecoProdutoAgregado;
import br.com.lojadigicom.repcom.data.contract.PrecoProdutoContract;

public class PrecoProdutoVo implements PrecoProduto  {

	public PrecoProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idPrecoProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private PrecoProdutoAgregado agregado = null;
	private PrecoProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new PrecoProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idPrecoProduto;
	public long getIdPrecoProduto() {
		return idPrecoProduto;
	}
	public void setIdPrecoProduto(long _valor) {
		idPrecoProduto = _valor;
	}


	private float valorPrecoAvista;
	public float getValorPrecoAvista() {
		return valorPrecoAvista;
	}
	public void setValorPrecoAvista(float _valor) {
		valorPrecoAvista = _valor;
	}
	
	public String getValorPrecoAvistaTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valorPrecoAvista);
		return saida;
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




	private long quantidadeParcela;
	public long getQuantidadeParcela() {
		return quantidadeParcela;
	}
	public void setQuantidadeParcela(long _valor) {
		quantidadeParcela = _valor;
	}


	private float valorParcela;
	public float getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(float _valor) {
		valorParcela = _valor;
	}
	
	public String getValorParcelaTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valorParcela);
		return saida;
	}

	private Timestamp dataExclusao;
	public Timestamp getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(Timestamp _valor) {
		dataExclusao = _valor;
	}


	public String getDataExclusaoDDMMAAAA() {
		if (dataExclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataExclusao);
    }
    public void setDataExclusaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataExclusao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataExclusaoDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataExclusao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }



	
	private long idProdutoPa;
	public long getIdProdutoPa() {
		// relacionamento
		//if (idProdutoPa==0 && this.getProduto_PertenceA()!=null) 
		//	return getProduto_PertenceA().getId();
		//else
			return idProdutoPa;
	}
	public void setIdProdutoPa(long _valor) {
		idProdutoPa = _valor;
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
  	
	public long getIdProdutoPaLazyLoader() {
		return idProdutoPa;
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
  	
		public Produto getProduto_PertenceA() {
			return getAgregado().getProduto_PertenceA();
		}
		public void setProduto_PertenceA(Produto item) {
			getAgregado().setProduto_PertenceA(item);
		}
		
		public void addListaProduto_PertenceA(Produto item) {
			getAgregado().addListaProduto_PertenceA(item);
		}
		public Produto getCorrenteProduto_PertenceA() {
			return getAgregado().getCorrenteProduto_PertenceA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(PrecoProdutoContract.COLUNA_ID_PRECO_PRODUTO, idPrecoProduto);
    	valores.put(PrecoProdutoContract.COLUNA_VALOR_PRECO_AVISTA, valorPrecoAvista);
    	valores.put(PrecoProdutoContract.COLUNA_DATA_INCLUSAO, UtilDatas.getDataLong(dataInclusao));
    	valores.put(PrecoProdutoContract.COLUNA_QUANTIDADE_PARCELA, quantidadeParcela);
    	valores.put(PrecoProdutoContract.COLUNA_VALOR_PARCELA, valorParcela);
    	valores.put(PrecoProdutoContract.COLUNA_DATA_EXCLUSAO, UtilDatas.getDataLong(dataExclusao));
		valores.put(PrecoProdutoContract.COLUNA_ID_PRODUTO_PA, idProdutoPa);
	
		return valores;
  	}
}