
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.OportunidadeDiaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.OportunidadeDiaDBHelper;
//import coletapreco.servico.OportunidadeDiaServico;
import br.com.digicom.log.DCLog;

public abstract class OportunidadeDiaServico {

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

	private OportunidadeDiaFiltro filtro = null;
	public OportunidadeDiaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new OportunidadeDiaFiltro();
		}
		return filtro;
	}

	public abstract OportunidadeDia getById(long id, Context contexto);
	public abstract OportunidadeDia getById(long id);
	public abstract List<OportunidadeDia> getAll(Context contexto);
	public abstract List<OportunidadeDia> getAllTela(Context contexto);
	public List<OportunidadeDia> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<OportunidadeDia> lista, Context contexto);
	public abstract void insertSincAll(List<OportunidadeDia> lista, Context contexto);
	
	// Itens Tela
	public abstract OportunidadeDia inicializaItemTela(DigicomContexto contexto);
	public abstract OportunidadeDia inicializaItemTela(OportunidadeDia itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(OportunidadeDia itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(OportunidadeDia itemTela, DigicomContexto contexto);
	
	public abstract OportunidadeDia ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(OportunidadeDia item);
	public abstract void insereParaSincronizacao(OportunidadeDia item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<OportunidadeDia> getPorReferenteAProduto(Context contexto, long id);
	public abstract List<OportunidadeDia> getPorReferenteAProduto(Context contexto, long id, int qtde);
	public abstract List<OportunidadeDia> getPorReferenteAProduto(long id);
	public abstract List<OportunidadeDia> getPorReferenteAProduto(long id, int qtde);
	
	public abstract List<OportunidadeDia> getPorPertenceANaturezaProduto(Context contexto, long id);
	public abstract List<OportunidadeDia> getPorPertenceANaturezaProduto(Context contexto, long id, int qtde);
	public abstract List<OportunidadeDia> getPorPertenceANaturezaProduto(long id);
	public abstract List<OportunidadeDia> getPorPertenceANaturezaProduto(long id, int qtde);
	
	
	
	public abstract List<OportunidadeDia> ListaPorNatureza(DigicomContexto contexto );




}