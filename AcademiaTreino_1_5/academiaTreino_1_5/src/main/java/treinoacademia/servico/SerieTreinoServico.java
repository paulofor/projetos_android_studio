
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import treinoacademia.servico.filtro.SerieTreinoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import treinoacademia.dao.base.SerieTreinoDBHelper;
//import treinoacademia.servico.SerieTreinoServico;
import br.com.digicom.log.DCLog;

public abstract class SerieTreinoServico {

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

	private SerieTreinoFiltro filtro = null;
	public SerieTreinoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new SerieTreinoFiltro();
		}
		return filtro;
	}

	public abstract SerieTreino getById(long id, Context contexto);
	public abstract SerieTreino getById(long id);
	public abstract List<SerieTreino> getAll(Context contexto);
	public abstract List<SerieTreino> getAllTela(Context contexto);
	public List<SerieTreino> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<SerieTreino> lista, Context contexto);
	public abstract void insertSincAll(List<SerieTreino> lista, Context contexto);
	
	// Itens Tela
	public abstract SerieTreino inicializaItemTela(DigicomContexto contexto);
	public abstract SerieTreino inicializaItemTela(SerieTreino itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(SerieTreino itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(SerieTreino itemTela, DigicomContexto contexto);
	
	public abstract SerieTreino ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(SerieTreino item);
	public abstract void insereParaSincronizacao(SerieTreino item);
	public abstract void excluiParaSincronizacao(SerieTreino item);
	public abstract void criaTabelaSincronizacao();
	
	
	
	public abstract SerieTreino ObtemProxima(DigicomContexto contexto );
	public abstract SerieTreino ObtemMontadaPorId(DigicomContexto contexto );
	public abstract SerieTreino CriaSerieCompleta(DigicomContexto contexto );
	public abstract SerieTreino CarregaItemSerie(DigicomContexto contexto );




}