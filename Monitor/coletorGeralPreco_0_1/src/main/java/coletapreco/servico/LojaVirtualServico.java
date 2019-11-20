
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.LojaVirtualFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.LojaVirtualDBHelper;
//import coletapreco.servico.LojaVirtualServico;
import br.com.digicom.log.DCLog;

public abstract class LojaVirtualServico {

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

	private LojaVirtualFiltro filtro = null;
	public LojaVirtualFiltro getFiltro() {
		if (filtro==null) {
			filtro = new LojaVirtualFiltro();
		}
		return filtro;
	}

	public abstract LojaVirtual getById(long id, Context contexto);
	public abstract LojaVirtual getById(long id);
	public abstract List<LojaVirtual> getAll(Context contexto);
	public abstract List<LojaVirtual> getAllTela(Context contexto);
	public List<LojaVirtual> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<LojaVirtual> lista, Context contexto);
	public abstract void insertSincAll(List<LojaVirtual> lista, Context contexto);
	
	// Itens Tela
	public abstract LojaVirtual inicializaItemTela(DigicomContexto contexto);
	public abstract LojaVirtual inicializaItemTela(LojaVirtual itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(LojaVirtual itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(LojaVirtual itemTela, DigicomContexto contexto);
	
	public abstract LojaVirtual ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(LojaVirtual item);
	public abstract void insereParaSincronizacao(LojaVirtual item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}