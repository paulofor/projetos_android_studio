
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.CategoriaLojaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.CategoriaLojaDBHelper;
//import coletapreco.servico.CategoriaLojaServico;
import br.com.digicom.log.DCLog;

public abstract class CategoriaLojaServico {

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

	private CategoriaLojaFiltro filtro = null;
	public CategoriaLojaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new CategoriaLojaFiltro();
		}
		return filtro;
	}

	public abstract CategoriaLoja getById(long id, Context contexto);
	public abstract CategoriaLoja getById(long id);
	public abstract List<CategoriaLoja> getAll(Context contexto);
	public abstract List<CategoriaLoja> getAllTela(Context contexto);
	public List<CategoriaLoja> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<CategoriaLoja> lista, Context contexto);
	public abstract void insertSincAll(List<CategoriaLoja> lista, Context contexto);
	
	// Itens Tela
	public abstract CategoriaLoja inicializaItemTela(DigicomContexto contexto);
	public abstract CategoriaLoja inicializaItemTela(CategoriaLoja itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(CategoriaLoja itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(CategoriaLoja itemTela, DigicomContexto contexto);
	
	public abstract CategoriaLoja ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(CategoriaLoja item);
	public abstract void insereParaSincronizacao(CategoriaLoja item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<CategoriaLoja> getPorFilhoCategoriaLoja(Context contexto, long id);
	public abstract List<CategoriaLoja> getPorFilhoCategoriaLoja(Context contexto, long id, int qtde);
	public abstract List<CategoriaLoja> getPorFilhoCategoriaLoja(long id);
	public abstract List<CategoriaLoja> getPorFilhoCategoriaLoja(long id, int qtde);
	
	public abstract List<CategoriaLoja> getPorReferenteANaturezaProduto(Context contexto, long id);
	public abstract List<CategoriaLoja> getPorReferenteANaturezaProduto(Context contexto, long id, int qtde);
	public abstract List<CategoriaLoja> getPorReferenteANaturezaProduto(long id);
	public abstract List<CategoriaLoja> getPorReferenteANaturezaProduto(long id, int qtde);
	
	public abstract List<CategoriaLoja> getPorPertenceALojaVirtual(Context contexto, long id);
	public abstract List<CategoriaLoja> getPorPertenceALojaVirtual(Context contexto, long id, int qtde);
	public abstract List<CategoriaLoja> getPorPertenceALojaVirtual(long id);
	public abstract List<CategoriaLoja> getPorPertenceALojaVirtual(long id, int qtde);
	
	
	




}