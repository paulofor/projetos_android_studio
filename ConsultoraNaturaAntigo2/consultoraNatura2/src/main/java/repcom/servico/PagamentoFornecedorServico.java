
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.PagamentoFornecedorFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.PagamentoFornecedorDBHelper;
//import repcom.servico.PagamentoFornecedorServico;
import br.com.digicom.log.DCLog;

public abstract class PagamentoFornecedorServico {

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

	private PagamentoFornecedorFiltro filtro = null;
	public PagamentoFornecedorFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PagamentoFornecedorFiltro();
		}
		return filtro;
	}

	public abstract PagamentoFornecedor getById(long id, Context contexto);
	public abstract PagamentoFornecedor getById(long id);
	public abstract List<PagamentoFornecedor> getAll(Context contexto);
	public abstract List<PagamentoFornecedor> getAllTela(Context contexto);
	public List<PagamentoFornecedor> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<PagamentoFornecedor> lista, Context contexto);
	public abstract void insertSincAll(List<PagamentoFornecedor> lista, Context contexto);
	
	// Itens Tela
	public abstract PagamentoFornecedor inicializaItemTela(DigicomContexto contexto);
	public abstract PagamentoFornecedor inicializaItemTela(PagamentoFornecedor itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(PagamentoFornecedor itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(PagamentoFornecedor itemTela, DigicomContexto contexto);
	
	public abstract PagamentoFornecedor ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(PagamentoFornecedor item);
	public abstract void insereParaSincronizacao(PagamentoFornecedor item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}