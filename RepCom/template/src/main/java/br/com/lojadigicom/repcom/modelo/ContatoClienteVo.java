
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
import br.com.lojadigicom.repcom.modelo.agregado.ContatoClienteAgregado;
import br.com.lojadigicom.repcom.data.contract.ContatoClienteContract;

public class ContatoClienteVo implements ContatoCliente  {

	public ContatoClienteVo() {
  	}
  	
  	public long getIdObj()
    {
       return idContatoCliente;
    }

	 // ----- INICIO AGREGADO -----------------
	private ContatoClienteAgregado agregado = null;
	private ContatoClienteAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ContatoClienteAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idContatoCliente;
	public long getIdContatoCliente() {
		return idContatoCliente;
	}
	public void setIdContatoCliente(long _valor) {
		idContatoCliente = _valor;
	}


	private Timestamp dataContato;
	public Timestamp getDataContato() {
		return dataContato;
	}
	public void setDataContato(Timestamp _valor) {
		dataContato = _valor;
	}


	public String getDataContatoDDMMAAAA() {
		if (dataContato==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataContato);
    }
    public void setDataContatoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataContato = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataContatoDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataContato = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }



	
	private long idClienteRa;
	public long getIdClienteRa() {
		// relacionamento
		//if (idClienteRa==0 && this.getCliente_ReferenteA()!=null) 
		//	return getCliente_ReferenteA().getId();
		//else
			return idClienteRa;
	}
	public void setIdClienteRa(long _valor) {
		idClienteRa = _valor;
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
  	
	public long getIdClienteRaLazyLoader() {
		return idClienteRa;
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
  	
		public Cliente getCliente_ReferenteA() {
			return getAgregado().getCliente_ReferenteA();
		}
		public void setCliente_ReferenteA(Cliente item) {
			getAgregado().setCliente_ReferenteA(item);
		}
		
		public void addListaCliente_ReferenteA(Cliente item) {
			getAgregado().addListaCliente_ReferenteA(item);
		}
		public Cliente getCorrenteCliente_ReferenteA() {
			return getAgregado().getCorrenteCliente_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ContatoClienteContract.COLUNA_ID_CONTATO_CLIENTE, idContatoCliente);
    	valores.put(ContatoClienteContract.COLUNA_DATA_CONTATO, UtilDatas.getDataLong(dataContato));
		valores.put(ContatoClienteContract.COLUNA_ID_CLIENTE_RA, idClienteRa);
	
		return valores;
  	}
}