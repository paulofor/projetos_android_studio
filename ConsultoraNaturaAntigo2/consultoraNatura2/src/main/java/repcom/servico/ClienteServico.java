
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.ClienteFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.ClienteDBHelper;
//import repcom.servico.ClienteServico;
import br.com.digicom.log.DCLog;

public abstract class ClienteServico {

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

	private ClienteFiltro filtro = null;
	public ClienteFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ClienteFiltro();
		}
		return filtro;
	}

	public abstract Cliente getById(long id, Context contexto);
	public abstract Cliente getById(long id);
	public abstract List<Cliente> getAll(Context contexto);
	public abstract List<Cliente> getAllTela(Context contexto);
	public List<Cliente> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<Cliente> lista, Context contexto);
	public abstract void insertSincAll(List<Cliente> lista, Context contexto);
	
	// Itens Tela
	public abstract Cliente inicializaItemTela(DigicomContexto contexto);
	public abstract Cliente inicializaItemTela(Cliente itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(Cliente itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(Cliente itemTela, DigicomContexto contexto);
	
	public abstract Cliente ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(Cliente item);
	public abstract void insereParaSincronizacao(Cliente item);
	public abstract void criaTabelaSincronizacao();
	
	
	
	public abstract List<Cliente> ObtemListaAgendaTel(DigicomContexto contexto );
	public abstract List<Cliente> SincronizaAgendaTel(DigicomContexto contexto );
	public abstract Cliente ObtemPorIdAgendaTel(DigicomContexto contexto );
	public abstract Cliente PreencheDerivadaAgendaTel(DigicomContexto contexto );
	public abstract Cliente DesativaPorId(DigicomContexto contexto );
	public abstract List<Cliente> ListaAtivos(DigicomContexto contexto );
	public abstract List<Cliente> PesquisaTrechoNome(DigicomContexto contexto );




}