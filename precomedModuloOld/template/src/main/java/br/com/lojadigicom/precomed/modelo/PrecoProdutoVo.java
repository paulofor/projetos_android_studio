
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
import br.com.lojadigicom.precomed.modelo.agregado.PrecoProdutoAgregado;
import br.com.lojadigicom.precomed.data.contract.PrecoProdutoContract;

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


	private float precoVenda;
	public float getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(float _valor) {
		precoVenda = _valor;
	}
	
	public String getPrecoVendaTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoVenda);
		return saida;
	}

	private Timestamp dataVisitaInicial;
	public Timestamp getDataVisitaInicial() {
		return dataVisitaInicial;
	}
	public void setDataVisitaInicial(Timestamp _valor) {
		dataVisitaInicial = _valor;
	}


	public String getDataVisitaInicialDDMMAAAA() {
		if (dataVisitaInicial==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataVisitaInicial);
    }
    public void setDataVisitaInicialDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataVisitaInicial = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataVisitaInicialDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataVisitaInicial = new Timestamp(date.getTime());
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


	private float precoParcela;
	public float getPrecoParcela() {
		return precoParcela;
	}
	public void setPrecoParcela(float _valor) {
		precoParcela = _valor;
	}
	
	public String getPrecoParcelaTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoParcela);
		return saida;
	}

	private float precoBoleto;
	public float getPrecoBoleto() {
		return precoBoleto;
	}
	public void setPrecoBoleto(float _valor) {
		precoBoleto = _valor;
	}
	
	public String getPrecoBoletoTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoBoleto);
		return saida;
	}

	private float precoRegular;
	public float getPrecoRegular() {
		return precoRegular;
	}
	public void setPrecoRegular(float _valor) {
		precoRegular = _valor;
	}
	
	public String getPrecoRegularTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoRegular);
		return saida;
	}

	private Timestamp dataUltimaVisita;
	public Timestamp getDataUltimaVisita() {
		return dataUltimaVisita;
	}
	public void setDataUltimaVisita(Timestamp _valor) {
		dataUltimaVisita = _valor;
	}


	public String getDataUltimaVisitaDDMMAAAA() {
		if (dataUltimaVisita==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataUltimaVisita);
    }
    public void setDataUltimaVisitaDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaVisita = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataUltimaVisitaDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaVisita = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private float percentualAjuste;
	public float getPercentualAjuste() {
		return percentualAjuste;
	}
	public void setPercentualAjuste(float _valor) {
		percentualAjuste = _valor;
	}
	
	public String getPercentualAjusteTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(percentualAjuste);
		return saida;
	}

	private float precoQuantidadeDesconto;
	public float getPrecoQuantidadeDesconto() {
		return precoQuantidadeDesconto;
	}
	public void setPrecoQuantidadeDesconto(float _valor) {
		precoQuantidadeDesconto = _valor;
	}
	
	public String getPrecoQuantidadeDescontoTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoQuantidadeDesconto);
		return saida;
	}

	private long quantidadeDesconto;
	public long getQuantidadeDesconto() {
		return quantidadeDesconto;
	}
	public void setQuantidadeDesconto(long _valor) {
		quantidadeDesconto = _valor;
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
    	valores.put(PrecoProdutoContract.COLUNA_PRECO_VENDA, precoVenda);
    	valores.put(PrecoProdutoContract.COLUNA_DATA_VISITA_INICIAL, UtilDatas.getDataLong(dataVisitaInicial));
    	valores.put(PrecoProdutoContract.COLUNA_QUANTIDADE_PARCELA, quantidadeParcela);
    	valores.put(PrecoProdutoContract.COLUNA_PRECO_PARCELA, precoParcela);
    	valores.put(PrecoProdutoContract.COLUNA_PRECO_BOLETO, precoBoleto);
    	valores.put(PrecoProdutoContract.COLUNA_PRECO_REGULAR, precoRegular);
    	valores.put(PrecoProdutoContract.COLUNA_DATA_ULTIMA_VISITA, UtilDatas.getDataLong(dataUltimaVisita));
    	valores.put(PrecoProdutoContract.COLUNA_PERCENTUAL_AJUSTE, percentualAjuste);
    	valores.put(PrecoProdutoContract.COLUNA_PRECO_QUANTIDADE_DESCONTO, precoQuantidadeDesconto);
    	valores.put(PrecoProdutoContract.COLUNA_QUANTIDADE_DESCONTO, quantidadeDesconto);
		valores.put(PrecoProdutoContract.COLUNA_ID_PRODUTO_PA, idProdutoPa);
	
		return valores;
  	}
}