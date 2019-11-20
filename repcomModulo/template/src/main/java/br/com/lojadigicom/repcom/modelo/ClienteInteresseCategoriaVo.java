
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
import br.com.lojadigicom.repcom.modelo.agregado.ClienteInteresseCategoriaAgregado;
import br.com.lojadigicom.repcom.data.contract.ClienteInteresseCategoriaContract;

public class ClienteInteresseCategoriaVo implements ClienteInteresseCategoria  {

	public ClienteInteresseCategoriaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idClienteInteresseCategoria;
    }

	 // ----- INICIO AGREGADO -----------------
	private ClienteInteresseCategoriaAgregado agregado = null;
	private ClienteInteresseCategoriaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ClienteInteresseCategoriaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idClienteInteresseCategoria;
	public long getIdClienteInteresseCategoria() {
		return idClienteInteresseCategoria;
	}
	public void setIdClienteInteresseCategoria(long _valor) {
		idClienteInteresseCategoria = _valor;
	}

	
	private long idClienteA;
	public long getIdClienteA() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getCliente_Associada()!=null) 
		//	return getCliente_Associada().getIdObj();
		//else
			return idClienteA;
	}
	public void setIdClienteA(long _valor) {
		idClienteA = _valor;
	}

	
	private long idCategoriaProdutoA;
	public long getIdCategoriaProdutoA() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getCategoriaProduto_Associada()!=null) 
		//	return getCategoriaProduto_Associada().getIdObj();
		//else
			return idCategoriaProdutoA;
	}
	public void setIdCategoriaProdutoA(long _valor) {
		idCategoriaProdutoA = _valor;
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
  	
	public long getIdClienteALazyLoader() {
		return idClienteA;
	} 
		
	public long getIdCategoriaProdutoALazyLoader() {
		return idCategoriaProdutoA;
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
  	
		public Cliente getCliente_Associada() {
			return getAgregado().getCliente_Associada();
		}
		public void setCliente_Associada(Cliente item) {
			// Coloquei em 10-11-2016
			idClienteA = item.getIdObj();
			getAgregado().setCliente_Associada(item);
		}
		
		public void addListaCliente_Associada(Cliente item) {
			getAgregado().addListaCliente_Associada(item);
		}
		public Cliente getCorrenteCliente_Associada() {
			return getAgregado().getCorrenteCliente_Associada();
		}
		
		
		public CategoriaProduto getCategoriaProduto_Associada() {
			return getAgregado().getCategoriaProduto_Associada();
		}
		public void setCategoriaProduto_Associada(CategoriaProduto item) {
			// Coloquei em 10-11-2016
			idCategoriaProdutoA = item.getIdObj();
			getAgregado().setCategoriaProduto_Associada(item);
		}
		
		public void addListaCategoriaProduto_Associada(CategoriaProduto item) {
			getAgregado().addListaCategoriaProduto_Associada(item);
		}
		public CategoriaProduto getCorrenteCategoriaProduto_Associada() {
			return getAgregado().getCorrenteCategoriaProduto_Associada();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ClienteInteresseCategoriaContract.COLUNA_ID_CLIENTE_INTERESSE_CATEGORIA, idClienteInteresseCategoria);
		valores.put(ClienteInteresseCategoriaContract.COLUNA_ID_CLIENTE_A, idClienteA);
	
		valores.put(ClienteInteresseCategoriaContract.COLUNA_ID_CATEGORIA_PRODUTO_A, idCategoriaProdutoA);
	
		return valores;
  	}
}