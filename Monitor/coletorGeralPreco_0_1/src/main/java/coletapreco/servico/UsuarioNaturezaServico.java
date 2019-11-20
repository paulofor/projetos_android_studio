
package  coletapreco.servico;

import java.util.List;
import coletapreco.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.servico.filtro.UsuarioNaturezaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import coletapreco.dao.base.UsuarioNaturezaDBHelper;
//import coletapreco.servico.UsuarioNaturezaServico;
import br.com.digicom.log.DCLog;

public abstract class UsuarioNaturezaServico {

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

	private UsuarioNaturezaFiltro filtro = null;
	public UsuarioNaturezaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new UsuarioNaturezaFiltro();
		}
		return filtro;
	}

	public abstract UsuarioNatureza getById(long id, Context contexto);
	public abstract List<UsuarioNatureza> getAll(Context contexto);
	public abstract List<UsuarioNatureza> getAllTela(Context contexto);
	public List<UsuarioNatureza> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<UsuarioNatureza> lista, Context contexto);
	public abstract void insertSincAll(List<UsuarioNatureza> lista, Context contexto);
	
	// Itens Tela
	public abstract UsuarioNatureza inicializaItemTela(DigicomContexto contexto);
	public abstract UsuarioNatureza inicializaItemTela(UsuarioNatureza itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(UsuarioNatureza itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(UsuarioNatureza itemTela, DigicomContexto contexto);
	
	public abstract UsuarioNatureza ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(UsuarioNatureza item);
	public abstract void insereParaSincronizacao(UsuarioNatureza item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<UsuarioNatureza> getPorPesquisadoPorUsuario(Context contexto, long id);
	
	public abstract List<UsuarioNatureza> getPorPesquisaNaturezaProduto(Context contexto, long id);
	
	
	


	public abstract void atualizaRelacionamento(Usuario item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(NaturezaProduto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}