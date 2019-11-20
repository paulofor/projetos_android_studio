
package  repcom.servico;

import java.util.List;
import repcom.modelo.*;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import repcom.servico.filtro.ClienteInteresseCategoriaFiltro;
import br.com.digicom.video.DigicomVideoView;
//import repcom.dao.base.ClienteInteresseCategoriaDBHelper;
//import repcom.servico.ClienteInteresseCategoriaServico;
import br.com.digicom.log.DCLog;

public abstract class ClienteInteresseCategoriaServico {

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

	private ClienteInteresseCategoriaFiltro filtro = null;
	public ClienteInteresseCategoriaFiltro getFiltro() {
		if (filtro==null) {
			filtro = new ClienteInteresseCategoriaFiltro();
		}
		return filtro;
	}

	public abstract ClienteInteresseCategoria getById(long id, Context contexto);
	public abstract ClienteInteresseCategoria getById(long id);
	public abstract List<ClienteInteresseCategoria> getAll(Context contexto);
	public abstract List<ClienteInteresseCategoria> getAllTela(Context contexto);
	public List<ClienteInteresseCategoria> listaSincronizada(Context contexto) {
		return null;
	}
	
	public abstract void insertAll(List<ClienteInteresseCategoria> lista, Context contexto);
	public abstract void insertSincAll(List<ClienteInteresseCategoria> lista, Context contexto);
	
	// Itens Tela
	public abstract ClienteInteresseCategoria inicializaItemTela(DigicomContexto contexto);
	public abstract ClienteInteresseCategoria inicializaItemTela(ClienteInteresseCategoria itemTela, DigicomContexto contexto);
	public abstract void finalizaItemTela(ClienteInteresseCategoria itemTela, boolean insercao, DigicomContexto contexto);
	public abstract void processaItemTela(ClienteInteresseCategoria itemTela, DigicomContexto contexto);
	
	public abstract ClienteInteresseCategoria ultimoInicializado();
	public abstract void dropCreate(Context contexto);
	public abstract void alteraParaSincronizacao(ClienteInteresseCategoria item);
	public abstract void insereParaSincronizacao(ClienteInteresseCategoria item);
	public abstract void criaTabelaSincronizacao();
	
	public abstract List<ClienteInteresseCategoria> getPorAssociadaCliente(Context contexto, long id);
	public abstract List<ClienteInteresseCategoria> getPorAssociadaCliente(Context contexto, long id, int qtde);
	public abstract List<ClienteInteresseCategoria> getPorAssociadaCliente(long id);
	public abstract List<ClienteInteresseCategoria> getPorAssociadaCliente(long id, int qtde);
	
	public abstract List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(Context contexto, long id);
	public abstract List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(Context contexto, long id, int qtde);
	public abstract List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(long id);
	public abstract List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(long id, int qtde);
	
	
	


	public abstract void atualizaRelacionamento(Cliente item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract void atualizaRelacionamento(CategoriaProduto item, List<DCIObjetoDominio> listaEscolhidos);
	public abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	


}