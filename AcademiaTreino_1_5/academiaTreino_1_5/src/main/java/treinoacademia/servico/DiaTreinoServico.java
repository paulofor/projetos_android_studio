
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import treinoacademia.servico.filtro.DiaTreinoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import treinoacademia.dao.base.DiaTreinoDBHelper;
//import treinoacademia.servico.DiaTreinoServico;
import br.com.digicom.log.DCLog;

public abstract class DiaTreinoServico {

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

	private DiaTreinoFiltro filtro = null;
	public DiaTreinoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new DiaTreinoFiltro();
		}
		return filtro;
	}

	public abstract DiaTreino getById(long id, Context contexto);
	public abstract DiaTreino getById(long id);
	public abstract List<DiaTreino> getAll(Context contexto);
	public abstract List<DiaTreino> getAllTela(Context contexto);
	public List<DiaTreino> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<DiaTreino> lista, Context contexto);
	public abstract void insertSincAll(List<DiaTreino> lista, Context contexto);
	
	// Itens Tela
	public abstract DiaTreino inicializaItemTela(DigicomContexto contexto);
	public abstract DiaTreino inicializaItemTela(DiaTreino itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(DiaTreino itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(DiaTreino itemTela, DigicomContexto contexto);
	
	public abstract DiaTreino ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(DiaTreino item);
	public abstract void insereParaSincronizacao(DiaTreino item);
	public abstract void excluiParaSincronizacao(DiaTreino item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<DiaTreino> getPorSerieDiaSerieTreino(Context contexto, long id);
	public abstract List<DiaTreino> getPorSerieDiaSerieTreino(Context contexto, long id, int qtde);
	public abstract List<DiaTreino> getPorSerieDiaSerieTreino(long id);
	public abstract List<DiaTreino> getPorSerieDiaSerieTreino(long id, int qtde);
	
	
	
	public abstract DiaTreino EncerraDiaTreino(DigicomContexto contexto );
	public abstract DiaTreino ObtemPorData(DigicomContexto contexto );
	public abstract DiaTreino LimpezaBase(DigicomContexto contexto );
	public abstract List<DiaTreino> HistoricoExecucaoPorIdExercicio(DigicomContexto contexto );
	public abstract List<DiaTreino> TreinosPosDataPesquisa(DigicomContexto contexto );




}