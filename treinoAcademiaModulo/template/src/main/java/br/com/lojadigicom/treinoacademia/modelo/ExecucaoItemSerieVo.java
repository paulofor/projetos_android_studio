
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
import br.com.lojadigicom.treinoacademia.modelo.agregado.ExecucaoItemSerieAgregado;
import br.com.lojadigicom.treinoacademia.data.contract.ExecucaoItemSerieContract;

public class ExecucaoItemSerieVo implements ExecucaoItemSerie  {

	public ExecucaoItemSerieVo() {
  	}
  	
  	public long getIdObj()
    {
       return idExecucaoItemSerie;
    }

	 // ----- INICIO AGREGADO -----------------
	private ExecucaoItemSerieAgregado agregado = null;
	private ExecucaoItemSerieAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ExecucaoItemSerieAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idExecucaoItemSerie;
	public long getIdExecucaoItemSerie() {
		return idExecucaoItemSerie;
	}
	public void setIdExecucaoItemSerie(long _valor) {
		idExecucaoItemSerie = _valor;
	}


	private Timestamp dataHoraInicio;
	public Timestamp getDataHoraInicio() {
		return dataHoraInicio;
	}
	public void setDataHoraInicio(Timestamp _valor) {
		dataHoraInicio = _valor;
	}


	public String getDataHoraInicioDDMMAAAA() {
		if (dataHoraInicio==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataHoraInicio);
    }




	public String getDataHoraInicioHHMM() {
		if (dataHoraInicio==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataHoraInicio);
    }
    public String getDataHoraInicioHHMMSS() {
		if (dataHoraInicio==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataHoraInicio);
    }
	private float cargaUtilizada;
	public float getCargaUtilizada() {
		return cargaUtilizada;
	}
	public void setCargaUtilizada(float _valor) {
		cargaUtilizada = _valor;
	}
	
	public String getCargaUtilizadaTela() {
		DecimalFormat df = new DecimalFormat("###,###.00");
		String saida = df.format(cargaUtilizada);
		return saida;
	}

	private boolean sucessoRepeticoes;
	public boolean getSucessoRepeticoes() {
		return sucessoRepeticoes;
	}
	public void setSucessoRepeticoes(boolean _valor) {
		sucessoRepeticoes = _valor;
	}


	private long numeroSerie;
	public long getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(long _valor) {
		numeroSerie = _valor;
	}


	private Timestamp dataHoraFinalizacao;
	public Timestamp getDataHoraFinalizacao() {
		return dataHoraFinalizacao;
	}
	public void setDataHoraFinalizacao(Timestamp _valor) {
		dataHoraFinalizacao = _valor;
	}


	public String getDataHoraFinalizacaoDDMMAAAA() {
		if (dataHoraFinalizacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataHoraFinalizacao);
    }




	public String getDataHoraFinalizacaoHHMM() {
		if (dataHoraFinalizacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataHoraFinalizacao);
    }
    public String getDataHoraFinalizacaoHHMMSS() {
		if (dataHoraFinalizacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataHoraFinalizacao);
    }
	private long quantidadeRepeticao;
	public long getQuantidadeRepeticao() {
		return quantidadeRepeticao;
	}
	public void setQuantidadeRepeticao(long _valor) {
		quantidadeRepeticao = _valor;
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

	
	private long idDiaTreinoE;
	public long getIdDiaTreinoE() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getDiaTreino_Em()!=null) 
		//	return getDiaTreino_Em().getIdObj();
		//else
			return idDiaTreinoE;
	}
	public void setIdDiaTreinoE(long _valor) {
		idDiaTreinoE = _valor;
	}

	
	private long idExercicioRa;
	public long getIdExercicioRa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getExercicio_ReferenteA()!=null) 
		//	return getExercicio_ReferenteA().getIdObj();
		//else
			return idExercicioRa;
	}
	public void setIdExercicioRa(long _valor) {
		idExercicioRa = _valor;
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
		
	public long getIdDiaTreinoELazyLoader() {
		return idDiaTreinoE;
	} 
		
	public long getIdExercicioRaLazyLoader() {
		return idExercicioRa;
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
		
		
		public DiaTreino getDiaTreino_Em() {
			return getAgregado().getDiaTreino_Em();
		}
		public void setDiaTreino_Em(DiaTreino item) {
			// Coloquei em 10-11-2016
			idDiaTreinoE = item.getIdObj();
			getAgregado().setDiaTreino_Em(item);
		}
		
		public void addListaDiaTreino_Em(DiaTreino item) {
			getAgregado().addListaDiaTreino_Em(item);
		}
		public DiaTreino getCorrenteDiaTreino_Em() {
			return getAgregado().getCorrenteDiaTreino_Em();
		}
		
		
		public Exercicio getExercicio_ReferenteA() {
			return getAgregado().getExercicio_ReferenteA();
		}
		public void setExercicio_ReferenteA(Exercicio item) {
			// Coloquei em 10-11-2016
			idExercicioRa = item.getIdObj();
			getAgregado().setExercicio_ReferenteA(item);
		}
		
		public void addListaExercicio_ReferenteA(Exercicio item) {
			getAgregado().addListaExercicio_ReferenteA(item);
		}
		public Exercicio getCorrenteExercicio_ReferenteA() {
			return getAgregado().getCorrenteExercicio_ReferenteA();
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
	
    	valores.put(ExecucaoItemSerieContract.COLUNA_ID_EXECUCAO_ITEM_SERIE, idExecucaoItemSerie);
    	valores.put(ExecucaoItemSerieContract.COLUNA_DATA_HORA_INICIO, UtilDatas.getDataHoraLong(dataHoraInicio));
    	valores.put(ExecucaoItemSerieContract.COLUNA_CARGA_UTILIZADA, cargaUtilizada);
    	valores.put(ExecucaoItemSerieContract.COLUNA_SUCESSO_REPETICOES, sucessoRepeticoes);
    	valores.put(ExecucaoItemSerieContract.COLUNA_NUMERO_SERIE, numeroSerie);
    	valores.put(ExecucaoItemSerieContract.COLUNA_DATA_HORA_FINALIZACAO, UtilDatas.getDataHoraLong(dataHoraFinalizacao));
    	valores.put(ExecucaoItemSerieContract.COLUNA_QUANTIDADE_REPETICAO, quantidadeRepeticao);
		valores.put(ExecucaoItemSerieContract.COLUNA_ID_ITEM_SERIE_RA, idItemSerieRa);
	
		valores.put(ExecucaoItemSerieContract.COLUNA_ID_DIA_TREINO_E, idDiaTreinoE);
	
		valores.put(ExecucaoItemSerieContract.COLUNA_ID_EXERCICIO_RA, idExercicioRa);
	
		valores.put(ExecucaoItemSerieContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}