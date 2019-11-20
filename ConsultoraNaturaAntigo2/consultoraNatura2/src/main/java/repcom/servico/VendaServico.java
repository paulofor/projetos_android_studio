
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.VendaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.VendaDBHelper;
//import repcom.servico.VendaServico;
import br.com.digicom.log.DCLog;

public abstract class VendaServico {

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

	private VendaFiltro filtro = null;
	public VendaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new VendaFiltro();
		}
		return filtro;
	}

	public abstract Venda getById(long id, Context contexto);
	public abstract Venda getById(long id);
	public abstract List<Venda> getAll(Context contexto);
	public abstract List<Venda> getAllTela(Context contexto);
	public List<Venda> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<Venda> lista, Context contexto);
	public abstract void insertSincAll(List<Venda> lista, Context contexto);
	
	// Itens Tela
	public abstract Venda inicializaItemTela(DigicomContexto contexto);
	public abstract Venda inicializaItemTela(Venda itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(Venda itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(Venda itemTela, DigicomContexto contexto);
	
	public abstract Venda ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(Venda item);
	public abstract void insereParaSincronizacao(Venda item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<Venda> getPorFeitaParaCliente(Context contexto, long id);
	public abstract List<Venda> getPorFeitaParaCliente(Context contexto, long id, int qtde);
	public abstract List<Venda> getPorFeitaParaCliente(long id);
	public abstract List<Venda> getPorFeitaParaCliente(long id, int qtde);
	
	
	




}