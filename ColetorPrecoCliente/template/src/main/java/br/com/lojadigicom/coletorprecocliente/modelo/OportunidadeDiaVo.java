
package br.com.lojadigicom.coletorprecocliente.modelo;


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

import br.com.lojadigicom.coletorprecocliente.framework.util.UtilDatas;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.OportunidadeDiaAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.OportunidadeDiaContract;

public class OportunidadeDiaVo implements OportunidadeDia  {

	public OportunidadeDiaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idOportunidadeDia;
    }

	 // ----- INICIO AGREGADO -----------------
	private OportunidadeDiaAgregado agregado = null;
	private OportunidadeDiaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new OportunidadeDiaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idOportunidadeDia;
	public long getIdOportunidadeDia() {
		return idOportunidadeDia;
	}
	public void setIdOportunidadeDia(long _valor) {
		idOportunidadeDia = _valor;
	}


	private String nomeProduto;
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String _valor) {
		nomeProduto = _valor;
	}


	private String urlProduto;
	public String getUrlProduto() {
		return urlProduto;
	}
	public void setUrlProduto(String _valor) {
		urlProduto = _valor;
	}


	private String urlImagem;
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String _valor) {
		urlImagem = _valor;
	}


	private float precoVendaAtual;
	public float getPrecoVendaAtual() {
		return precoVendaAtual;
	}
	public void setPrecoVendaAtual(float _valor) {
		precoVendaAtual = _valor;
	}
	
	public String getPrecoVendaAtualTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoVendaAtual);
		return saida;
	}

	private float precoVendaAnterior;
	public float getPrecoVendaAnterior() {
		return precoVendaAnterior;
	}
	public void setPrecoVendaAnterior(float _valor) {
		precoVendaAnterior = _valor;
	}
	
	public String getPrecoVendaAnteriorTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoVendaAnterior);
		return saida;
	}

	private float percentualAjusteVenda;
	public float getPercentualAjusteVenda() {
		return percentualAjusteVenda;
	}
	public void setPercentualAjusteVenda(float _valor) {
		percentualAjusteVenda = _valor;
	}
	
	public String getPercentualAjusteVendaTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(percentualAjusteVenda);
		return saida;
	}

	private long quantidadeParcelaAtual;
	public long getQuantidadeParcelaAtual() {
		return quantidadeParcelaAtual;
	}
	public void setQuantidadeParcelaAtual(long _valor) {
		quantidadeParcelaAtual = _valor;
	}


	private float precoParcelaAtual;
	public float getPrecoParcelaAtual() {
		return precoParcelaAtual;
	}
	public void setPrecoParcelaAtual(float _valor) {
		precoParcelaAtual = _valor;
	}
	
	public String getPrecoParcelaAtualTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoParcelaAtual);
		return saida;
	}

	private float precoParcelaAnterior;
	public float getPrecoParcelaAnterior() {
		return precoParcelaAnterior;
	}
	public void setPrecoParcelaAnterior(float _valor) {
		precoParcelaAnterior = _valor;
	}
	
	public String getPrecoParcelaAnteriorTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoParcelaAnterior);
		return saida;
	}

	private long quantidadeParcelaAnterior;
	public long getQuantidadeParcelaAnterior() {
		return quantidadeParcelaAnterior;
	}
	public void setQuantidadeParcelaAnterior(long _valor) {
		quantidadeParcelaAnterior = _valor;
	}


	private String nomeLojaVirtual;
	public String getNomeLojaVirtual() {
		return nomeLojaVirtual;
	}
	public void setNomeLojaVirtual(String _valor) {
		nomeLojaVirtual = _valor;
	}


	private Timestamp dataUltimaPrecoAnterior;
	public Timestamp getDataUltimaPrecoAnterior() {
		return dataUltimaPrecoAnterior;
	}
	public void setDataUltimaPrecoAnterior(Timestamp _valor) {
		dataUltimaPrecoAnterior = _valor;
	}


	public String getDataUltimaPrecoAnteriorDDMMAAAA() {
		if (dataUltimaPrecoAnterior==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataUltimaPrecoAnterior);
    }
    public void setDataUltimaPrecoAnteriorDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaPrecoAnterior = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataUltimaPrecoAnteriorDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaPrecoAnterior = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private Timestamp dataInicioPrecoAtual;
	public Timestamp getDataInicioPrecoAtual() {
		return dataInicioPrecoAtual;
	}
	public void setDataInicioPrecoAtual(Timestamp _valor) {
		dataInicioPrecoAtual = _valor;
	}


	public String getDataInicioPrecoAtualDDMMAAAA() {
		if (dataInicioPrecoAtual==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataInicioPrecoAtual);
    }
    public void setDataInicioPrecoAtualDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInicioPrecoAtual = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataInicioPrecoAtualDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInicioPrecoAtual = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private float precoMedio;
	public float getPrecoMedio() {
		return precoMedio;
	}
	public void setPrecoMedio(float _valor) {
		precoMedio = _valor;
	}
	
	public String getPrecoMedioTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoMedio);
		return saida;
	}

	private float precoMinimo;
	public float getPrecoMinimo() {
		return precoMinimo;
	}
	public void setPrecoMinimo(float _valor) {
		precoMinimo = _valor;
	}
	
	public String getPrecoMinimoTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(precoMinimo);
		return saida;
	}
	
	private long idNaturezaProdutoPa;
	public long getIdNaturezaProdutoPa() {
		// relacionamento
		//if (idNaturezaProdutoPa==0 && this.getNaturezaProduto_PertenceA()!=null) 
		//	return getNaturezaProduto_PertenceA().getId();
		//else
			return idNaturezaProdutoPa;
	}
	public void setIdNaturezaProdutoPa(long _valor) {
		idNaturezaProdutoPa = _valor;
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
  	
	public long getIdNaturezaProdutoPaLazyLoader() {
		return idNaturezaProdutoPa;
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
  	
		public NaturezaProduto getNaturezaProduto_PertenceA() {
			return getAgregado().getNaturezaProduto_PertenceA();
		}
		public void setNaturezaProduto_PertenceA(NaturezaProduto item) {
			getAgregado().setNaturezaProduto_PertenceA(item);
		}
		
		public void addListaNaturezaProduto_PertenceA(NaturezaProduto item) {
			getAgregado().addListaNaturezaProduto_PertenceA(item);
		}
		public NaturezaProduto getCorrenteNaturezaProduto_PertenceA() {
			return getAgregado().getCorrenteNaturezaProduto_PertenceA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(OportunidadeDiaContract.COLUNA_ID_OPORTUNIDADE_DIA, idOportunidadeDia);
    	valores.put(OportunidadeDiaContract.COLUNA_NOME_PRODUTO, nomeProduto);
    	valores.put(OportunidadeDiaContract.COLUNA_URL_PRODUTO, urlProduto);
    	valores.put(OportunidadeDiaContract.COLUNA_URL_IMAGEM, urlImagem);
    	valores.put(OportunidadeDiaContract.COLUNA_PRECO_VENDA_ATUAL, precoVendaAtual);
    	valores.put(OportunidadeDiaContract.COLUNA_PRECO_VENDA_ANTERIOR, precoVendaAnterior);
    	valores.put(OportunidadeDiaContract.COLUNA_PERCENTUAL_AJUSTE_VENDA, percentualAjusteVenda);
    	valores.put(OportunidadeDiaContract.COLUNA_QUANTIDADE_PARCELA_ATUAL, quantidadeParcelaAtual);
    	valores.put(OportunidadeDiaContract.COLUNA_PRECO_PARCELA_ATUAL, precoParcelaAtual);
    	valores.put(OportunidadeDiaContract.COLUNA_PRECO_PARCELA_ANTERIOR, precoParcelaAnterior);
    	valores.put(OportunidadeDiaContract.COLUNA_QUANTIDADE_PARCELA_ANTERIOR, quantidadeParcelaAnterior);
    	valores.put(OportunidadeDiaContract.COLUNA_NOME_LOJA_VIRTUAL, nomeLojaVirtual);
    	valores.put(OportunidadeDiaContract.COLUNA_DATA_ULTIMA_PRECO_ANTERIOR, UtilDatas.getDataLong(dataUltimaPrecoAnterior));
    	valores.put(OportunidadeDiaContract.COLUNA_DATA_INICIO_PRECO_ATUAL, UtilDatas.getDataLong(dataInicioPrecoAtual));
    	valores.put(OportunidadeDiaContract.COLUNA_PRECO_MEDIO, precoMedio);
    	valores.put(OportunidadeDiaContract.COLUNA_PRECO_MINIMO, precoMinimo);
		valores.put(OportunidadeDiaContract.COLUNA_ID_NATUREZA_PRODUTO_PA, idNaturezaProdutoPa);
	
		return valores;
  	}
}