
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
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.UsuarioPesquisaAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioPesquisaContract;

public class UsuarioPesquisaVo implements UsuarioPesquisa  {

	public UsuarioPesquisaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idUsuarioPesquisa;
    }

	 // ----- INICIO AGREGADO -----------------
	private UsuarioPesquisaAgregado agregado = null;
	private UsuarioPesquisaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new UsuarioPesquisaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idUsuarioPesquisa;
	public long getIdUsuarioPesquisa() {
		return idUsuarioPesquisa;
	}
	public void setIdUsuarioPesquisa(long _valor) {
		idUsuarioPesquisa = _valor;
	}

	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento
		//if (idUsuarioS==0 && this.getUsuario_Sincroniza()!=null) 
		//	return getUsuario_Sincroniza().getId();
		//else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
	}

	
	private long idNaturezaProdutoP;
	public long getIdNaturezaProdutoP() {
		// relacionamento
		//if (idNaturezaProdutoP==0 && this.getNaturezaProduto_Pesquisa()!=null) 
		//	return getNaturezaProduto_Pesquisa().getId();
		//else
			return idNaturezaProdutoP;
	}
	public void setIdNaturezaProdutoP(long _valor) {
		idNaturezaProdutoP = _valor;
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
		
	public long getIdNaturezaProdutoPLazyLoader() {
		return idNaturezaProdutoP;
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
			getAgregado().setUsuario_Sincroniza(item);
		}
		
		public void addListaUsuario_Sincroniza(Usuario item) {
			getAgregado().addListaUsuario_Sincroniza(item);
		}
		public Usuario getCorrenteUsuario_Sincroniza() {
			return getAgregado().getCorrenteUsuario_Sincroniza();
		}
		
		
		public NaturezaProduto getNaturezaProduto_Pesquisa() {
			return getAgregado().getNaturezaProduto_Pesquisa();
		}
		public void setNaturezaProduto_Pesquisa(NaturezaProduto item) {
			getAgregado().setNaturezaProduto_Pesquisa(item);
		}
		
		public void addListaNaturezaProduto_Pesquisa(NaturezaProduto item) {
			getAgregado().addListaNaturezaProduto_Pesquisa(item);
		}
		public NaturezaProduto getCorrenteNaturezaProduto_Pesquisa() {
			return getAgregado().getCorrenteNaturezaProduto_Pesquisa();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(UsuarioPesquisaContract.COLUNA_ID_USUARIO_PESQUISA, idUsuarioPesquisa);
		valores.put(UsuarioPesquisaContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		valores.put(UsuarioPesquisaContract.COLUNA_ID_NATUREZA_PRODUTO_P, idNaturezaProdutoP);
	
		return valores;
  	}
}