
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.MarcaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.MarcaDBHelper;
//import coletapreco.servico.MarcaServico;
import br.com.digicom.log.DCLog;

public abstract class MarcaServico {

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

	private MarcaFiltro filtro = null;
	public MarcaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new MarcaFiltro();
		}
		return filtro;
	}

	public abstract Marca getById(long id, Context contexto);
	public abstract Marca getById(long id);
	public abstract List<Marca> getAll(Context contexto);
	public abstract List<Marca> getAllTela(Context contexto);
	public List<Marca> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<Marca> lista, Context contexto);
	public abstract void insertSincAll(List<Marca> lista, Context contexto);
	
	// Itens Tela
	public abstract Marca inicializaItemTela(DigicomContexto contexto);
	public abstract Marca inicializaItemTela(Marca itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(Marca itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(Marca itemTela, DigicomContexto contexto);
	
	public abstract Marca ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(Marca item);
	public abstract void insereParaSincronizacao(Marca item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}