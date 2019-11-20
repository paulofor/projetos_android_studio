
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import treinoacademia.servico.filtro.ExercicioFiltro;
import br.com.digicom.video.DigicomVideoView;
//import treinoacademia.dao.base.ExercicioDBHelper;
//import treinoacademia.servico.ExercicioServico;
import br.com.digicom.log.DCLog;

public abstract class ExercicioServico {

/*
	protected Context ctx = null;
	public void setContext(Context context) {
		ctx = context;
	}
*/
	// Alterar para o Impl ? ( Decidir ate 29-05-2014 )
	private DigicomVideoView video = null;
	public void setVideo(DigicomVideoView video) {
		this.video = video;
	}
	


	protected void reproduzVideo(String arquivo, int posIni, int posFim) {
		DCLog.d(DigicomVideoView.TAG, this, ": Arquivo:" + arquivo);
		try {
			if (video==null) throw new Exception("video=null");
			video.setVideoPath(arquivo);
			video.start();
			video.requestFocus();
		} catch (Exception e) {
			DCLog.e(DigicomVideoView.TAG, this, e);
		}
	}

	private ExercicioFiltro filtro = null;
	public ExercicioFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ExercicioFiltro();
		}
		return filtro;
	}

	public abstract Exercicio getById(long id, Context contexto);
	public abstract Exercicio getById(long id);
	public abstract List<Exercicio> getAll(Context contexto);
	public abstract List<Exercicio> getAllTela(Context contexto);
	public List<Exercicio> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<Exercicio> lista, Context contexto);
	public abstract void insertSincAll(List<Exercicio> lista, Context contexto);
	
	// Itens Tela
	public abstract Exercicio inicializaItemTela(DigicomContexto contexto);
	public abstract Exercicio inicializaItemTela(Exercicio itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(Exercicio itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(Exercicio itemTela, DigicomContexto contexto);
	
	public abstract Exercicio ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(Exercicio item);
	public abstract void insereParaSincronizacao(Exercicio item);
	public abstract void excluiParaSincronizacao(Exercicio item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<Exercicio> getPorParaGrupoMuscular(Context contexto, long id);
	public abstract List<Exercicio> getPorParaGrupoMuscular(Context contexto, long id, int qtde);
	public abstract List<Exercicio> getPorParaGrupoMuscular(long id);
	public abstract List<Exercicio> getPorParaGrupoMuscular(long id, int qtde);
	
	
	
	public abstract List<Exercicio> ListaAtivosNoMomento(DigicomContexto contexto );




}