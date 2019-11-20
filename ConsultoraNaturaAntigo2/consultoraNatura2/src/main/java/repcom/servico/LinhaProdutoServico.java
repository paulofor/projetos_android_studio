
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.LinhaProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.LinhaProdutoDBHelper;
//import repcom.servico.LinhaProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class LinhaProdutoServico {

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

	private LinhaProdutoFiltro filtro = null;
	public LinhaProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new LinhaProdutoFiltro();
		}
		return filtro;
	}

	public abstract LinhaProduto getById(long id, Context contexto);
	public abstract LinhaProduto getById(long id);
	public abstract List<LinhaProduto> getAll(Context contexto);
	public abstract List<LinhaProduto> getAllTela(Context contexto);
	public List<LinhaProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<LinhaProduto> lista, Context contexto);
	public abstract void insertSincAll(List<LinhaProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract LinhaProduto inicializaItemTela(DigicomContexto contexto);
	public abstract LinhaProduto inicializaItemTela(LinhaProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(LinhaProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(LinhaProduto itemTela, DigicomContexto contexto);
	
	public abstract LinhaProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(LinhaProduto item);
	public abstract void insereParaSincronizacao(LinhaProduto item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}