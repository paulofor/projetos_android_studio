
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.LojaNaturezaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.LojaNaturezaDBHelper;
//import coletapreco.servico.LojaNaturezaServico;
import br.com.digicom.log.DCLog;

public abstract class LojaNaturezaServico {

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

	private LojaNaturezaFiltro filtro = null;
	public LojaNaturezaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new LojaNaturezaFiltro();
		}
		return filtro;
	}

	public abstract LojaNatureza getById(long id, Context contexto);
	public abstract LojaNatureza getById(long id);
	public abstract List<LojaNatureza> getAll(Context contexto);
	public abstract List<LojaNatureza> getAllTela(Context contexto);
	public List<LojaNatureza> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<LojaNatureza> lista, Context contexto);
	public abstract void insertSincAll(List<LojaNatureza> lista, Context contexto);
	
	// Itens Tela
	public abstract LojaNatureza inicializaItemTela(DigicomContexto contexto);
	public abstract LojaNatureza inicializaItemTela(LojaNatureza itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(LojaNatureza itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(LojaNatureza itemTela, DigicomContexto contexto);
	
	public abstract LojaNatureza ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(LojaNatureza item);
	public abstract void insereParaSincronizacao(LojaNatureza item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<LojaNatureza> getPorReferenteALojaVirtual(Context contexto, long id);
	public abstract List<LojaNatureza> getPorReferenteALojaVirtual(Context contexto, long id, int qtde);
	public abstract List<LojaNatureza> getPorReferenteALojaVirtual(long id);
	public abstract List<LojaNatureza> getPorReferenteALojaVirtual(long id, int qtde);
	
	public abstract List<LojaNatureza> getPorReferenteANaturezaProduto(Context contexto, long id);
	public abstract List<LojaNatureza> getPorReferenteANaturezaProduto(Context contexto, long id, int qtde);
	public abstract List<LojaNatureza> getPorReferenteANaturezaProduto(long id);
	public abstract List<LojaNatureza> getPorReferenteANaturezaProduto(long id, int qtde);
	
	
	


	public abstract void atualizaRelacionamento(LojaVirtual item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(NaturezaProduto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}