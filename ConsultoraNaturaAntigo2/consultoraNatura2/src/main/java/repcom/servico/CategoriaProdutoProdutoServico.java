
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.CategoriaProdutoProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.CategoriaProdutoProdutoDBHelper;
//import repcom.servico.CategoriaProdutoProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class CategoriaProdutoProdutoServico {

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

	private CategoriaProdutoProdutoFiltro filtro = null;
	public CategoriaProdutoProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new CategoriaProdutoProdutoFiltro();
		}
		return filtro;
	}

	public abstract CategoriaProdutoProduto getById(long id, Context contexto);
	public abstract CategoriaProdutoProduto getById(long id);
	public abstract List<CategoriaProdutoProduto> getAll(Context contexto);
	public abstract List<CategoriaProdutoProduto> getAllTela(Context contexto);
	public List<CategoriaProdutoProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<CategoriaProdutoProduto> lista, Context contexto);
	public abstract void insertSincAll(List<CategoriaProdutoProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract CategoriaProdutoProduto inicializaItemTela(DigicomContexto contexto);
	public abstract CategoriaProdutoProduto inicializaItemTela(CategoriaProdutoProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(CategoriaProdutoProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(CategoriaProdutoProduto itemTela, DigicomContexto contexto);
	
	public abstract CategoriaProdutoProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(CategoriaProdutoProduto item);
	public abstract void insereParaSincronizacao(CategoriaProdutoProduto item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(Context contexto, long id);
	public abstract List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(Context contexto, long id, int qtde);
	public abstract List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(long id);
	public abstract List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(long id, int qtde);
	
	public abstract List<CategoriaProdutoProduto> getPorReferenteAProduto(Context contexto, long id);
	public abstract List<CategoriaProdutoProduto> getPorReferenteAProduto(Context contexto, long id, int qtde);
	public abstract List<CategoriaProdutoProduto> getPorReferenteAProduto(long id);
	public abstract List<CategoriaProdutoProduto> getPorReferenteAProduto(long id, int qtde);
	
	
	


	public abstract void atualizaRelacionamento(CategoriaProduto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}