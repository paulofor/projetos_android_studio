
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
import br.com.lojadigicom.treinoacademia.modelo.agregado.GrupoMuscularAgregado;
import br.com.lojadigicom.treinoacademia.data.contract.GrupoMuscularContract;

public class GrupoMuscularVo implements GrupoMuscular  {

	public GrupoMuscularVo() {
  	}
  	
  	public long getIdObj()
    {
       return idGrupoMuscular;
    }

	 // ----- INICIO AGREGADO -----------------
	private GrupoMuscularAgregado agregado = null;
	private GrupoMuscularAgregado getAgregado() {
		if (agregado==null) {
			agregado = new GrupoMuscularAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------


	// ----- PROC VALOR - DERIVADA -----------
	
	
	
	// ---- FINAL PROC VALOR - DERIVADA ------

  	
  	
	private long idGrupoMuscular;
	public long getIdGrupoMuscular() {
		return idGrupoMuscular;
	}
	public void setIdGrupoMuscular(long _valor) {
		idGrupoMuscular = _valor;
	}


	private String nomeGrupo;
	public String getNomeGrupo() {
		return nomeGrupo;
	}
	public void setNomeGrupo(String _valor) {
		nomeGrupo = _valor;
	}


	private String imagemGrupo;
	public String getImagemGrupo() {
		return imagemGrupo;
	}
	public void setImagemGrupo(String _valor) {
		imagemGrupo = _valor;
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
	
		public Exercicio getCorrenteExercicio_ReferenteA() {
			return getAgregado().getCorrenteExercicio_ReferenteA();
		}
		public void addListaExercicio_ReferenteA(Exercicio item) {
			getAgregado().addListaExercicio_ReferenteA(item);
		}
		public List<Exercicio> getListaExercicio_ReferenteA() {
			return getAgregado().getListaExercicio_ReferenteA();
		}
		public List<Exercicio> getListaExercicio_ReferenteAOriginal() {
			return getAgregado().getListaExercicio_ReferenteAOriginal();
		}
		public List<Exercicio> getListaExercicio_ReferenteA(int qtde) {
			return getAgregado().getListaExercicio_ReferenteA(qtde);
		}
		public void setListaExercicio_ReferenteA(List<Exercicio> lista) {
			getAgregado().setListaExercicio_ReferenteA(lista);
		}
		public void setListaExercicio_ReferenteAByDao(List<Exercicio> lista) {
			getAgregado().setListaExercicio_ReferenteAByDao(lista);
		}
		public void criaVaziaListaExercicio_ReferenteA() {
			getAgregado().criaVaziaListaExercicio_ReferenteA();
		}
		
		public boolean existeListaExercicio_ReferenteA() {
			return getAgregado().existeListaExercicio_ReferenteA();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  	
  
	public ContentValues getContentValues() {
		ContentValues valores = new ContentValues();
	
    	valores.put(GrupoMuscularContract.COLUNA_ID_GRUPO_MUSCULAR, idGrupoMuscular);
    	valores.put(GrupoMuscularContract.COLUNA_NOME_GRUPO, nomeGrupo);
    	valores.put(GrupoMuscularContract.COLUNA_IMAGEM_GRUPO, imagemGrupo);
		return valores;
  	}
}