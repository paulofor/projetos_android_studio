
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
import br.com.lojadigicom.treinoacademia.modelo.agregado.SerieTreinoAgregado;
import br.com.lojadigicom.treinoacademia.data.contract.SerieTreinoContract;

public class SerieTreinoVo implements SerieTreino  {

	public SerieTreinoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idSerieTreino;
    }

	 // ----- INICIO AGREGADO -----------------
	private SerieTreinoAgregado agregado = null;
	private SerieTreinoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new SerieTreinoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idSerieTreino;
	public long getIdSerieTreino() {
		return idSerieTreino;
	}
	public void setIdSerieTreino(long _valor) {
		idSerieTreino = _valor;
	}


	private long qtdeExecucao;
	public long getQtdeExecucao() {
		return qtdeExecucao;
	}
	public void setQtdeExecucao(long _valor) {
		qtdeExecucao = _valor;
	}


	private boolean ativa;
	public boolean getAtiva() {
		return ativa;
	}
	public void setAtiva(boolean _valor) {
		ativa = _valor;
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
    public void setDataCriacaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataCriacao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataCriacaoDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataCriacao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private Timestamp dataUltimaExecucao;
	public Timestamp getDataUltimaExecucao() {
		return dataUltimaExecucao;
	}
	public void setDataUltimaExecucao(Timestamp _valor) {
		dataUltimaExecucao = _valor;
	}


	public String getDataUltimaExecucaoDDMMAAAA() {
		if (dataUltimaExecucao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataUltimaExecucao);
    }
    public void setDataUltimaExecucaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaExecucao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataUltimaExecucaoDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaExecucao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
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
	
		public ItemSerie getCorrenteItemSerie_Possui() {
			return getAgregado().getCorrenteItemSerie_Possui();
		}
		public void addListaItemSerie_Possui(ItemSerie item) {
			getAgregado().addListaItemSerie_Possui(item);
		}
		public List<ItemSerie> getListaItemSerie_Possui() {
			return getAgregado().getListaItemSerie_Possui();
		}
		public List<ItemSerie> getListaItemSerie_PossuiOriginal() {
			return getAgregado().getListaItemSerie_PossuiOriginal();
		}
		public List<ItemSerie> getListaItemSerie_Possui(int qtde) {
			return getAgregado().getListaItemSerie_Possui(qtde);
		}
		public void setListaItemSerie_Possui(List<ItemSerie> lista) {
			getAgregado().setListaItemSerie_Possui(lista);
		}
		public void setListaItemSerie_PossuiByDao(List<ItemSerie> lista) {
			getAgregado().setListaItemSerie_PossuiByDao(lista);
		}
		public void criaVaziaListaItemSerie_Possui() {
			getAgregado().criaVaziaListaItemSerie_Possui();
		}
		
		public boolean existeListaItemSerie_Possui() {
			return getAgregado().existeListaItemSerie_Possui();
		}
 		
		public DiaTreino getCorrenteDiaTreino_SerieDia() {
			return getAgregado().getCorrenteDiaTreino_SerieDia();
		}
		public void addListaDiaTreino_SerieDia(DiaTreino item) {
			getAgregado().addListaDiaTreino_SerieDia(item);
		}
		public List<DiaTreino> getListaDiaTreino_SerieDia() {
			return getAgregado().getListaDiaTreino_SerieDia();
		}
		public List<DiaTreino> getListaDiaTreino_SerieDiaOriginal() {
			return getAgregado().getListaDiaTreino_SerieDiaOriginal();
		}
		public List<DiaTreino> getListaDiaTreino_SerieDia(int qtde) {
			return getAgregado().getListaDiaTreino_SerieDia(qtde);
		}
		public void setListaDiaTreino_SerieDia(List<DiaTreino> lista) {
			getAgregado().setListaDiaTreino_SerieDia(lista);
		}
		public void setListaDiaTreino_SerieDiaByDao(List<DiaTreino> lista) {
			getAgregado().setListaDiaTreino_SerieDiaByDao(lista);
		}
		public void criaVaziaListaDiaTreino_SerieDia() {
			getAgregado().criaVaziaListaDiaTreino_SerieDia();
		}
		
		public boolean existeListaDiaTreino_SerieDia() {
			return getAgregado().existeListaDiaTreino_SerieDia();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(SerieTreinoContract.COLUNA_ID_SERIE_TREINO, idSerieTreino);
    	valores.put(SerieTreinoContract.COLUNA_QTDE_EXECUCAO, qtdeExecucao);
    	valores.put(SerieTreinoContract.COLUNA_ATIVA, ativa);
    	valores.put(SerieTreinoContract.COLUNA_DATA_CRIACAO, UtilDatas.getDataLong(dataCriacao));
    	valores.put(SerieTreinoContract.COLUNA_DATA_ULTIMA_EXECUCAO, UtilDatas.getDataLong(dataUltimaExecucao));
		valores.put(SerieTreinoContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}