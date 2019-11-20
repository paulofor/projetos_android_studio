
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.CategoriaLojaProdutoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.CategoriaLojaProdutoDBHelper;
//import coletapreco.servico.CategoriaLojaProdutoServico;
import br.com.digicom.log.DCLog;

public abstract class CategoriaLojaProdutoServico {

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

	private CategoriaLojaProdutoFiltro filtro = null;
	public CategoriaLojaProdutoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new CategoriaLojaProdutoFiltro();
		}
		return filtro;
	}

	public abstract CategoriaLojaProduto getById(long id, Context contexto);
	public abstract CategoriaLojaProduto getById(long id);
	public abstract List<CategoriaLojaProduto> getAll(Context contexto);
	public abstract List<CategoriaLojaProduto> getAllTela(Context contexto);
	public List<CategoriaLojaProduto> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<CategoriaLojaProduto> lista, Context contexto);
	public abstract void insertSincAll(List<CategoriaLojaProduto> lista, Context contexto);
	
	// Itens Tela
	public abstract CategoriaLojaProduto inicializaItemTela(DigicomContexto contexto);
	public abstract CategoriaLojaProduto inicializaItemTela(CategoriaLojaProduto itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(CategoriaLojaProduto itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(CategoriaLojaProduto itemTela, DigicomContexto contexto);
	
	public abstract CategoriaLojaProduto ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(CategoriaLojaProduto item);
	public abstract void insereParaSincronizacao(CategoriaLojaProduto item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(Context contexto, long id);
	public abstract List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(Context contexto, long id, int qtde);
	public abstract List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(long id);
	public abstract List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(long id, int qtde);
	
	public abstract List<CategoriaLojaProduto> getPorReferenteAProduto(Context contexto, long id);
	public abstract List<CategoriaLojaProduto> getPorReferenteAProduto(Context contexto, long id, int qtde);
	public abstract List<CategoriaLojaProduto> getPorReferenteAProduto(long id);
	public abstract List<CategoriaLojaProduto> getPorReferenteAProduto(long id, int qtde);
	
	
	


	public abstract void atualizaRelacionamento(CategoriaLoja item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}