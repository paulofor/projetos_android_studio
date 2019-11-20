
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.PrecoProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.PrecoProdutoDBHelper;
//import repcom.servico.PrecoProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class PrecoProdutoServico {

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

	private PrecoProdutoFiltro filtro = null;
	public PrecoProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PrecoProdutoFiltro();
		}
		return filtro;
	}

	public abstract PrecoProduto getById(long id, Context contexto);
	public abstract PrecoProduto getById(long id);
	public abstract List<PrecoProduto> getAll(Context contexto);
	public abstract List<PrecoProduto> getAllTela(Context contexto);
	public List<PrecoProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<PrecoProduto> lista, Context contexto);
	public abstract void insertSincAll(List<PrecoProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract PrecoProduto inicializaItemTela(DigicomContexto contexto);
	public abstract PrecoProduto inicializaItemTela(PrecoProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(PrecoProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(PrecoProduto itemTela, DigicomContexto contexto);
	
	public abstract PrecoProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(PrecoProduto item);
	public abstract void insereParaSincronizacao(PrecoProduto item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<PrecoProduto> getPorPertenceAProduto(Context contexto, long id);
	public abstract List<PrecoProduto> getPorPertenceAProduto(Context contexto, long id, int qtde);
	public abstract List<PrecoProduto> getPorPertenceAProduto(long id);
	public abstract List<PrecoProduto> getPorPertenceAProduto(long id, int qtde);
	
	
	




}