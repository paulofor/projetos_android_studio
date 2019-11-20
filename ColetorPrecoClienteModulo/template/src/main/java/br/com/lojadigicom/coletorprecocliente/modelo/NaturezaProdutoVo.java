
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
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.NaturezaProdutoAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;

public class NaturezaProdutoVo implements NaturezaProduto  {

	public NaturezaProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idNaturezaProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private NaturezaProdutoAgregado agregado = null;
	private NaturezaProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new NaturezaProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	private long QtdeOportunidadeDia;
	public long getQtdeOportunidadeDia() {
		return QtdeOportunidadeDia;
	}
	public void setQtdeOportunidadeDia(long _valor) {
		QtdeOportunidadeDia = _valor;
	}
	
	private boolean Ativo;
	public boolean getAtivo() {
		return Ativo;
	}
	public void setAtivo(boolean _valor) {
		Ativo = _valor;
	}
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idNaturezaProduto;
	public long getIdNaturezaProduto() {
		return idNaturezaProduto;
	}
	public void setIdNaturezaProduto(long _valor) {
		idNaturezaProduto = _valor;
	}


	private String nomeNaturezaProduto;
	public String getNomeNaturezaProduto() {
		return nomeNaturezaProduto;
	}
	public void setNomeNaturezaProduto(String _valor) {
		nomeNaturezaProduto = _valor;
	}

	
	private long idAppProdutoAp;
	public long getIdAppProdutoAp() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getAppProduto_AtendidoPor()!=null) 
		//	return getAppProduto_AtendidoPor().getIdObj();
		//else
			return idAppProdutoAp;
	}
	public void setIdAppProdutoAp(long _valor) {
		idAppProdutoAp = _valor;
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
  	
	public long getIdAppProdutoApLazyLoader() {
		return idAppProdutoAp;
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
  	
		public AppProduto getAppProduto_AtendidoPor() {
			return getAgregado().getAppProduto_AtendidoPor();
		}
		public void setAppProduto_AtendidoPor(AppProduto item) {
			// Coloquei em 10-11-2016
			idAppProdutoAp = item.getIdObj();
			getAgregado().setAppProduto_AtendidoPor(item);
		}
		
		public void addListaAppProduto_AtendidoPor(AppProduto item) {
			getAgregado().addListaAppProduto_AtendidoPor(item);
		}
		public AppProduto getCorrenteAppProduto_AtendidoPor() {
			return getAgregado().getCorrenteAppProduto_AtendidoPor();
		}
		
		
	
	// SemChaveEstrangeira
	
		public OportunidadeDia getCorrenteOportunidadeDia_Possui() {
			return getAgregado().getCorrenteOportunidadeDia_Possui();
		}
		public void addListaOportunidadeDia_Possui(OportunidadeDia item) {
			getAgregado().addListaOportunidadeDia_Possui(item);
		}
		public List<OportunidadeDia> getListaOportunidadeDia_Possui() {
			return getAgregado().getListaOportunidadeDia_Possui();
		}
		public List<OportunidadeDia> getListaOportunidadeDia_PossuiOriginal() {
			return getAgregado().getListaOportunidadeDia_PossuiOriginal();
		}
		public List<OportunidadeDia> getListaOportunidadeDia_Possui(int qtde) {
			return getAgregado().getListaOportunidadeDia_Possui(qtde);
		}
		public void setListaOportunidadeDia_Possui(List<OportunidadeDia> lista) {
			getAgregado().setListaOportunidadeDia_Possui(lista);
		}
		public void setListaOportunidadeDia_PossuiByDao(List<OportunidadeDia> lista) {
			getAgregado().setListaOportunidadeDia_PossuiByDao(lista);
		}
		public void criaVaziaListaOportunidadeDia_Possui() {
			getAgregado().criaVaziaListaOportunidadeDia_Possui();
		}
		
		public boolean existeListaOportunidadeDia_Possui() {
			return getAgregado().existeListaOportunidadeDia_Possui();
		}
 		
		public UsuarioPesquisa getCorrenteUsuarioPesquisa_PesquisadoPor() {
			return getAgregado().getCorrenteUsuarioPesquisa_PesquisadoPor();
		}
		public void addListaUsuarioPesquisa_PesquisadoPor(UsuarioPesquisa item) {
			getAgregado().addListaUsuarioPesquisa_PesquisadoPor(item);
		}
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor() {
			return getAgregado().getListaUsuarioPesquisa_PesquisadoPor();
		}
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPorOriginal() {
			return getAgregado().getListaUsuarioPesquisa_PesquisadoPorOriginal();
		}
		public List<UsuarioPesquisa> getListaUsuarioPesquisa_PesquisadoPor(int qtde) {
			return getAgregado().getListaUsuarioPesquisa_PesquisadoPor(qtde);
		}
		public void setListaUsuarioPesquisa_PesquisadoPor(List<UsuarioPesquisa> lista) {
			getAgregado().setListaUsuarioPesquisa_PesquisadoPor(lista);
		}
		public void setListaUsuarioPesquisa_PesquisadoPorByDao(List<UsuarioPesquisa> lista) {
			getAgregado().setListaUsuarioPesquisa_PesquisadoPorByDao(lista);
		}
		public void criaVaziaListaUsuarioPesquisa_PesquisadoPor() {
			getAgregado().criaVaziaListaUsuarioPesquisa_PesquisadoPor();
		}
		
		public boolean existeListaUsuarioPesquisa_PesquisadoPor() {
			return getAgregado().existeListaUsuarioPesquisa_PesquisadoPor();
		}
 		
		public PalavraChavePesquisa getCorrentePalavraChavePesquisa_Possui() {
			return getAgregado().getCorrentePalavraChavePesquisa_Possui();
		}
		public void addListaPalavraChavePesquisa_Possui(PalavraChavePesquisa item) {
			getAgregado().addListaPalavraChavePesquisa_Possui(item);
		}
		public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_Possui() {
			return getAgregado().getListaPalavraChavePesquisa_Possui();
		}
		public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_PossuiOriginal() {
			return getAgregado().getListaPalavraChavePesquisa_PossuiOriginal();
		}
		public List<PalavraChavePesquisa> getListaPalavraChavePesquisa_Possui(int qtde) {
			return getAgregado().getListaPalavraChavePesquisa_Possui(qtde);
		}
		public void setListaPalavraChavePesquisa_Possui(List<PalavraChavePesquisa> lista) {
			getAgregado().setListaPalavraChavePesquisa_Possui(lista);
		}
		public void setListaPalavraChavePesquisa_PossuiByDao(List<PalavraChavePesquisa> lista) {
			getAgregado().setListaPalavraChavePesquisa_PossuiByDao(lista);
		}
		public void criaVaziaListaPalavraChavePesquisa_Possui() {
			getAgregado().criaVaziaListaPalavraChavePesquisa_Possui();
		}
		
		public boolean existeListaPalavraChavePesquisa_Possui() {
			return getAgregado().existeListaPalavraChavePesquisa_Possui();
		}
 		
		public ProdutoCliente getCorrenteProdutoCliente_Possui() {
			return getAgregado().getCorrenteProdutoCliente_Possui();
		}
		public void addListaProdutoCliente_Possui(ProdutoCliente item) {
			getAgregado().addListaProdutoCliente_Possui(item);
		}
		public List<ProdutoCliente> getListaProdutoCliente_Possui() {
			return getAgregado().getListaProdutoCliente_Possui();
		}
		public List<ProdutoCliente> getListaProdutoCliente_PossuiOriginal() {
			return getAgregado().getListaProdutoCliente_PossuiOriginal();
		}
		public List<ProdutoCliente> getListaProdutoCliente_Possui(int qtde) {
			return getAgregado().getListaProdutoCliente_Possui(qtde);
		}
		public void setListaProdutoCliente_Possui(List<ProdutoCliente> lista) {
			getAgregado().setListaProdutoCliente_Possui(lista);
		}
		public void setListaProdutoCliente_PossuiByDao(List<ProdutoCliente> lista) {
			getAgregado().setListaProdutoCliente_PossuiByDao(lista);
		}
		public void criaVaziaListaProdutoCliente_Possui() {
			getAgregado().criaVaziaListaProdutoCliente_Possui();
		}
		
		public boolean existeListaProdutoCliente_Possui() {
			return getAgregado().existeListaProdutoCliente_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(NaturezaProdutoContract.COLUNA_ID_NATUREZA_PRODUTO, idNaturezaProduto);
    	valores.put(NaturezaProdutoContract.COLUNA_NOME_NATUREZA_PRODUTO, nomeNaturezaProduto);
		valores.put(NaturezaProdutoContract.COLUNA_ID_APP_PRODUTO_AP, idAppProdutoAp);
	
		return valores;
  	}
}