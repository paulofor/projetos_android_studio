
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.ProdutoVendaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.ProdutoVendaDBHelper;
//import repcom.servico.ProdutoVendaServico;
import br.com.digicom.log.DCLog;

public abstract class ProdutoVendaServico {

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

	private ProdutoVendaFiltro filtro = null;
	public ProdutoVendaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ProdutoVendaFiltro();
		}
		return filtro;
	}

	public abstract ProdutoVenda getById(long id, Context contexto);
	public abstract ProdutoVenda getById(long id);
	public abstract List<ProdutoVenda> getAll(Context contexto);
	public abstract List<ProdutoVenda> getAllTela(Context contexto);
	public List<ProdutoVenda> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ProdutoVenda> lista, Context contexto);
	public abstract void insertSincAll(List<ProdutoVenda> lista, Context contexto);
	
	// Itens Tela
	public abstract ProdutoVenda inicializaItemTela(DigicomContexto contexto);
	public abstract ProdutoVenda inicializaItemTela(ProdutoVenda itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ProdutoVenda itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ProdutoVenda itemTela, DigicomContexto contexto);
	
	public abstract ProdutoVenda ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ProdutoVenda item);
	public abstract void insereParaSincronizacao(ProdutoVenda item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<ProdutoVenda> getPorAssociadaProduto(Context contexto, long id);
	public abstract List<ProdutoVenda> getPorAssociadaProduto(Context contexto, long id, int qtde);
	public abstract List<ProdutoVenda> getPorAssociadaProduto(long id);
	public abstract List<ProdutoVenda> getPorAssociadaProduto(long id, int qtde);
	
	public abstract List<ProdutoVenda> getPorAssociadaVenda(Context contexto, long id);
	public abstract List<ProdutoVenda> getPorAssociadaVenda(Context contexto, long id, int qtde);
	public abstract List<ProdutoVenda> getPorAssociadaVenda(long id);
	public abstract List<ProdutoVenda> getPorAssociadaVenda(long id, int qtde);
	
	
	


	public abstract void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(Venda item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}