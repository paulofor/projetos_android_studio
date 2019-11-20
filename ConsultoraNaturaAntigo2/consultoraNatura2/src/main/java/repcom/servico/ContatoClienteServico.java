
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.ContatoClienteFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.ContatoClienteDBHelper;
//import repcom.servico.ContatoClienteServico;
import br.com.digicom.log.DCLog;

public abstract class ContatoClienteServico {

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

	private ContatoClienteFiltro filtro = null;
	public ContatoClienteFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ContatoClienteFiltro();
		}
		return filtro;
	}

	public abstract ContatoCliente getById(long id, Context contexto);
	public abstract ContatoCliente getById(long id);
	public abstract List<ContatoCliente> getAll(Context contexto);
	public abstract List<ContatoCliente> getAllTela(Context contexto);
	public List<ContatoCliente> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ContatoCliente> lista, Context contexto);
	public abstract void insertSincAll(List<ContatoCliente> lista, Context contexto);
	
	// Itens Tela
	public abstract ContatoCliente inicializaItemTela(DigicomContexto contexto);
	public abstract ContatoCliente inicializaItemTela(ContatoCliente itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ContatoCliente itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ContatoCliente itemTela, DigicomContexto contexto);
	
	public abstract ContatoCliente ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ContatoCliente item);
	public abstract void insereParaSincronizacao(ContatoCliente item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<ContatoCliente> getPorReferenteACliente(Context contexto, long id);
	public abstract List<ContatoCliente> getPorReferenteACliente(Context contexto, long id, int qtde);
	public abstract List<ContatoCliente> getPorReferenteACliente(long id);
	public abstract List<ContatoCliente> getPorReferenteACliente(long id, int qtde);
	
	
	




}