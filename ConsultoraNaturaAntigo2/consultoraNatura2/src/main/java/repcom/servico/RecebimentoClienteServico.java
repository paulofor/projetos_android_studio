
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.RecebimentoClienteFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.RecebimentoClienteDBHelper;
//import repcom.servico.RecebimentoClienteServico;
import br.com.digicom.log.DCLog;

public abstract class RecebimentoClienteServico {

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

	private RecebimentoClienteFiltro filtro = null;
	public RecebimentoClienteFiltro getFiltro() {
		if (filtro==null) {
			filtro = new RecebimentoClienteFiltro();
		}
		return filtro;
	}

	public abstract RecebimentoCliente getById(long id, Context contexto);
	public abstract List<RecebimentoCliente> getAll(Context contexto);
	public abstract List<RecebimentoCliente> getAllTela(Context contexto);
	public List<RecebimentoCliente> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<RecebimentoCliente> lista, Context contexto);
	public abstract void insertSincAll(List<RecebimentoCliente> lista, Context contexto);
	
	// Itens Tela
	public abstract RecebimentoCliente inicializaItemTela(DigicomContexto contexto);
	public abstract void finalizaItemTela(RecebimentoCliente itemTela, DigicomContexto contexto);
	public abstract void processaItemTela(RecebimentoCliente itemTela, DigicomContexto contexto);
	
	public abstract RecebimentoCliente ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(RecebimentoCliente item);
	public abstract void insereParaSincronizacao(RecebimentoCliente item);
	public abstract void criaTabelaSincronizacao();
	
	
	


}