
package br.com.lojadigicom.capitalexterno.modelo;


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

import br.com.lojadigicom.capitalexterno.framework.util.UtilDatas;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.agregado.NegocioAgregado;
import br.com.lojadigicom.capitalexterno.data.contract.NegocioContract;

public class NegocioVo implements Negocio  {

	public NegocioVo() {
  	}
  	
  	public long getIdObj()
    {
       return idNegocio;
    }

	 // ----- INICIO AGREGADO -----------------
	private NegocioAgregado agregado = null;
	private NegocioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new NegocioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idNegocio;
	public long getIdNegocio() {
		return idNegocio;
	}
	public void setIdNegocio(long _valor) {
		idNegocio = _valor;
	}


	private String descricao;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String _valor) {
		descricao = _valor;
	}


	private Timestamp dataCriacao;
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Timestamp _valor) {
		dataCriacao = _valor;
	}


	public String getDataCriacaoDDMMAAAA() {
		if (dataCriacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataCriacao);
    }




	public String getDataCriacaoHHMM() {
		if (dataCriacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataCriacao);
    }
    public String getDataCriacaoHHMMSS() {
		if (dataCriacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataCriacao);
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
	
		public CaracteristicaMercado getCorrenteCaracteristicaMercado_Possui() {
			return getAgregado().getCorrenteCaracteristicaMercado_Possui();
		}
		public void addListaCaracteristicaMercado_Possui(CaracteristicaMercado item) {
			getAgregado().addListaCaracteristicaMercado_Possui(item);
		}
		public List<CaracteristicaMercado> getListaCaracteristicaMercado_Possui() {
			return getAgregado().getListaCaracteristicaMercado_Possui();
		}
		public List<CaracteristicaMercado> getListaCaracteristicaMercado_PossuiOriginal() {
			return getAgregado().getListaCaracteristicaMercado_PossuiOriginal();
		}
		public List<CaracteristicaMercado> getListaCaracteristicaMercado_Possui(int qtde) {
			return getAgregado().getListaCaracteristicaMercado_Possui(qtde);
		}
		public void setListaCaracteristicaMercado_Possui(List<CaracteristicaMercado> lista) {
			getAgregado().setListaCaracteristicaMercado_Possui(lista);
		}
		public void setListaCaracteristicaMercado_PossuiByDao(List<CaracteristicaMercado> lista) {
			getAgregado().setListaCaracteristicaMercado_PossuiByDao(lista);
		}
		public void criaVaziaListaCaracteristicaMercado_Possui() {
			getAgregado().criaVaziaListaCaracteristicaMercado_Possui();
		}
		
		public boolean existeListaCaracteristicaMercado_Possui() {
			return getAgregado().existeListaCaracteristicaMercado_Possui();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(NegocioContract.COLUNA_ID_NEGOCIO, idNegocio);
    	valores.put(NegocioContract.COLUNA_DESCRICAO, descricao);
    	valores.put(NegocioContract.COLUNA_DATA_CRIACAO, UtilDatas.getDataHoraLong(dataCriacao));
		return valores;
  	}
}