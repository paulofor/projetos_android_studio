
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.UsuarioFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.UsuarioDBHelper;
//import coletapreco.servico.UsuarioServico;
import br.com.digicom.log.DCLog;

public abstract class UsuarioServico {

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

	private UsuarioFiltro filtro = null;
	public UsuarioFiltro getFiltro() {
		if (filtro==null) {
			filtro = new UsuarioFiltro();
		}
		return filtro;
	}

	public abstract Usuario getById(long id, Context contexto);
	public abstract Usuario getById(long id);
	public abstract List<Usuario> getAll(Context contexto);
	public abstract List<Usuario> getAllTela(Context contexto);
	public List<Usuario> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract Usuario getCorrente();
	
	public abstract void insertAll(List<Usuario> lista, Context contexto);
	public abstract void insertSincAll(List<Usuario> lista, Context contexto);
	
	// Itens Tela
	public abstract Usuario inicializaItemTela(DigicomContexto contexto);
	public abstract Usuario inicializaItemTela(Usuario itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(Usuario itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(Usuario itemTela, DigicomContexto contexto);
	
	public abstract Usuario ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(Usuario item);
	public abstract void insereParaSincronizacao(Usuario item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}