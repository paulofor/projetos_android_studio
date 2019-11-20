
package repcom.servico.sqlite.base;


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
import repcom.modelo.vo.FabricaVo;
import repcom.modelo.*;
import repcom.servico.*;
import android.content.Context;
import android.util.Log;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import repcom.dao.CategoriaProdutoDBHelper;
import repcom.servico.CategoriaProdutoServico;

public abstract class CategoriaProdutoServicoSqliteBase extends  CategoriaProdutoServico 
	implements ServicoLocal<CategoriaProduto>, WifiServicoI{


	@Override
	public List<CategoriaProduto> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(CategoriaProduto item) {
		CategoriaProdutoDBHelper dao = getDao();
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
	
	


	private CategoriaProdutoDBHelper dao = null; 
	protected CategoriaProdutoDBHelper getDao() {
		if (dao==null) {
			dao = new CategoriaProdutoDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<CategoriaProduto> listaSincronizadaAlteracao(Context contexto) {
		List<CategoriaProduto> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaProduto> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<CategoriaProduto> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	//public List<CategoriaProduto> ListaNivel0(Context contexto){}
	
	




	public List<CategoriaProduto> getAll(Context contexto) {
		List<CategoriaProduto> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<CategoriaProduto> getAllTela(Context contexto) {
		List<CategoriaProduto> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(CategoriaProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(CategoriaProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<CategoriaProduto> lista, Context contexto) {
		CategoriaProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<CategoriaProduto> lista, Context contexto) {
		CategoriaProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		CategoriaProdutoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		CategoriaProdutoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		CategoriaProdutoDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<CategoriaProduto> getPorPaiCategoriaProduto(Context contexto, long id) {
		List<CategoriaProduto> saida = null; 
		try {
			saida = getDao().getPorPaiCategoriaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPaiCategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaProduto> getPorPaiCategoriaProduto(Context contexto, long id, int qtde) {
		List<CategoriaProduto> saida = null; 
		try {
			saida = getDao().getPorPaiCategoriaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPaiCategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaProduto> getPorPaiCategoriaProduto(long id) {
		List<CategoriaProduto> saida = null; 
		try {
			saida = getDao().getPorPaiCategoriaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPaiCategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaProduto> getPorPaiCategoriaProduto(long id, int qtde) {
		List<CategoriaProduto> saida = null; 
		try {
			saida = getDao().getPorPaiCategoriaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPaiCategoriaProduto : " +  saida.size() + " itens" );
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
	private CategoriaProduto ultimoInicializado = null;
	public final CategoriaProduto inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final CategoriaProduto inicializaItemTela(CategoriaProduto itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(CategoriaProduto itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(CategoriaProduto itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final CategoriaProduto getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final CategoriaProduto getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected CategoriaProduto inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CategoriaProdutoServicoSqliteImpl ");
	}
	protected CategoriaProduto inicializaItemTelaImpl(CategoriaProduto itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CategoriaProdutoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(CategoriaProduto itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em CategoriaProdutoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(CategoriaProduto itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em CategoriaProdutoServicoSqliteImpl ");
	}
	
	public CategoriaProduto ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final List<CategoriaProduto> ListaNivel0(DigicomContexto contexto ) {
		List<CategoriaProduto> saida = ListaNivel0Impl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ListaNivel0 : " +  saida.size() + " itens" );
		return saida;
	}
	protected abstract List<CategoriaProduto> ListaNivel0Impl(DigicomContexto contexto );
	@Override
	public void limpaSinc(List lista) {
		CategoriaProdutoDBHelper dao = getDao();
		List<CategoriaProduto> listaItem = lista;
		for (CategoriaProduto item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public CategoriaProduto atribuiUsuario(CategoriaProduto item) {
		
		return item;
	}
}