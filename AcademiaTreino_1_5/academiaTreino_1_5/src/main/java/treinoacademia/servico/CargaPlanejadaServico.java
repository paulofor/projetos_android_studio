
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import treinoacademia.servico.filtro.CargaPlanejadaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import treinoacademia.dao.base.CargaPlanejadaDBHelper;
//import treinoacademia.servico.CargaPlanejadaServico;
import br.com.digicom.log.DCLog;

public abstract class CargaPlanejadaServico {

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

	private CargaPlanejadaFiltro filtro = null;
	public CargaPlanejadaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new CargaPlanejadaFiltro();
		}
		return filtro;
	}

	public abstract CargaPlanejada getById(long id, Context contexto);
	public abstract CargaPlanejada getById(long id);
	public abstract List<CargaPlanejada> getAll(Context contexto);
	public abstract List<CargaPlanejada> getAllTela(Context contexto);
	public List<CargaPlanejada> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<CargaPlanejada> lista, Context contexto);
	public abstract void insertSincAll(List<CargaPlanejada> lista, Context contexto);
	
	// Itens Tela
	public abstract CargaPlanejada inicializaItemTela(DigicomContexto contexto);
	public abstract CargaPlanejada inicializaItemTela(CargaPlanejada itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(CargaPlanejada itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(CargaPlanejada itemTela, DigicomContexto contexto);
	
	public abstract CargaPlanejada ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(CargaPlanejada item);
	public abstract void insereParaSincronizacao(CargaPlanejada item);
	public abstract void excluiParaSincronizacao(CargaPlanejada item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<CargaPlanejada> getPorReferenteAItemSerie(Context contexto, long id);
	public abstract List<CargaPlanejada> getPorReferenteAItemSerie(Context contexto, long id, int qtde);
	public abstract List<CargaPlanejada> getPorReferenteAItemSerie(long id);
	public abstract List<CargaPlanejada> getPorReferenteAItemSerie(long id, int qtde);
	
	
	
	public abstract List<CargaPlanejada> ListaCargaPorExercicio(DigicomContexto contexto );
	public abstract List<CargaPlanejada> ListaCargaAtivaPorExercicio(DigicomContexto contexto );




}