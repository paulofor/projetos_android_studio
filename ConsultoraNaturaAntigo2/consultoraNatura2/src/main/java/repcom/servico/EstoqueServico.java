
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.EstoqueFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.EstoqueDBHelper;
//import repcom.servico.EstoqueServico;
import br.com.digicom.log.DCLog;

public abstract class EstoqueServico {

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

	private EstoqueFiltro filtro = null;
	public EstoqueFiltro getFiltro() {
		if (filtro==null) {
			filtro = new EstoqueFiltro();
		}
		return filtro;
	}

	public abstract Estoque getById(long id, Context contexto);
	public abstract Estoque getById(long id);
	public abstract List<Estoque> getAll(Context contexto);
	public abstract List<Estoque> getAllTela(Context contexto);
	public List<Estoque> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<Estoque> lista, Context contexto);
	public abstract void insertSincAll(List<Estoque> lista, Context contexto);
	
	// Itens Tela
	public abstract Estoque inicializaItemTela(DigicomContexto contexto);
	public abstract Estoque inicializaItemTela(Estoque itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(Estoque itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(Estoque itemTela, DigicomContexto contexto);
	
	public abstract Estoque ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(Estoque item);
	public abstract void insereParaSincronizacao(Estoque item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}