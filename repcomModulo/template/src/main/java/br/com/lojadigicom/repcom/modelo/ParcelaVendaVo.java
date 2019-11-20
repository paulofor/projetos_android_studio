
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
import br.com.lojadigicom.repcom.modelo.agregado.ParcelaVendaAgregado;
import br.com.lojadigicom.repcom.data.contract.ParcelaVendaContract;

public class ParcelaVendaVo implements ParcelaVenda  {

	public ParcelaVendaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idParcelaVenda;
    }

	 // ----- INICIO AGREGADO -----------------
	private ParcelaVendaAgregado agregado = null;
	private ParcelaVendaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ParcelaVendaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idParcelaVenda;
	public long getIdParcelaVenda() {
		return idParcelaVenda;
	}
	public void setIdParcelaVenda(long _valor) {
		idParcelaVenda = _valor;
	}


	private float valorParcela;
	public float getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(float _valor) {
		valorParcela = _valor;
	}
	
	public String getValorParcelaTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valorParcela);
		return saida;
	}

	private Timestamp dataVencimento;
	public Timestamp getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Timestamp _valor) {
		dataVencimento = _valor;
	}


	public String getDataVencimentoDDMMAAAA() {
		if (dataVencimento==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataVencimento);
    }
    public void setDataVencimentoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataVencimento = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataVencimentoDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataVencimento = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private long numeroParcela;
	public long getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(long _valor) {
		numeroParcela = _valor;
	}


	private boolean paga;
	public boolean getPaga() {
		return paga;
	}
	public void setPaga(boolean _valor) {
		paga = _valor;
	}

	
	private long idVendaPa;
	public long getIdVendaPa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getVenda_PertenceA()!=null) 
		//	return getVenda_PertenceA().getIdObj();
		//else
			return idVendaPa;
	}
	public void setIdVendaPa(long _valor) {
		idVendaPa = _valor;
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
  	
	public long getIdVendaPaLazyLoader() {
		return idVendaPa;
	} 
		
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
  	
		public Venda getVenda_PertenceA() {
			return getAgregado().getVenda_PertenceA();
		}
		public void setVenda_PertenceA(Venda item) {
			// Coloquei em 10-11-2016
			idVendaPa = item.getIdObj();
			getAgregado().setVenda_PertenceA(item);
		}
		
		public void addListaVenda_PertenceA(Venda item) {
			getAgregado().addListaVenda_PertenceA(item);
		}
		public Venda getCorrenteVenda_PertenceA() {
			return getAgregado().getCorrenteVenda_PertenceA();
		}
		
		
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
	
    	valores.put(ParcelaVendaContract.COLUNA_ID_PARCELA_VENDA, idParcelaVenda);
    	valores.put(ParcelaVendaContract.COLUNA_VALOR_PARCELA, valorParcela);
    	valores.put(ParcelaVendaContract.COLUNA_DATA_VENCIMENTO, UtilDatas.getDataLong(dataVencimento));
    	valores.put(ParcelaVendaContract.COLUNA_NUMERO_PARCELA, numeroParcela);
    	valores.put(ParcelaVendaContract.COLUNA_PAGA, paga);
		valores.put(ParcelaVendaContract.COLUNA_ID_VENDA_PA, idVendaPa);
	
		valores.put(ParcelaVendaContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}