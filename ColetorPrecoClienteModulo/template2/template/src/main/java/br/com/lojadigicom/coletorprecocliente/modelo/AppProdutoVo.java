
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
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.AppProdutoAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.AppProdutoContract;

public class AppProdutoVo implements AppProduto  {

	public AppProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idAppProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private AppProdutoAgregado agregado = null;
	private AppProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new AppProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idAppProduto;
	public long getIdAppProduto() {
		return idAppProduto;
	}
	public void setIdAppProduto(long _valor) {
		idAppProduto = _valor;
	}


	private String nome;
	public String getNome() {
		return nome;
	}
	public void setNome(String _valor) {
		nome = _valor;
	}


	private String urlInstalacao;
	public String getUrlInstalacao() {
		return urlInstalacao;
	}
	public void setUrlInstalacao(String _valor) {
		urlInstalacao = _valor;
	}


	private boolean possuiVitrine;
	public boolean getPossuiVitrine() {
		return possuiVitrine;
	}
	public void setPossuiVitrine(boolean _valor) {
		possuiVitrine = _valor;
	}


	private boolean ativo;
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean _valor) {
		ativo = _valor;
	}


	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String _valor) {
		status = _valor;
	}


	private long limitePosicionador;
	public long getLimitePosicionador() {
		return limitePosicionador;
	}
	public void setLimitePosicionador(long _valor) {
		limitePosicionador = _valor;
	}


	private boolean possuiPalavraChave;
	public boolean getPossuiPalavraChave() {
		return possuiPalavraChave;
	}
	public void setPossuiPalavraChave(boolean _valor) {
		possuiPalavraChave = _valor;
	}


	private String codigoHash;
	public String getCodigoHash() {
		return codigoHash;
	}
	public void setCodigoHash(String _valor) {
		codigoHash = _valor;
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
	
		public NaturezaProduto getCorrenteNaturezaProduto_Atende() {
			return getAgregado().getCorrenteNaturezaProduto_Atende();
		}
		public void addListaNaturezaProduto_Atende(NaturezaProduto item) {
			getAgregado().addListaNaturezaProduto_Atende(item);
		}
		public List<NaturezaProduto> getListaNaturezaProduto_Atende() {
			return getAgregado().getListaNaturezaProduto_Atende();
		}
		public List<NaturezaProduto> getListaNaturezaProduto_AtendeOriginal() {
			return getAgregado().getListaNaturezaProduto_AtendeOriginal();
		}
		public List<NaturezaProduto> getListaNaturezaProduto_Atende(int qtde) {
			return getAgregado().getListaNaturezaProduto_Atende(qtde);
		}
		public void setListaNaturezaProduto_Atende(List<NaturezaProduto> lista) {
			getAgregado().setListaNaturezaProduto_Atende(lista);
		}
		public void setListaNaturezaProduto_AtendeByDao(List<NaturezaProduto> lista) {
			getAgregado().setListaNaturezaProduto_AtendeByDao(lista);
		}
		public void criaVaziaListaNaturezaProduto_Atende() {
			getAgregado().criaVaziaListaNaturezaProduto_Atende();
		}
		
		public boolean existeListaNaturezaProduto_Atende() {
			return getAgregado().existeListaNaturezaProduto_Atende();
		}
 		
		public DispositivoUsuario getCorrenteDispositivoUsuario_UsadoPor() {
			return getAgregado().getCorrenteDispositivoUsuario_UsadoPor();
		}
		public void addListaDispositivoUsuario_UsadoPor(DispositivoUsuario item) {
			getAgregado().addListaDispositivoUsuario_UsadoPor(item);
		}
		public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPor() {
			return getAgregado().getListaDispositivoUsuario_UsadoPor();
		}
		public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPorOriginal() {
			return getAgregado().getListaDispositivoUsuario_UsadoPorOriginal();
		}
		public List<DispositivoUsuario> getListaDispositivoUsuario_UsadoPor(int qtde) {
			return getAgregado().getListaDispositivoUsuario_UsadoPor(qtde);
		}
		public void setListaDispositivoUsuario_UsadoPor(List<DispositivoUsuario> lista) {
			getAgregado().setListaDispositivoUsuario_UsadoPor(lista);
		}
		public void setListaDispositivoUsuario_UsadoPorByDao(List<DispositivoUsuario> lista) {
			getAgregado().setListaDispositivoUsuario_UsadoPorByDao(lista);
		}
		public void criaVaziaListaDispositivoUsuario_UsadoPor() {
			getAgregado().criaVaziaListaDispositivoUsuario_UsadoPor();
		}
		
		public boolean existeListaDispositivoUsuario_UsadoPor() {
			return getAgregado().existeListaDispositivoUsuario_UsadoPor();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(AppProdutoContract.COLUNA_ID_APP_PRODUTO, idAppProduto);
    	valores.put(AppProdutoContract.COLUNA_NOME, nome);
    	valores.put(AppProdutoContract.COLUNA_URL_INSTALACAO, urlInstalacao);
    	valores.put(AppProdutoContract.COLUNA_POSSUI_VITRINE, possuiVitrine);
    	valores.put(AppProdutoContract.COLUNA_ATIVO, ativo);
    	valores.put(AppProdutoContract.COLUNA_STATUS, status);
    	valores.put(AppProdutoContract.COLUNA_LIMITE_POSICIONADOR, limitePosicionador);
    	valores.put(AppProdutoContract.COLUNA_POSSUI_PALAVRA_CHAVE, possuiPalavraChave);
    	valores.put(AppProdutoContract.COLUNA_CODIGO_HASH, codigoHash);
		return valores;
  	}
}