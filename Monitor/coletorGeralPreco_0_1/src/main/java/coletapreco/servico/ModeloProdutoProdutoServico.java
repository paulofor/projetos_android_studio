
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.ModeloProdutoProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.ModeloProdutoProdutoDBHelper;
//import coletapreco.servico.ModeloProdutoProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class ModeloProdutoProdutoServico {

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

	private ModeloProdutoProdutoFiltro filtro = null;
	public ModeloProdutoProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ModeloProdutoProdutoFiltro();
		}
		return filtro;
	}

	public abstract ModeloProdutoProduto getById(long id, Context contexto);
	public abstract ModeloProdutoProduto getById(long id);
	public abstract List<ModeloProdutoProduto> getAll(Context contexto);
	public abstract List<ModeloProdutoProduto> getAllTela(Context contexto);
	public List<ModeloProdutoProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ModeloProdutoProduto> lista, Context contexto);
	public abstract void insertSincAll(List<ModeloProdutoProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract ModeloProdutoProduto inicializaItemTela(DigicomContexto contexto);
	public abstract ModeloProdutoProduto inicializaItemTela(ModeloProdutoProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ModeloProdutoProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ModeloProdutoProduto itemTela, DigicomContexto contexto);
	
	public abstract ModeloProdutoProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ModeloProdutoProduto item);
	public abstract void insereParaSincronizacao(ModeloProdutoProduto item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<ModeloProdutoProduto> getPorReferenteAModeloProduto(Context contexto, long id);
	public abstract List<ModeloProdutoProduto> getPorReferenteAModeloProduto(Context contexto, long id, int qtde);
	public abstract List<ModeloProdutoProduto> getPorReferenteAModeloProduto(long id);
	public abstract List<ModeloProdutoProduto> getPorReferenteAModeloProduto(long id, int qtde);
	
	public abstract List<ModeloProdutoProduto> getPorReferenteAProduto(Context contexto, long id);
	public abstract List<ModeloProdutoProduto> getPorReferenteAProduto(Context contexto, long id, int qtde);
	public abstract List<ModeloProdutoProduto> getPorReferenteAProduto(long id);
	public abstract List<ModeloProdutoProduto> getPorReferenteAProduto(long id, int qtde);
	
	
	


	public abstract void atualizaRelacionamento(ModeloProduto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}