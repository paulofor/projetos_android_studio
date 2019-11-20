
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
import br.com.lojadigicom.treinoacademia.modelo.agregado.ItemSerieAgregado;
import br.com.lojadigicom.treinoacademia.data.contract.ItemSerieContract;

public class ItemSerieVo implements ItemSerie  {

	public ItemSerieVo() {
  	}
  	
  	public long getIdObj()
    {
       return idItemSerie;
    }

	 // ----- INICIO AGREGADO -----------------
	private ItemSerieAgregado agregado = null;
	private ItemSerieAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ItemSerieAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idItemSerie;
	public long getIdItemSerie() {
		return idItemSerie;
	}
	public void setIdItemSerie(long _valor) {
		idItemSerie = _valor;
	}


	private long repeticoes;
	public long getRepeticoes() {
		return repeticoes;
	}
	public void setRepeticoes(long _valor) {
		repeticoes = _valor;
	}


	private long serie;
	public long getSerie() {
		return serie;
	}
	public void setSerie(long _valor) {
		serie = _valor;
	}


	private String parametros;
	public String getParametros() {
		return parametros;
	}
	public void setParametros(String _valor) {
		parametros = _valor;
	}


	private long ordemExecucao;
	public long getOrdemExecucao() {
		return ordemExecucao;
	}
	public void setOrdemExecucao(long _valor) {
		ordemExecucao = _valor;
	}

	
	private long idExercicioEd;
	public long getIdExercicioEd() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getExercicio_ExecucaoDe()!=null) 
		//	return getExercicio_ExecucaoDe().getIdObj();
		//else
			return idExercicioEd;
	}
	public void setIdExercicioEd(long _valor) {
		idExercicioEd = _valor;
	}

	
	private long idSerieTreinoPa;
	public long getIdSerieTreinoPa() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getSerieTreino_PertencenteA()!=null) 
		//	return getSerieTreino_PertencenteA().getIdObj();
		//else
			return idSerieTreinoPa;
	}
	public void setIdSerieTreinoPa(long _valor) {
		idSerieTreinoPa = _valor;
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
  	
	public long getIdExercicioEdLazyLoader() {
		return idExercicioEd;
	} 
		
	public long getIdSerieTreinoPaLazyLoader() {
		return idSerieTreinoPa;
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
  	
		public Exercicio getExercicio_ExecucaoDe() {
			return getAgregado().getExercicio_ExecucaoDe();
		}
		public void setExercicio_ExecucaoDe(Exercicio item) {
			// Coloquei em 10-11-2016
			idExercicioEd = item.getIdObj();
			getAgregado().setExercicio_ExecucaoDe(item);
		}
		
		public void addListaExercicio_ExecucaoDe(Exercicio item) {
			getAgregado().addListaExercicio_ExecucaoDe(item);
		}
		public Exercicio getCorrenteExercicio_ExecucaoDe() {
			return getAgregado().getCorrenteExercicio_ExecucaoDe();
		}
		
		
		public SerieTreino getSerieTreino_PertencenteA() {
			return getAgregado().getSerieTreino_PertencenteA();
		}
		public void setSerieTreino_PertencenteA(SerieTreino item) {
			// Coloquei em 10-11-2016
			idSerieTreinoPa = item.getIdObj();
			getAgregado().setSerieTreino_PertencenteA(item);
		}
		
		public void addListaSerieTreino_PertencenteA(SerieTreino item) {
			getAgregado().addListaSerieTreino_PertencenteA(item);
		}
		public SerieTreino getCorrenteSerieTreino_PertencenteA() {
			return getAgregado().getCorrenteSerieTreino_PertencenteA();
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
	
		public CargaPlanejada getCorrenteCargaPlanejada_Possui() {
			return getAgregado().getCorrenteCargaPlanejada_Possui();
		}
		public void addListaCargaPlanejada_Possui(CargaPlanejada item) {
			getAgregado().addListaCargaPlanejada_Possui(item);
		}
		public List<CargaPlanejada> getListaCargaPlanejada_Possui() {
			return getAgregado().getListaCargaPlanejada_Possui();
		}
		public List<CargaPlanejada> getListaCargaPlanejada_PossuiOriginal() {
			return getAgregado().getListaCargaPlanejada_PossuiOriginal();
		}
		public List<CargaPlanejada> getListaCargaPlanejada_Possui(int qtde) {
			return getAgregado().getListaCargaPlanejada_Possui(qtde);
		}
		public void setListaCargaPlanejada_Possui(List<CargaPlanejada> lista) {
			getAgregado().setListaCargaPlanejada_Possui(lista);
		}
		public void setListaCargaPlanejada_PossuiByDao(List<CargaPlanejada> lista) {
			getAgregado().setListaCargaPlanejada_PossuiByDao(lista);
		}
		public void criaVaziaListaCargaPlanejada_Possui() {
			getAgregado().criaVaziaListaCargaPlanejada_Possui();
		}
		
		public boolean existeListaCargaPlanejada_Possui() {
			return getAgregado().existeListaCargaPlanejada_Possui();
		}
 		
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_Gera() {
			return getAgregado().getCorrenteExecucaoItemSerie_Gera();
		}
		public void addListaExecucaoItemSerie_Gera(ExecucaoItemSerie item) {
			getAgregado().addListaExecucaoItemSerie_Gera(item);
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Gera() {
			return getAgregado().getListaExecucaoItemSerie_Gera();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_GeraOriginal() {
			return getAgregado().getListaExecucaoItemSerie_GeraOriginal();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Gera(int qtde) {
			return getAgregado().getListaExecucaoItemSerie_Gera(qtde);
		}
		public void setListaExecucaoItemSerie_Gera(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_Gera(lista);
		}
		public void setListaExecucaoItemSerie_GeraByDao(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_GeraByDao(lista);
		}
		public void criaVaziaListaExecucaoItemSerie_Gera() {
			getAgregado().criaVaziaListaExecucaoItemSerie_Gera();
		}
		
		public boolean existeListaExecucaoItemSerie_Gera() {
			return getAgregado().existeListaExecucaoItemSerie_Gera();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ItemSerieContract.COLUNA_ID_ITEM_SERIE, idItemSerie);
    	valores.put(ItemSerieContract.COLUNA_REPETICOES, repeticoes);
    	valores.put(ItemSerieContract.COLUNA_SERIE, serie);
    	valores.put(ItemSerieContract.COLUNA_PARAMETROS, parametros);
    	valores.put(ItemSerieContract.COLUNA_ORDEM_EXECUCAO, ordemExecucao);
		valores.put(ItemSerieContract.COLUNA_ID_EXERCICIO_ED, idExercicioEd);
	
		valores.put(ItemSerieContract.COLUNA_ID_SERIE_TREINO_PA, idSerieTreinoPa);
	
		valores.put(ItemSerieContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}