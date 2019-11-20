
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.MesAnoFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.MesAnoDBHelper;
//import repcom.servico.MesAnoServico;
import br.com.digicom.log.DCLog;

public abstract class MesAnoServico {

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

	private MesAnoFiltro filtro = null;
	public MesAnoFiltro getFiltro() {
		if (filtro==null) {
			filtro = new MesAnoFiltro();
		}
		return filtro;
	}

	public abstract MesAno getById(long id, Context contexto);
	public abstract MesAno getById(long id);
	public abstract List<MesAno> getAll(Context contexto);
	public abstract List<MesAno> getAllTela(Context contexto);
	public List<MesAno> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<MesAno> lista, Context contexto);
	public abstract void insertSincAll(List<MesAno> lista, Context contexto);
	
	// Itens Tela
	public abstract MesAno inicializaItemTela(DigicomContexto contexto);
	public abstract MesAno inicializaItemTela(MesAno itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(MesAno itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(MesAno itemTela, DigicomContexto contexto);
	
	public abstract MesAno ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(MesAno item);
	public abstract void insereParaSincronizacao(MesAno item);
	public abstract void criaTabelaSincronizacao();
	
	
	
	public abstract MesAno ObtemMesCorrente(DigicomContexto contexto );




}