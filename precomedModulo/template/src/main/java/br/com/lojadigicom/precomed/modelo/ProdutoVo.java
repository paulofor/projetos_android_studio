
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
import br.com.lojadigicom.precomed.modelo.agregado.ProdutoAgregado;
import br.com.lojadigicom.precomed.data.contract.ProdutoContract;

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


	private String urlOrigem;
	public String getUrlOrigem() {
		return urlOrigem;
	}
	public void setUrlOrigem(String _valor) {
		urlOrigem = _valor;
	}


	private String imagemLocal;
	public String getImagemLocal() {
		return imagemLocal;
	}
	public void setImagemLocal(String _valor) {
		imagemLocal = _valor;
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




	public String getDataInclusaoHHMM() {
		if (dataInclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataInclusao);
    }
    public String getDataInclusaoHHMMSS() {
		if (dataInclusao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataInclusao);
    }
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String _valor) {
		url = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}


	private long posicaoProduto;
	public long getPosicaoProduto() {
		return posicaoProduto;
	}
	public void setPosicaoProduto(long _valor) {
		posicaoProduto = _valor;
	}


	private String imagem;
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String _valor) {
		imagem = _valor;
	}


	private String codigoMs;
	public String getCodigoMs() {
		return codigoMs;
	}
	public void setCodigoMs(String _valor) {
		codigoMs = _valor;
	}


	private String principioAtivo;
	public String getPrincipioAtivo() {
		return principioAtivo;
	}
	public void setPrincipioAtivo(String _valor) {
		principioAtivo = _valor;
	}


	private boolean possuiEstoque;
	public boolean getPossuiEstoque() {
		return possuiEstoque;
	}
	public void setPossuiEstoque(boolean _valor) {
		possuiEstoque = _valor;
	}

	
	private long idLojaVirtualLe;
	public long getIdLojaVirtualLe() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getLojaVirtual_LidoEm()!=null) 
		//	return getLojaVirtual_LidoEm().getIdObj();
		//else
			return idLojaVirtualLe;
	}
	public void setIdLojaVirtualLe(long _valor) {
		idLojaVirtualLe = _valor;
	}

	
	private long idMarcaP;
	public long getIdMarcaP() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getMarca_Possui()!=null) 
		//	return getMarca_Possui().getIdObj();
		//else
			return idMarcaP;
	}
	public void setIdMarcaP(long _valor) {
		idMarcaP = _valor;
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
  	
	public long getIdLojaVirtualLeLazyLoader() {
		return idLojaVirtualLe;
	} 
		
	public long getIdMarcaPLazyLoader() {
		return idMarcaP;
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
  	
		public LojaVirtual getLojaVirtual_LidoEm() {
			return getAgregado().getLojaVirtual_LidoEm();
		}
		public void setLojaVirtual_LidoEm(LojaVirtual item) {
			// Coloquei em 10-11-2016
			idLojaVirtualLe = item.getIdObj();
			getAgregado().setLojaVirtual_LidoEm(item);
		}
		
		public void addListaLojaVirtual_LidoEm(LojaVirtual item) {
			getAgregado().addListaLojaVirtual_LidoEm(item);
		}
		public LojaVirtual getCorrenteLojaVirtual_LidoEm() {
			return getAgregado().getCorrenteLojaVirtual_LidoEm();
		}
		
		
		public Marca getMarca_Possui() {
			return getAgregado().getMarca_Possui();
		}
		public void setMarca_Possui(Marca item) {
			// Coloquei em 10-11-2016
			idMarcaP = item.getIdObj();
			getAgregado().setMarca_Possui(item);
		}
		
		public void addListaMarca_Possui(Marca item) {
			getAgregado().addListaMarca_Possui(item);
		}
		public Marca getCorrenteMarca_Possui() {
			return getAgregado().getCorrenteMarca_Possui();
		}
		
		
	
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
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ProdutoContract.COLUNA_ID_PRODUTO, idProduto);
    	valores.put(ProdutoContract.COLUNA_URL_ORIGEM, urlOrigem);
    	valores.put(ProdutoContract.COLUNA_IMAGEM_LOCAL, imagemLocal);
    	valores.put(ProdutoContract.COLUNA_DATA_INCLUSAO, UtilDatas.getDataHoraLong(dataInclusao));
    	valores.put(ProdutoContract.COLUNA_URL, url);
    	valores.put(ProdutoContract.COLUNA_NOME, nome);
    	valores.put(ProdutoContract.COLUNA_POSICAO_PRODUTO, posicaoProduto);
    	valores.put(ProdutoContract.COLUNA_IMAGEM, imagem);
    	valores.put(ProdutoContract.COLUNA_CODIGO_MS, codigoMs);
    	valores.put(ProdutoContract.COLUNA_PRINCIPIO_ATIVO, principioAtivo);
    	valores.put(ProdutoContract.COLUNA_POSSUI_ESTOQUE, possuiEstoque);
		valores.put(ProdutoContract.COLUNA_ID_LOJA_VIRTUAL_LE, idLojaVirtualLe);
	
		valores.put(ProdutoContract.COLUNA_ID_MARCA_P, idMarcaP);
	
		return valores;
  	}
}