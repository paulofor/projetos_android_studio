
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
import br.com.lojadigicom.treinoacademia.modelo.agregado.DiaTreinoAgregado;
import br.com.lojadigicom.treinoacademia.data.contract.DiaTreinoContract;

public class DiaTreinoVo implements DiaTreino  {

	public DiaTreinoVo() {
  	}
  	
  	public long getIdObj()
    {
       return idDiaTreino;
    }

	 // ----- INICIO AGREGADO -----------------
	private DiaTreinoAgregado agregado = null;
	private DiaTreinoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new DiaTreinoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idDiaTreino;
	public long getIdDiaTreino() {
		return idDiaTreino;
	}
	public void setIdDiaTreino(long _valor) {
		idDiaTreino = _valor;
	}


	private Timestamp data;
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp _valor) {
		data = _valor;
	}


	public String getDataDDMMAAAA() {
		if (data==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(data);
    }
    public void setDataDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			data = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }
    public void setDataDDMMAAAAComTraco(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			data = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private boolean concluido;
	public boolean getConcluido() {
		return concluido;
	}
	public void setConcluido(boolean _valor) {
		concluido = _valor;
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

	
	private long idSerieTreinoSd;
	public long getIdSerieTreinoSd() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getSerieTreino_SerieDia()!=null) 
		//	return getSerieTreino_SerieDia().getIdObj();
		//else
			return idSerieTreinoSd;
	}
	public void setIdSerieTreinoSd(long _valor) {
		idSerieTreinoSd = _valor;
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
		
	public long getIdSerieTreinoSdLazyLoader() {
		return idSerieTreinoSd;
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
		
		
		public SerieTreino getSerieTreino_SerieDia() {
			return getAgregado().getSerieTreino_SerieDia();
		}
		public void setSerieTreino_SerieDia(SerieTreino item) {
			// Coloquei em 10-11-2016
			idSerieTreinoSd = item.getIdObj();
			getAgregado().setSerieTreino_SerieDia(item);
		}
		
		public void addListaSerieTreino_SerieDia(SerieTreino item) {
			getAgregado().addListaSerieTreino_SerieDia(item);
		}
		public SerieTreino getCorrenteSerieTreino_SerieDia() {
			return getAgregado().getCorrenteSerieTreino_SerieDia();
		}
		
		
	
	// SemChaveEstrangeira
	
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_FoiRealizado() {
			return getAgregado().getCorrenteExecucaoItemSerie_FoiRealizado();
		}
		public void addListaExecucaoItemSerie_FoiRealizado(ExecucaoItemSerie item) {
			getAgregado().addListaExecucaoItemSerie_FoiRealizado(item);
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizado() {
			return getAgregado().getListaExecucaoItemSerie_FoiRealizado();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizadoOriginal() {
			return getAgregado().getListaExecucaoItemSerie_FoiRealizadoOriginal();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizado(int qtde) {
			return getAgregado().getListaExecucaoItemSerie_FoiRealizado(qtde);
		}
		public void setListaExecucaoItemSerie_FoiRealizado(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_FoiRealizado(lista);
		}
		public void setListaExecucaoItemSerie_FoiRealizadoByDao(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_FoiRealizadoByDao(lista);
		}
		public void criaVaziaListaExecucaoItemSerie_FoiRealizado() {
			getAgregado().criaVaziaListaExecucaoItemSerie_FoiRealizado();
		}
		
		public boolean existeListaExecucaoItemSerie_FoiRealizado() {
			return getAgregado().existeListaExecucaoItemSerie_FoiRealizado();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(DiaTreinoContract.COLUNA_ID_DIA_TREINO, idDiaTreino);
    	valores.put(DiaTreinoContract.COLUNA_DATA, UtilDatas.getDataLong(data));
    	valores.put(DiaTreinoContract.COLUNA_CONCLUIDO, concluido);
		valores.put(DiaTreinoContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		valores.put(DiaTreinoContract.COLUNA_ID_SERIE_TREINO_SD, idSerieTreinoSd);
	
		return valores;
  	}
}