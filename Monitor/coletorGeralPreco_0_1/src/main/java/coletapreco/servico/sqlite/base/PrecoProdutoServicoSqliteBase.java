
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
import coletapreco.dao.PrecoProdutoDBHelper;
import coletapreco.servico.PrecoProdutoServico;

public abstract class PrecoProdutoServicoSqliteBase extends  PrecoProdutoServico 
	implements ServicoLocal<PrecoProduto>, WifiServicoI{


	@Override
	public List<PrecoProduto> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(PrecoProduto item) {
		PrecoProdutoDBHelper dao = getDao();
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
	
	


	private PrecoProdutoDBHelper dao = null; 
	protected PrecoProdutoDBHelper getDao() {
		if (dao==null) {
			dao = new PrecoProdutoDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<PrecoProduto> listaSincronizadaAlteracao(Context contexto) {
		List<PrecoProduto> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<PrecoProduto> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<PrecoProduto> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<PrecoProduto> getAll(Context contexto) {
		List<PrecoProduto> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<PrecoProduto> getAllTela(Context contexto) {
		List<PrecoProduto> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(PrecoProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(PrecoProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<PrecoProduto> lista, Context contexto) {
		PrecoProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<PrecoProduto> lista, Context contexto) {
		PrecoProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		PrecoProdutoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		PrecoProdutoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		PrecoProdutoDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<PrecoProduto> getPorPertenceAProduto(Context contexto, long id) {
		List<PrecoProduto> saida = null; 
		try {
			saida = getDao().getPorPertenceAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<PrecoProduto> getPorPertenceAProduto(Context contexto, long id, int qtde) {
		List<PrecoProduto> saida = null; 
		try {
			saida = getDao().getPorPertenceAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<PrecoProduto> getPorPertenceAProduto(long id) {
		List<PrecoProduto> saida = null; 
		try {
			saida = getDao().getPorPertenceAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<PrecoProduto> getPorPertenceAProduto(long id, int qtde) {
		List<PrecoProduto> saida = null; 
		try {
			saida = getDao().getPorPertenceAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceAProduto : " +  saida.size() + " itens" );
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
	private PrecoProduto ultimoInicializado = null;
	public final PrecoProduto inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final PrecoProduto inicializaItemTela(PrecoProduto itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(PrecoProduto itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(PrecoProduto itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final PrecoProduto getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final PrecoProduto getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected PrecoProduto inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em PrecoProdutoServicoSqliteImpl ");
	}
	protected PrecoProduto inicializaItemTelaImpl(PrecoProduto itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em PrecoProdutoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(PrecoProduto itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em PrecoProdutoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(PrecoProduto itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em PrecoProdutoServicoSqliteImpl ");
	}
	
	public PrecoProduto ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		PrecoProdutoDBHelper dao = getDao();
		List<PrecoProduto> listaItem = lista;
		for (PrecoProduto item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public PrecoProduto atribuiUsuario(PrecoProduto item) {
		
		return item;
	}
}