
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.DispositivoUsuarioFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.DispositivoUsuarioDBHelper;
//import coletapreco.servico.DispositivoUsuarioServico;
import br.com.digicom.log.DCLog;

public abstract class DispositivoUsuarioServico {

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

	private DispositivoUsuarioFiltro filtro = null;
	public DispositivoUsuarioFiltro getFiltro() {
		if (filtro==null) {
			filtro = new DispositivoUsuarioFiltro();
		}
		return filtro;
	}

	public abstract DispositivoUsuario getById(long id, Context contexto);
	public abstract DispositivoUsuario getById(long id);
	public abstract List<DispositivoUsuario> getAll(Context contexto);
	public abstract List<DispositivoUsuario> getAllTela(Context contexto);
	public List<DispositivoUsuario> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract DispositivoUsuario getCorrente();
	
	public abstract void insertAll(List<DispositivoUsuario> lista, Context contexto);
	public abstract void insertSincAll(List<DispositivoUsuario> lista, Context contexto);
	
	// Itens Tela
	public abstract DispositivoUsuario inicializaItemTela(DigicomContexto contexto);
	public abstract DispositivoUsuario inicializaItemTela(DispositivoUsuario itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(DispositivoUsuario itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(DispositivoUsuario itemTela, DigicomContexto contexto);
	
	public abstract DispositivoUsuario ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(DispositivoUsuario item);
	public abstract void insereParaSincronizacao(DispositivoUsuario item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<DispositivoUsuario> getPorReferenteAUsuario(Context contexto, long id);
	public abstract List<DispositivoUsuario> getPorReferenteAUsuario(Context contexto, long id, int qtde);
	public abstract List<DispositivoUsuario> getPorReferenteAUsuario(long id);
	public abstract List<DispositivoUsuario> getPorReferenteAUsuario(long id, int qtde);
	
	
	




}