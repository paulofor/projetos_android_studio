
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.PalavraFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.PalavraDBHelper;
//import coletapreco.servico.PalavraServico;
import br.com.digicom.log.DCLog;

public abstract class PalavraServico {

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

	private PalavraFiltro filtro = null;
	public PalavraFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PalavraFiltro();
		}
		return filtro;
	}

	public abstract Palavra getById(long id, Context contexto);
	public abstract Palavra getById(long id);
	public abstract List<Palavra> getAll(Context contexto);
	public abstract List<Palavra> getAllTela(Context contexto);
	public List<Palavra> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<Palavra> lista, Context contexto);
	public abstract void insertSincAll(List<Palavra> lista, Context contexto);
	
	// Itens Tela
	public abstract Palavra inicializaItemTela(DigicomContexto contexto);
	public abstract Palavra inicializaItemTela(Palavra itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(Palavra itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(Palavra itemTela, DigicomContexto contexto);
	
	public abstract Palavra ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(Palavra item);
	public abstract void insereParaSincronizacao(Palavra item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}