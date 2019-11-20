
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
import br.com.lojadigicom.treinoacademia.modelo.agregado.ExercicioAgregado;
import br.com.lojadigicom.treinoacademia.data.contract.ExercicioContract;

public class ExercicioVo implements Exercicio  {

	public ExercicioVo() {
  	}
  	
  	public long getIdObj()
    {
       return idExercicio;
    }

	 // ----- INICIO AGREGADO -----------------
	private ExercicioAgregado agregado = null;
	private ExercicioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ExercicioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idExercicio;
	public long getIdExercicio() {
		return idExercicio;
	}
	public void setIdExercicio(long _valor) {
		idExercicio = _valor;
	}


	private String descricaoExercicio;
	public String getDescricaoExercicio() {
		return descricaoExercicio;
	}
	public void setDescricaoExercicio(String _valor) {
		descricaoExercicio = _valor;
	}


	private String imagem;
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String _valor) {
		imagem = _valor;
	}


	private String titulo;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String _valor) {
		titulo = _valor;
	}


	private String subtitulo;
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String _valor) {
		subtitulo = _valor;
	}

	
	private long idGrupoMuscularP;
	public long getIdGrupoMuscularP() {
		// relacionamento - Nao adianta pq na hora de gravar no banco pega o valor da variavel e nao desse 
		// metodo.
		//if (this.getGrupoMuscular_Para()!=null) 
		//	return getGrupoMuscular_Para().getIdObj();
		//else
			return idGrupoMuscularP;
	}
	public void setIdGrupoMuscularP(long _valor) {
		idGrupoMuscularP = _valor;
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
  	
	public long getIdGrupoMuscularPLazyLoader() {
		return idGrupoMuscularP;
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
  	
		public GrupoMuscular getGrupoMuscular_Para() {
			return getAgregado().getGrupoMuscular_Para();
		}
		public void setGrupoMuscular_Para(GrupoMuscular item) {
			// Coloquei em 10-11-2016
			idGrupoMuscularP = item.getIdObj();
			getAgregado().setGrupoMuscular_Para(item);
		}
		
		public void addListaGrupoMuscular_Para(GrupoMuscular item) {
			getAgregado().addListaGrupoMuscular_Para(item);
		}
		public GrupoMuscular getCorrenteGrupoMuscular_Para() {
			return getAgregado().getCorrenteGrupoMuscular_Para();
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
	
		public ItemSerie getCorrenteItemSerie_Gera() {
			return getAgregado().getCorrenteItemSerie_Gera();
		}
		public void addListaItemSerie_Gera(ItemSerie item) {
			getAgregado().addListaItemSerie_Gera(item);
		}
		public List<ItemSerie> getListaItemSerie_Gera() {
			return getAgregado().getListaItemSerie_Gera();
		}
		public List<ItemSerie> getListaItemSerie_GeraOriginal() {
			return getAgregado().getListaItemSerie_GeraOriginal();
		}
		public List<ItemSerie> getListaItemSerie_Gera(int qtde) {
			return getAgregado().getListaItemSerie_Gera(qtde);
		}
		public void setListaItemSerie_Gera(List<ItemSerie> lista) {
			getAgregado().setListaItemSerie_Gera(lista);
		}
		public void setListaItemSerie_GeraByDao(List<ItemSerie> lista) {
			getAgregado().setListaItemSerie_GeraByDao(lista);
		}
		public void criaVaziaListaItemSerie_Gera() {
			getAgregado().criaVaziaListaItemSerie_Gera();
		}
		
		public boolean existeListaItemSerie_Gera() {
			return getAgregado().existeListaItemSerie_Gera();
		}
 		
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_Executado() {
			return getAgregado().getCorrenteExecucaoItemSerie_Executado();
		}
		public void addListaExecucaoItemSerie_Executado(ExecucaoItemSerie item) {
			getAgregado().addListaExecucaoItemSerie_Executado(item);
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Executado() {
			return getAgregado().getListaExecucaoItemSerie_Executado();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_ExecutadoOriginal() {
			return getAgregado().getListaExecucaoItemSerie_ExecutadoOriginal();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Executado(int qtde) {
			return getAgregado().getListaExecucaoItemSerie_Executado(qtde);
		}
		public void setListaExecucaoItemSerie_Executado(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_Executado(lista);
		}
		public void setListaExecucaoItemSerie_ExecutadoByDao(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_ExecutadoByDao(lista);
		}
		public void criaVaziaListaExecucaoItemSerie_Executado() {
			getAgregado().criaVaziaListaExecucaoItemSerie_Executado();
		}
		
		public boolean existeListaExecucaoItemSerie_Executado() {
			return getAgregado().existeListaExecucaoItemSerie_Executado();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(ExercicioContract.COLUNA_ID_EXERCICIO, idExercicio);
    	valores.put(ExercicioContract.COLUNA_DESCRICAO_EXERCICIO, descricaoExercicio);
    	valores.put(ExercicioContract.COLUNA_IMAGEM, imagem);
    	valores.put(ExercicioContract.COLUNA_TITULO, titulo);
    	valores.put(ExercicioContract.COLUNA_SUBTITULO, subtitulo);
		valores.put(ExercicioContract.COLUNA_ID_GRUPO_MUSCULAR_P, idGrupoMuscularP);
	
		valores.put(ExercicioContract.COLUNA_ID_USUARIO_S, idUsuarioS);
	
		return valores;
  	}
}