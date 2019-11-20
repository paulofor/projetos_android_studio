
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
import br.com.lojadigicom.coletorprecocliente.modelo.agregado.CompartilhamentoProdutoAgregado;
import br.com.lojadigicom.coletorprecocliente.data.contract.CompartilhamentoProdutoContract;

public class CompartilhamentoProdutoVo implements CompartilhamentoProduto  {

	public CompartilhamentoProdutoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idCompartilhamentoProduto;
    }

	 // ----- INICIO AGREGADO -----------------
	private CompartilhamentoProdutoAgregado agregado = null;
	private CompartilhamentoProdutoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CompartilhamentoProdutoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idCompartilhamentoProduto;
	public long getIdCompartilhamentoProduto() {
		return idCompartilhamentoProduto;
	}
	public void setIdCompartilhamentoProduto(long _valor) {
		idCompartilhamentoProduto = _valor;
	}


	private long idProdutoRa;
	public long getIdProdutoRa() {
		return idProdutoRa;
	}
	public void setIdProdutoRa(long _valor) {
		idProdutoRa = _valor;
	}


	private Timestamp dataHora;
	public Timestamp getDataHora() {
		return dataHora;
	}
	public void setDataHora(Timestamp _valor) {
		dataHora = _valor;
	}


	public String getDataHoraDDMMAAAA() {
		if (dataHora==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataHora);
    }




	public String getDataHoraHHMM() {
		if (dataHora==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataHora);
    }
    public String getDataHoraHHMMSS() {
		if (dataHora==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataHora);
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
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(CompartilhamentoProdutoContract.COLUNA_ID_COMPARTILHAMENTO_PRODUTO, idCompartilhamentoProduto);
    	valores.put(CompartilhamentoProdutoContract.COLUNA_ID_PRODUTO_RA, idProdutoRa);
    	valores.put(CompartilhamentoProdutoContract.COLUNA_DATA_HORA, UtilDatas.getDataHoraLong(dataHora));
		valores.put(CompartilhamentoProdutoContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}