
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import treinoacademia.servico.filtro.ExecucaoItemSerieFiltro;
import br.com.digicom.video.DigicomVideoView;
//import treinoacademia.dao.base.ExecucaoItemSerieDBHelper;
//import treinoacademia.servico.ExecucaoItemSerieServico;
import br.com.digicom.log.DCLog;

public abstract class ExecucaoItemSerieServico {

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

	private ExecucaoItemSerieFiltro filtro = null;
	public ExecucaoItemSerieFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ExecucaoItemSerieFiltro();
		}
		return filtro;
	}

	public abstract ExecucaoItemSerie getById(long id, Context contexto);
	public abstract ExecucaoItemSerie getById(long id);
	public abstract List<ExecucaoItemSerie> getAll(Context contexto);
	public abstract List<ExecucaoItemSerie> getAllTela(Context contexto);
	public List<ExecucaoItemSerie> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ExecucaoItemSerie> lista, Context contexto);
	public abstract void insertSincAll(List<ExecucaoItemSerie> lista, Context contexto);
	
	// Itens Tela
	public abstract ExecucaoItemSerie inicializaItemTela(DigicomContexto contexto);
	public abstract ExecucaoItemSerie inicializaItemTela(ExecucaoItemSerie itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ExecucaoItemSerie itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ExecucaoItemSerie itemTela, DigicomContexto contexto);
	
	public abstract ExecucaoItemSerie ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ExecucaoItemSerie item);
	public abstract void insereParaSincronizacao(ExecucaoItemSerie item);
	public abstract void excluiParaSincronizacao(ExecucaoItemSerie item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<ExecucaoItemSerie> getPorReferenteAItemSerie(Context contexto, long id);
	public abstract List<ExecucaoItemSerie> getPorReferenteAItemSerie(Context contexto, long id, int qtde);
	public abstract List<ExecucaoItemSerie> getPorReferenteAItemSerie(long id);
	public abstract List<ExecucaoItemSerie> getPorReferenteAItemSerie(long id, int qtde);
	
	public abstract List<ExecucaoItemSerie> getPorEmDiaTreino(Context contexto, long id);
	public abstract List<ExecucaoItemSerie> getPorEmDiaTreino(Context contexto, long id, int qtde);
	public abstract List<ExecucaoItemSerie> getPorEmDiaTreino(long id);
	public abstract List<ExecucaoItemSerie> getPorEmDiaTreino(long id, int qtde);
	
	public abstract List<ExecucaoItemSerie> getPorReferenteAExercicio(Context contexto, long id);
	public abstract List<ExecucaoItemSerie> getPorReferenteAExercicio(Context contexto, long id, int qtde);
	public abstract List<ExecucaoItemSerie> getPorReferenteAExercicio(long id);
	public abstract List<ExecucaoItemSerie> getPorReferenteAExercicio(long id, int qtde);
	
	
	
	public abstract List<ExecucaoItemSerie> ObtemPorDiaItemSerie(DigicomContexto contexto );
	public abstract List<ExecucaoItemSerie> UltimasExecucoesUsuarioExercicio(DigicomContexto contexto );
	public abstract List<ExecucaoItemSerie> UltimasExecucoesItemSerie(DigicomContexto contexto );
	public abstract List<ExecucaoItemSerie> CarregaCompletoPorDia(DigicomContexto contexto );
	public abstract ExecucaoItemSerie PrimeiraPorDia(DigicomContexto contexto );
	public abstract ExecucaoItemSerie UltimaPorDia(DigicomContexto contexto );
	public abstract ExecucaoItemSerie PrimeiraPorSerie(DigicomContexto contexto );




}