
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.PalavraProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.PalavraProdutoDBHelper;
//import coletapreco.servico.PalavraProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class PalavraProdutoServico {

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

	private PalavraProdutoFiltro filtro = null;
	public PalavraProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PalavraProdutoFiltro();
		}
		return filtro;
	}

	public abstract PalavraProduto getById(long id, Context contexto);
	public abstract PalavraProduto getById(long id);
	public abstract List<PalavraProduto> getAll(Context contexto);
	public abstract List<PalavraProduto> getAllTela(Context contexto);
	public List<PalavraProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<PalavraProduto> lista, Context contexto);
	public abstract void insertSincAll(List<PalavraProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract PalavraProduto inicializaItemTela(DigicomContexto contexto);
	public abstract PalavraProduto inicializaItemTela(PalavraProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(PalavraProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(PalavraProduto itemTela, DigicomContexto contexto);
	
	public abstract PalavraProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(PalavraProduto item);
	public abstract void insereParaSincronizacao(PalavraProduto item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<PalavraProduto> getPorRelaciondoAPalavra(Context contexto, long id);
	public abstract List<PalavraProduto> getPorRelaciondoAPalavra(Context contexto, long id, int qtde);
	public abstract List<PalavraProduto> getPorRelaciondoAPalavra(long id);
	public abstract List<PalavraProduto> getPorRelaciondoAPalavra(long id, int qtde);
	
	public abstract List<PalavraProduto> getPorRelaciondoAProduto(Context contexto, long id);
	public abstract List<PalavraProduto> getPorRelaciondoAProduto(Context contexto, long id, int qtde);
	public abstract List<PalavraProduto> getPorRelaciondoAProduto(long id);
	public abstract List<PalavraProduto> getPorRelaciondoAProduto(long id, int qtde);
	
	
	


	public abstract void atualizaRelacionamento(Palavra item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}