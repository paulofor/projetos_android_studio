
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
import br.com.lojadigicom.precomed.modelo.agregado.ProdutoPesquisaAgregado;
import br.com.lojadigicom.precomed.data.contract.ProdutoPesquisaContract;

public class ProdutoPesquisaVo implements ProdutoPesquisa  {

	public ProdutoPesquisaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idProdutoPesquisa;
    }

	 // ----- INICIO AGREGADO -----------------
	private ProdutoPesquisaAgregado agregado = null;
	private ProdutoPesquisaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ProdutoPesquisaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idProdutoPesquisa;
	public long getIdProdutoPesquisa() {
		return idProdutoPesquisa;
	}
	public void setIdProdutoPesquisa(long _valor) {
		idProdutoPesquisa = _valor;
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
	private boolean ativo;
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean _valor) {
		ativo = _valor;
	}


	private String nomeProdutoPesquisa;
	public String getNomeProdutoPesquisa() {
		return nomeProdutoPesquisa;
	}
	public void setNomeProdutoPesquisa(String _valor) {
		nomeProdutoPesquisa = _valor;
	}

	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getUsuario_Sincroniza()!=null) 
		//	return getUsuario_Sincroniza().getIdObj();
		//else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
	}

	
	private long idModeloProdutoRa;
	public long getIdModeloProdutoRa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getModeloProduto_ReferenteA()!=null) 
		//	return getModeloProduto_ReferenteA().getIdObj();
		//else
			return idModeloProdutoRa;
	}
	public void setIdModeloProdutoRa(long _valor) {
		idModeloProdutoRa = _valor;
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
  	
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
	public long getIdModeloProdutoRaLazyLoader() {
		return idModeloProdutoRa;
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
  	
		public Usuario getUsuario_Sincroniza() {
			return getAgregado().getUsuario_Sincroniza();
		}
		public void setUsuario_Sincroniza(Usuario item) {
			// Coloquei em 10-11-2016
			idUsuarioS = item.getIdObj();
			getAgregado().setUsuario_Sincroniza(item);
		}
		
		public void addListaUsuario_Sincroniza(Usuario item) {
			getAgregado().addListaUsuario_Sincroniza(item);
		}
		public Usuario getCorrenteUsuario_Sincroniza() {
			return getAgregado().getCorrenteUsuario_Sincroniza();
		}
		
		
		public ModeloProduto getModeloProduto_ReferenteA() {
			return getAgregado().getModeloProduto_ReferenteA();
		}
		public void setModeloProduto_ReferenteA(ModeloProduto item) {
			// Coloquei em 10-11-2016
			idModeloProdutoRa = item.getIdObj();
			getAgregado().setModeloProduto_ReferenteA(item);
		}
		
		public void addListaModeloProduto_ReferenteA(ModeloProduto item) {
			getAgregado().addListaModeloProduto_ReferenteA(item);
		}
		public ModeloProduto getCorrenteModeloProduto_ReferenteA() {
			return getAgregado().getCorrenteModeloProduto_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ProdutoPesquisaContract.COLUNA_ID_PRODUTO_PESQUISA, idProdutoPesquisa);
    	valores.put(ProdutoPesquisaContract.COLUNA_DATA_INCLUSAO, UtilDatas.getDataHoraLong(dataInclusao));
    	valores.put(ProdutoPesquisaContract.COLUNA_ATIVO, ativo);
    	valores.put(ProdutoPesquisaContract.COLUNA_NOME_PRODUTO_PESQUISA, nomeProdutoPesquisa);
		valores.put(ProdutoPesquisaContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		valores.put(ProdutoPesquisaContract.COLUNA_ID_MODELO_PRODUTO_RA, idModeloProdutoRa);
	
		return valores;
  	}
}