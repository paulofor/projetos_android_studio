
package  treinoacademia.servico;

import java.util.List;
import treinoacademia.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import treinoacademia.servico.filtro.ItemSerieFiltro;
import br.com.digicom.video.DigicomVideoView;
//import treinoacademia.dao.base.ItemSerieDBHelper;
//import treinoacademia.servico.ItemSerieServico;
import br.com.digicom.log.DCLog;

public abstract class ItemSerieServico {

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

	private ItemSerieFiltro filtro = null;
	public ItemSerieFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ItemSerieFiltro();
		}
		return filtro;
	}

	public abstract ItemSerie getById(long id, Context contexto);
	public abstract ItemSerie getById(long id);
	public abstract List<ItemSerie> getAll(Context contexto);
	public abstract List<ItemSerie> getAllTela(Context contexto);
	public List<ItemSerie> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ItemSerie> lista, Context contexto);
	public abstract void insertSincAll(List<ItemSerie> lista, Context contexto);
	
	// Itens Tela
	public abstract ItemSerie inicializaItemTela(DigicomContexto contexto);
	public abstract ItemSerie inicializaItemTela(ItemSerie itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ItemSerie itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ItemSerie itemTela, DigicomContexto contexto);
	
	public abstract ItemSerie ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ItemSerie item);
	public abstract void insereParaSincronizacao(ItemSerie item);
	public abstract void excluiParaSincronizacao(ItemSerie item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<ItemSerie> getPorExecucaoDeExercicio(Context contexto, long id);
	public abstract List<ItemSerie> getPorExecucaoDeExercicio(Context contexto, long id, int qtde);
	public abstract List<ItemSerie> getPorExecucaoDeExercicio(long id);
	public abstract List<ItemSerie> getPorExecucaoDeExercicio(long id, int qtde);
	
	public abstract List<ItemSerie> getPorPertencenteASerieTreino(Context contexto, long id);
	public abstract List<ItemSerie> getPorPertencenteASerieTreino(Context contexto, long id, int qtde);
	public abstract List<ItemSerie> getPorPertencenteASerieTreino(long id);
	public abstract List<ItemSerie> getPorPertencenteASerieTreino(long id, int qtde);
	
	
	
	public abstract List<ItemSerie> ListaPorDiaComExecucao(DigicomContexto contexto );
	public abstract ItemSerie FinalizaItemSerie(DigicomContexto contexto );
	public abstract ItemSerie CarregaCompleto(DigicomContexto contexto );
	public abstract ItemSerie CarregaUltimasExecucoes(DigicomContexto contexto );
	public abstract ItemSerie AtualizaCarga(DigicomContexto contexto );




}