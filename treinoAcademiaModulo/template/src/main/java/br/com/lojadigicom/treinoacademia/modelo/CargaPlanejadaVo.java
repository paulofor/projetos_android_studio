
package br.com.lojadigicom.treinoacademia.modelo;


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

import br.com.lojadigicom.treinoacademia.framework.util.UtilDatas;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import br.com.lojadigicom.treinoacademia.modelo.agregado.CargaPlanejadaAgregado;
import br.com.lojadigicom.treinoacademia.data.contract.CargaPlanejadaContract;

public class CargaPlanejadaVo implements CargaPlanejada  {

	public CargaPlanejadaVo() {
  	}
  	
  	public long getIdObj()
    {
       return idCargaPlanejada;
    }

	 // ----- INICIO AGREGADO -----------------
	private CargaPlanejadaAgregado agregado = null;
	private CargaPlanejadaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CargaPlanejadaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idCargaPlanejada;
	public long getIdCargaPlanejada() {
		return idCargaPlanejada;
	}
	public void setIdCargaPlanejada(long _valor) {
		idCargaPlanejada = _valor;
	}


	private float valorCarga;
	public float getValorCarga() {
		return valorCarga;
	}
	public void setValorCarga(float _valor) {
		valorCarga = _valor;
	}
	
	public String getValorCargaTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(valorCarga);
		return saida;
	}

	private Timestamp dataInicio;
	public Timestamp getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Timestamp _valor) {
		dataInicio = _valor;
	}


	public String getDataInicioDDMMAAAA() {
		if (dataInicio==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataInicio);
    }
    public void setDataInicioDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInicio = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataInicioDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInicio = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private Timestamp dataFinal;
	public Timestamp getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Timestamp _valor) {
		dataFinal = _valor;
	}


	public String getDataFinalDDMMAAAA() {
		if (dataFinal==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataFinal);
    }
    public void setDataFinalDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataFinal = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataFinalDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataFinal = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private boolean ativa;
	public boolean getAtiva() {
		return ativa;
	}
	public void setAtiva(boolean _valor) {
		ativa = _valor;
	}


	private long quantidadeRepeticao;
	public long getQuantidadeRepeticao() {
		return quantidadeRepeticao;
	}
	public void setQuantidadeRepeticao(long _valor) {
		quantidadeRepeticao = _valor;
	}


	private long ordemRepeticao;
	public long getOrdemRepeticao() {
		return ordemRepeticao;
	}
	public void setOrdemRepeticao(long _valor) {
		ordemRepeticao = _valor;
	}

	
	private long idItemSerieRa;
	public long getIdItemSerieRa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getItemSerie_ReferenteA()!=null) 
		//	return getItemSerie_ReferenteA().getIdObj();
		//else
			return idItemSerieRa;
	}
	public void setIdItemSerieRa(long _valor) {
		idItemSerieRa = _valor;
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
  	
	public long getIdItemSerieRaLazyLoader() {
		return idItemSerieRa;
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
  	
		public ItemSerie getItemSerie_ReferenteA() {
			return getAgregado().getItemSerie_ReferenteA();
		}
		public void setItemSerie_ReferenteA(ItemSerie item) {
			// Coloquei em 10-11-2016
			idItemSerieRa = item.getIdObj();
			getAgregado().setItemSerie_ReferenteA(item);
		}
		
		public void addListaItemSerie_ReferenteA(ItemSerie item) {
			getAgregado().addListaItemSerie_ReferenteA(item);
		}
		public ItemSerie getCorrenteItemSerie_ReferenteA() {
			return getAgregado().getCorrenteItemSerie_ReferenteA();
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
	
    	valores.put(CargaPlanejadaContract.COLUNA_ID_CARGA_PLANEJADA, idCargaPlanejada);
    	valores.put(CargaPlanejadaContract.COLUNA_VALOR_CARGA, valorCarga);
    	valores.put(CargaPlanejadaContract.COLUNA_DATA_INICIO, UtilDatas.getDataLong(dataInicio));
    	valores.put(CargaPlanejadaContract.COLUNA_DATA_FINAL, UtilDatas.getDataLong(dataFinal));
    	valores.put(CargaPlanejadaContract.COLUNA_ATIVA, ativa);
    	valores.put(CargaPlanejadaContract.COLUNA_QUANTIDADE_REPETICAO, quantidadeRepeticao);
    	valores.put(CargaPlanejadaContract.COLUNA_ORDEM_REPETICAO, ordemRepeticao);
		valores.put(CargaPlanejadaContract.COLUNA_ID_ITEM_SERIE_RA, idItemSerieRa);
	
		valores.put(CargaPlanejadaContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}