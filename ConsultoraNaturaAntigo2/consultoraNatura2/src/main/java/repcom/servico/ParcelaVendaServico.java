
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.ParcelaVendaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.ParcelaVendaDBHelper;
//import repcom.servico.ParcelaVendaServico;
import br.com.digicom.log.DCLog;

public abstract class ParcelaVendaServico {

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

	private ParcelaVendaFiltro filtro = null;
	public ParcelaVendaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ParcelaVendaFiltro();
		}
		return filtro;
	}

	public abstract ParcelaVenda getById(long id, Context contexto);
	public abstract ParcelaVenda getById(long id);
	public abstract List<ParcelaVenda> getAll(Context contexto);
	public abstract List<ParcelaVenda> getAllTela(Context contexto);
	public List<ParcelaVenda> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ParcelaVenda> lista, Context contexto);
	public abstract void insertSincAll(List<ParcelaVenda> lista, Context contexto);
	
	// Itens Tela
	public abstract ParcelaVenda inicializaItemTela(DigicomContexto contexto);
	public abstract ParcelaVenda inicializaItemTela(ParcelaVenda itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ParcelaVenda itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ParcelaVenda itemTela, DigicomContexto contexto);
	
	public abstract ParcelaVenda ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ParcelaVenda item);
	public abstract void insereParaSincronizacao(ParcelaVenda item);
	public abstract void criaTabelaSincronizacao();
	
	
	




}