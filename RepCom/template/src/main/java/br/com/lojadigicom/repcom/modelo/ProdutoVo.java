
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
import br.com.lojadigicom.repcom.modelo.agregado.ProdutoAgregado;
import br.com.lojadigicom.repcom.data.contract.ProdutoContract;

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


	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String _valor) {
		url = _valor;
	}


	private String imagem;
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String _valor) {
		imagem = _valor;
	}


	private long tamanhoImagem;
	public long getTamanhoImagem() {
		return tamanhoImagem;
	}
	public void setTamanhoImagem(long _valor) {
		tamanhoImagem = _valor;
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



	
	private long idLinhaProdutoEe;
	public long getIdLinhaProdutoEe() {
		// relacionamento
		//if (idLinhaProdutoEe==0 && this.getLinhaProduto_EstaEm()!=null) 
		//	return getLinhaProduto_EstaEm().getId();
		//else
			return idLinhaProdutoEe;
	}
	public void setIdLinhaProdutoEe(long _valor) {
		idLinhaProdutoEe = _valor;
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
  	
	public long getIdLinhaProdutoEeLazyLoader() {
		return idLinhaProdutoEe;
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
  	
		public LinhaProduto getLinhaProduto_EstaEm() {
			return getAgregado().getLinhaProduto_EstaEm();
		}
		public void setLinhaProduto_EstaEm(LinhaProduto item) {
			getAgregado().setLinhaProduto_EstaEm(item);
		}
		
		public void addListaLinhaProduto_EstaEm(LinhaProduto item) {
			getAgregado().addListaLinhaProduto_EstaEm(item);
		}
		public LinhaProduto getCorrenteLinhaProduto_EstaEm() {
			return getAgregado().getCorrenteLinhaProduto_EstaEm();
		}
		
		
	
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
 		
		public ProdutoVenda getCorrenteProdutoVenda_Associada() {
			return getAgregado().getCorrenteProdutoVenda_Associada();
		}
		public void addListaProdutoVenda_Associada(ProdutoVenda item) {
			getAgregado().addListaProdutoVenda_Associada(item);
		}
		public List<ProdutoVenda> getListaProdutoVenda_Associada() {
			return getAgregado().getListaProdutoVenda_Associada();
		}
		public List<ProdutoVenda> getListaProdutoVenda_AssociadaOriginal() {
			return getAgregado().getListaProdutoVenda_AssociadaOriginal();
		}
		public List<ProdutoVenda> getListaProdutoVenda_Associada(int qtde) {
			return getAgregado().getListaProdutoVenda_Associada(qtde);
		}
		public void setListaProdutoVenda_Associada(List<ProdutoVenda> lista) {
			getAgregado().setListaProdutoVenda_Associada(lista);
		}
		public void setListaProdutoVenda_AssociadaByDao(List<ProdutoVenda> lista) {
			getAgregado().setListaProdutoVenda_AssociadaByDao(lista);
		}
		public void criaVaziaListaProdutoVenda_Associada() {
			getAgregado().criaVaziaListaProdutoVenda_Associada();
		}
		
		public boolean existeListaProdutoVenda_Associada() {
			return getAgregado().existeListaProdutoVenda_Associada();
		}
 		
		public PrecoProduto getCorrentePrecoProduto_Possui() {
			return getAgregado().getCorrentePrecoProduto_Possui();
		}
		public void addListaPrecoProduto_Possui(PrecoProduto item) {
			getAgregado().addListaPrecoProduto_Possui(item);
		}
		public List<PrecoProduto> getListaPrecoProduto_Possui() {
			return getAgregado().getListaPrecoProduto_Possui();
		}
		public List<PrecoProduto> getListaPrecoProduto_PossuiOriginal() {
			return getAgregado().getListaPrecoProduto_PossuiOriginal();
		}
		public List<PrecoProduto> getListaPrecoProduto_Possui(int qtde) {
			return getAgregado().getListaPrecoProduto_Possui(qtde);
		}
		public void setListaPrecoProduto_Possui(List<PrecoProduto> lista) {
			getAgregado().setListaPrecoProduto_Possui(lista);
		}
		public void setListaPrecoProduto_PossuiByDao(List<PrecoProduto> lista) {
			getAgregado().setListaPrecoProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaPrecoProduto_Possui() {
			getAgregado().criaVaziaListaPrecoProduto_Possui();
		}
		
		public boolean existeListaPrecoProduto_Possui() {
			return getAgregado().existeListaPrecoProduto_Possui();
		}
 		
		public CategoriaProdutoProduto getCorrenteCategoriaProdutoProduto_Possui() {
			return getAgregado().getCorrenteCategoriaProdutoProduto_Possui();
		}
		public void addListaCategoriaProdutoProduto_Possui(CategoriaProdutoProduto item) {
			getAgregado().addListaCategoriaProdutoProduto_Possui(item);
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui() {
			return getAgregado().getListaCategoriaProdutoProduto_Possui();
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_PossuiOriginal() {
			return getAgregado().getListaCategoriaProdutoProduto_PossuiOriginal();
		}
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui(int qtde) {
			return getAgregado().getListaCategoriaProdutoProduto_Possui(qtde);
		}
		public void setListaCategoriaProdutoProduto_Possui(List<CategoriaProdutoProduto> lista) {
			getAgregado().setListaCategoriaProdutoProduto_Possui(lista);
		}
		public void setListaCategoriaProdutoProduto_PossuiByDao(List<CategoriaProdutoProduto> lista) {
			getAgregado().setListaCategoriaProdutoProduto_PossuiByDao(lista);
		}
		public void criaVaziaListaCategoriaProdutoProduto_Possui() {
			getAgregado().criaVaziaListaCategoriaProdutoProduto_Possui();
		}
		
		public boolean existeListaCategoriaProdutoProduto_Possui() {
			return getAgregado().existeListaCategoriaProdutoProduto_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ProdutoContract.COLUNA_ID_PRODUTO, idProduto);
    	valores.put(ProdutoContract.COLUNA_NOME, nome);
    	valores.put(ProdutoContract.COLUNA_URL, url);
    	valores.put(ProdutoContract.COLUNA_IMAGEM, imagem);
    	valores.put(ProdutoContract.COLUNA_TAMANHO_IMAGEM, tamanhoImagem);
    	valores.put(ProdutoContract.COLUNA_DATA_INCLUSAO, UtilDatas.getDataLong(dataInclusao));
    	valores.put(ProdutoContract.COLUNA_DATA_EXCLUSAO, UtilDatas.getDataLong(dataExclusao));
		valores.put(ProdutoContract.COLUNA_ID_LINHA_PRODUTO_EE, idLinhaProdutoEe);
	
		return valores;
  	}
}