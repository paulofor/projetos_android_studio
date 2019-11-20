
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.ModeloProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.ModeloProdutoDBHelper;
//import coletapreco.servico.ModeloProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class ModeloProdutoServico {

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

	private ModeloProdutoFiltro filtro = null;
	public ModeloProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ModeloProdutoFiltro();
		}
		return filtro;
	}

	public abstract ModeloProduto getById(long id, Context contexto);
	public abstract ModeloProduto getById(long id);
	public abstract List<ModeloProduto> getAll(Context contexto);
	public abstract List<ModeloProduto> getAllTela(Context contexto);
	public List<ModeloProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ModeloProduto> lista, Context contexto);
	public abstract void insertSincAll(List<ModeloProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract ModeloProduto inicializaItemTela(DigicomContexto contexto);
	public abstract ModeloProduto inicializaItemTela(ModeloProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ModeloProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ModeloProduto itemTela, DigicomContexto contexto);
	
	public abstract ModeloProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ModeloProduto item);
	public abstract void insereParaSincronizacao(ModeloProduto item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}