
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.ProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.ProdutoDBHelper;
//import repcom.servico.ProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class ProdutoServico {

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

	private ProdutoFiltro filtro = null;
	public ProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ProdutoFiltro();
		}
		return filtro;
	}

	public abstract Produto getById(long id, Context contexto);
	public abstract Produto getById(long id);
	public abstract List<Produto> getAll(Context contexto);
	public abstract List<Produto> getAllTela(Context contexto);
	public List<Produto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<Produto> lista, Context contexto);
	public abstract void insertSincAll(List<Produto> lista, Context contexto);
	
	// Itens Tela
	public abstract Produto inicializaItemTela(DigicomContexto contexto);
	public abstract Produto inicializaItemTela(Produto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(Produto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(Produto itemTela, DigicomContexto contexto);
	
	public abstract Produto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(Produto item);
	public abstract void insereParaSincronizacao(Produto item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<Produto> getPorEstaEmLinhaProduto(Context contexto, long id);
	public abstract List<Produto> getPorEstaEmLinhaProduto(Context contexto, long id, int qtde);
	public abstract List<Produto> getPorEstaEmLinhaProduto(long id);
	public abstract List<Produto> getPorEstaEmLinhaProduto(long id, int qtde);
	
	
	
	public abstract List<Produto> ListaPorIdCategoria(DigicomContexto contexto );
	public abstract List<Produto> PesquisaTrechoNome(DigicomContexto contexto );




}