
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
import br.com.lojadigicom.repcom.modelo.agregado.CategoriaProdutoAgregado;
import br.com.lojadigicom.repcom.data.contract.CategoriaProdutoContract;

public class CategoriaProdutoVo implements CategoriaProduto  {

	public CategoriaProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idCategoriaProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private CategoriaProdutoAgregado agregado = null;
	private CategoriaProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CategoriaProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idCategoriaProduto;
	public long getIdCategoriaProduto() {
		return idCategoriaProduto;
	}
	public void setIdCategoriaProduto(long _valor) {
		idCategoriaProduto = _valor;
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



	
	private long idCategoriaProdutoP;
	public long getIdCategoriaProdutoP() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getCategoriaProduto_Pai()!=null) 
		//	return getCategoriaProduto_Pai().getIdObj();
		//else
			return idCategoriaProdutoP;
	}
	public void setIdCategoriaProdutoP(long _valor) {
		idCategoriaProdutoP = _valor;
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
  	
	public long getIdCategoriaProdutoPLazyLoader() {
		return idCategoriaProdutoP;
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
  	
		public CategoriaProduto getCategoriaProduto_Pai() {
			return getAgregado().getCategoriaProduto_Pai();
		}
		public void setCategoriaProduto_Pai(CategoriaProduto item) {
			// Coloquei em 10-11-2016
			idCategoriaProdutoP = item.getIdObj();
			getAgregado().setCategoriaProduto_Pai(item);
		}
		
		
	
	// SemChaveEstrangeira
	
		public ClienteInteresseCategoria getCorrenteClienteInteresseCategoria_Associada() {
			return getAgregado().getCorrenteClienteInteresseCategoria_Associada();
		}
		public void addListaClienteInteresseCategoria_Associada(ClienteInteresseCategoria item) {
			getAgregado().addListaClienteInteresseCategoria_Associada(item);
		}
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada() {
			return getAgregado().getListaClienteInteresseCategoria_Associada();
		}
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_AssociadaOriginal() {
			return getAgregado().getListaClienteInteresseCategoria_AssociadaOriginal();
		}
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada(int qtde) {
			return getAgregado().getListaClienteInteresseCategoria_Associada(qtde);
		}
		public void setListaClienteInteresseCategoria_Associada(List<ClienteInteresseCategoria> lista) {
			getAgregado().setListaClienteInteresseCategoria_Associada(lista);
		}
		public void setListaClienteInteresseCategoria_AssociadaByDao(List<ClienteInteresseCategoria> lista) {
			getAgregado().setListaClienteInteresseCategoria_AssociadaByDao(lista);
		}
		public void criaVaziaListaClienteInteresseCategoria_Associada() {
			getAgregado().criaVaziaListaClienteInteresseCategoria_Associada();
		}
		
		public boolean existeListaClienteInteresseCategoria_Associada() {
			return getAgregado().existeListaClienteInteresseCategoria_Associada();
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
 		
		public CategoriaProduto getCorrenteCategoriaProduto_Pai() {
			return getAgregado().getCorrenteCategoriaProduto_Pai();
		}
		public void addListaCategoriaProduto_Pai(CategoriaProduto item) {
			getAgregado().addListaCategoriaProduto_Pai(item);
		}
		public List<CategoriaProduto> getListaCategoriaProduto_Pai() {
			return getAgregado().getListaCategoriaProduto_Pai();
		}
		public List<CategoriaProduto> getListaCategoriaProduto_PaiOriginal() {
			return getAgregado().getListaCategoriaProduto_PaiOriginal();
		}
		public List<CategoriaProduto> getListaCategoriaProduto_Pai(int qtde) {
			return getAgregado().getListaCategoriaProduto_Pai(qtde);
		}
		public void setListaCategoriaProduto_Pai(List<CategoriaProduto> lista) {
			getAgregado().setListaCategoriaProduto_Pai(lista);
		}
		public void setListaCategoriaProduto_PaiByDao(List<CategoriaProduto> lista) {
			getAgregado().setListaCategoriaProduto_PaiByDao(lista);
		}
		public void criaVaziaListaCategoriaProduto_Pai() {
			getAgregado().criaVaziaListaCategoriaProduto_Pai();
		}
		
		public boolean existeListaCategoriaProduto_Pai() {
			return getAgregado().existeListaCategoriaProduto_Pai();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(CategoriaProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO, idCategoriaProduto);
    	valores.put(CategoriaProdutoContract.COLUNA_NOME, nome);
    	valores.put(CategoriaProdutoContract.COLUNA_URL, url);
    	valores.put(CategoriaProdutoContract.COLUNA_CODIGO_LINE_ID, codigoLineId);
    	valores.put(CategoriaProdutoContract.COLUNA_DATA_INCLUSAO, UtilDatas.getDataLong(dataInclusao));
		valores.put(CategoriaProdutoContract.COLUNA_ID_CATEGORIA_PRODUTO_P, idCategoriaProdutoP);
	
		return valores;
  	}
}