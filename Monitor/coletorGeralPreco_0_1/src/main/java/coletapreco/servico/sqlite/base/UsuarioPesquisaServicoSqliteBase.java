
package coletapreco.servico.sqlite.base;


import java.util.List;
import java.util.ArrayList;

import br.com.digicom.servico.WifiServicoI;
import br.com.digicom.network.WifiListenerReceiver;
import br.com.digicom.servico.ServicoLocal;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.multimidia.DigicomAudioPlayer;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.dao.DaoException;
import br.com.digicom.dao.DaoSincronismo;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.DCIObjetoDominio;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.modelo.*;
import coletapreco.servico.*;
import android.content.Context;
import android.util.Log;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import coletapreco.dao.UsuarioPesquisaDBHelper;
import coletapreco.servico.UsuarioPesquisaServico;

public abstract class UsuarioPesquisaServicoSqliteBase extends  UsuarioPesquisaServico 
	implements ServicoLocal<UsuarioPesquisa>, WifiServicoI{


	@Override
	public List<UsuarioPesquisa> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(UsuarioPesquisa item) {
		UsuarioPesquisaDBHelper dao = getDao();
		dao.insert(item);
	}
	
	
	public DaoSincronismo getDaoSincronismo() {
		return (DaoSincronismo) getDao();
	}
	
	
	// Multimidia
	// Multimidia
	DigicomAudioPlayer player = null;
	protected void reproduzAudioRaw(DigicomContexto ctx, AudioRecurso recurso) {
		if (player==null)
			player = new DigicomAudioPlayer();
		player.reproduzAudioRaw(ctx, recurso);
	}
	
	// Rede
	// Registra Wifi
	protected void registraWifi(Context ctx) {
		WifiListenerReceiver wifi = new WifiListenerReceiver();
		wifi.setServico(this);
		wifi.registraReceiver(ctx);
	}
	
	


	private UsuarioPesquisaDBHelper dao = null; 
	protected UsuarioPesquisaDBHelper getDao() {
		if (dao==null) {
			dao = new UsuarioPesquisaDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<UsuarioPesquisa> listaSincronizadaAlteracao(Context contexto) {
		List<UsuarioPesquisa> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<UsuarioPesquisa> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<UsuarioPesquisa> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<UsuarioPesquisa> getAll(Context contexto) {
		List<UsuarioPesquisa> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<UsuarioPesquisa> getAllTela(Context contexto) {
		List<UsuarioPesquisa> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(UsuarioPesquisa item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(UsuarioPesquisa item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<UsuarioPesquisa> lista, Context contexto) {
		UsuarioPesquisaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<UsuarioPesquisa> lista, Context contexto) {
		UsuarioPesquisaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		UsuarioPesquisaDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		UsuarioPesquisaDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		UsuarioPesquisaDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(Context contexto, long id) {
		List<UsuarioPesquisa> saida = null; 
		try {
			saida = getDao().getPorPesquisaNaturezaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPesquisaNaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(Context contexto, long id, int qtde) {
		List<UsuarioPesquisa> saida = null; 
		try {
			saida = getDao().getPorPesquisaNaturezaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPesquisaNaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(long id) {
		List<UsuarioPesquisa> saida = null; 
		try {
			saida = getDao().getPorPesquisaNaturezaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPesquisaNaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<UsuarioPesquisa> getPorPesquisaNaturezaProduto(long id, int qtde) {
		List<UsuarioPesquisa> saida = null; 
		try {
			saida = getDao().getPorPesquisaNaturezaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPesquisaNaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	

	
	// Servicos Wifi
	@Override
	public void entrouWifi() {
	}

	@Override
	public void saiuWifi() {
	}	
	
	// Itens Tela itemTela
	private UsuarioPesquisa ultimoInicializado = null;
	public final UsuarioPesquisa inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final UsuarioPesquisa inicializaItemTela(UsuarioPesquisa itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(UsuarioPesquisa itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(UsuarioPesquisa itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final UsuarioPesquisa getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final UsuarioPesquisa getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected UsuarioPesquisa inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em UsuarioPesquisaServicoSqliteImpl ");
	}
	protected UsuarioPesquisa inicializaItemTelaImpl(UsuarioPesquisa itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em UsuarioPesquisaServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(UsuarioPesquisa itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em UsuarioPesquisaServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(UsuarioPesquisa itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em UsuarioPesquisaServicoSqliteImpl ");
	}
	
	public UsuarioPesquisa ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		UsuarioPesquisaDBHelper dao = getDao();
		List<UsuarioPesquisa> listaItem = lista;
		for (UsuarioPesquisa item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public UsuarioPesquisa atribuiUsuario(UsuarioPesquisa item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}