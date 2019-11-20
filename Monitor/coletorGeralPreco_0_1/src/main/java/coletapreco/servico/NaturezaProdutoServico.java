
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.NaturezaProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.NaturezaProdutoDBHelper;
//import coletapreco.servico.NaturezaProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class NaturezaProdutoServico {

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

	private NaturezaProdutoFiltro filtro = null;
	public NaturezaProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new NaturezaProdutoFiltro();
		}
		return filtro;
	}

	public abstract NaturezaProduto getById(long id, Context contexto);
	public abstract NaturezaProduto getById(long id);
	public abstract List<NaturezaProduto> getAll(Context contexto);
	public abstract List<NaturezaProduto> getAllTela(Context contexto);
	public List<NaturezaProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<NaturezaProduto> lista, Context contexto);
	public abstract void insertSincAll(List<NaturezaProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract NaturezaProduto inicializaItemTela(DigicomContexto contexto);
	public abstract NaturezaProduto inicializaItemTela(NaturezaProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(NaturezaProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(NaturezaProduto itemTela, DigicomContexto contexto);
	
	public abstract NaturezaProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(NaturezaProduto item);
	public abstract void insereParaSincronizacao(NaturezaProduto item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}