
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.PedidoFornecedorFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.PedidoFornecedorDBHelper;
//import repcom.servico.PedidoFornecedorServico;
import br.com.digicom.log.DCLog;

public abstract class PedidoFornecedorServico {

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

	private PedidoFornecedorFiltro filtro = null;
	public PedidoFornecedorFiltro getFiltro() {
		if (filtro==null) {
			filtro = new PedidoFornecedorFiltro();
		}
		return filtro;
	}

	public abstract PedidoFornecedor getById(long id, Context contexto);
	public abstract PedidoFornecedor getById(long id);
	public abstract List<PedidoFornecedor> getAll(Context contexto);
	public abstract List<PedidoFornecedor> getAllTela(Context contexto);
	public List<PedidoFornecedor> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<PedidoFornecedor> lista, Context contexto);
	public abstract void insertSincAll(List<PedidoFornecedor> lista, Context contexto);
	
	// Itens Tela
	public abstract PedidoFornecedor inicializaItemTela(DigicomContexto contexto);
	public abstract PedidoFornecedor inicializaItemTela(PedidoFornecedor itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(PedidoFornecedor itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(PedidoFornecedor itemTela, DigicomContexto contexto);
	
	public abstract PedidoFornecedor ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(PedidoFornecedor item);
	public abstract void insereParaSincronizacao(PedidoFornecedor item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}