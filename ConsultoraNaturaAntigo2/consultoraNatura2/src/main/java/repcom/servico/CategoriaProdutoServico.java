
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.CategoriaProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.CategoriaProdutoDBHelper;
//import repcom.servico.CategoriaProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class CategoriaProdutoServico {

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

	private CategoriaProdutoFiltro filtro = null;
	public CategoriaProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new CategoriaProdutoFiltro();
		}
		return filtro;
	}

	public abstract CategoriaProduto getById(long id, Context contexto);
	public abstract CategoriaProduto getById(long id);
	public abstract List<CategoriaProduto> getAll(Context contexto);
	public abstract List<CategoriaProduto> getAllTela(Context contexto);
	public List<CategoriaProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<CategoriaProduto> lista, Context contexto);
	public abstract void insertSincAll(List<CategoriaProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract CategoriaProduto inicializaItemTela(DigicomContexto contexto);
	public abstract CategoriaProduto inicializaItemTela(CategoriaProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(CategoriaProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(CategoriaProduto itemTela, DigicomContexto contexto);
	
	public abstract CategoriaProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(CategoriaProduto item);
	public abstract void insereParaSincronizacao(CategoriaProduto item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<CategoriaProduto> getPorPaiCategoriaProduto(Context contexto, long id);
	public abstract List<CategoriaProduto> getPorPaiCategoriaProduto(Context contexto, long id, int qtde);
	public abstract List<CategoriaProduto> getPorPaiCategoriaProduto(long id);
	public abstract List<CategoriaProduto> getPorPaiCategoriaProduto(long id, int qtde);
	
	
	
	public abstract List<CategoriaProduto> ListaNivel0(DigicomContexto contexto );




}