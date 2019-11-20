
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.UsuarioPesquisaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.UsuarioPesquisaDBHelper;
//import coletapreco.servico.UsuarioPesquisaServico;
import br.com.digicom.log.DCLog;

public abstract class UsuarioPesquisaServico {

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

	private UsuarioPesquisaFiltro filtro = null;
	public UsuarioPesquisaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new UsuarioPesquisaFiltro();
		}
		return filtro;
	}

	public abstract UsuarioPesquisa getById(long id, Context contexto);
	public abstract UsuarioPesquisa getById(long id);
	public abstract List<UsuarioPesquisa> getAll(Context contexto);
	public abstract List<UsuarioPesquisa> getAllTela(Context contexto);
	public List<UsuarioPesquisa> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<UsuarioPesquisa> lista, Context contexto);
	public abstract void insertSincAll(List<UsuarioPesquisa> lista, Context contexto);
	
	// Itens Tela
	public abstract UsuarioPesquisa inicializaItemTela(DigicomContexto contexto);
	public abstract UsuarioPesquisa inicializaItemTela(UsuarioPesquisa itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(UsuarioPesquisa itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(UsuarioPesquisa itemTela, DigicomContexto contexto);
	
	public abstract UsuarioPesquisa ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(UsuarioPesquisa item);
	public abstract void insereParaSincronizacao(UsuarioPesquisa item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(Context contexto, long id);
	public abstract List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(Context contexto, long id, int qtde);
	public abstract List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(long id);
	public abstract List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(long id, int qtde);
	
	
	




}