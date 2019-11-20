
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.ProdutoPedidoFornecedorFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.ProdutoPedidoFornecedorDBHelper;
//import repcom.servico.ProdutoPedidoFornecedorServico;
import br.com.digicom.log.DCLog;

public abstract class ProdutoPedidoFornecedorServico {

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

	private ProdutoPedidoFornecedorFiltro filtro = null;
	public ProdutoPedidoFornecedorFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ProdutoPedidoFornecedorFiltro();
		}
		return filtro;
	}

	public abstract ProdutoPedidoFornecedor getById(long id, Context contexto);
	public abstract ProdutoPedidoFornecedor getById(long id);
	public abstract List<ProdutoPedidoFornecedor> getAll(Context contexto);
	public abstract List<ProdutoPedidoFornecedor> getAllTela(Context contexto);
	public List<ProdutoPedidoFornecedor> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ProdutoPedidoFornecedor> lista, Context contexto);
	public abstract void insertSincAll(List<ProdutoPedidoFornecedor> lista, Context contexto);
	
	// Itens Tela
	public abstract ProdutoPedidoFornecedor inicializaItemTela(DigicomContexto contexto);
	public abstract ProdutoPedidoFornecedor inicializaItemTela(ProdutoPedidoFornecedor itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ProdutoPedidoFornecedor itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ProdutoPedidoFornecedor itemTela, DigicomContexto contexto);
	
	public abstract ProdutoPedidoFornecedor ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ProdutoPedidoFornecedor item);
	public abstract void insereParaSincronizacao(ProdutoPedidoFornecedor item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(Context contexto, long id);
	public abstract List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(Context contexto, long id, int qtde);
	public abstract List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(long id);
	public abstract List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(long id, int qtde);
	
	public abstract List<ProdutoPedidoFornecedor> getPorAssociadaProduto(Context contexto, long id);
	public abstract List<ProdutoPedidoFornecedor> getPorAssociadaProduto(Context contexto, long id, int qtde);
	public abstract List<ProdutoPedidoFornecedor> getPorAssociadaProduto(long id);
	public abstract List<ProdutoPedidoFornecedor> getPorAssociadaProduto(long id, int qtde);
	
	
	


	public abstract void atualizaRelacionamento(PedidoFornecedor item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}