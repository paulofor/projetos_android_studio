
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
import repcom.dao.EstoqueDBHelper;
import repcom.servico.EstoqueServico;

public abstract class EstoqueServicoSqliteBase extends  EstoqueServico 
	implements ServicoLocal<Estoque>, WifiServicoI{


	@Override
	public List<Estoque> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(Estoque item) {
		EstoqueDBHelper dao = getDao();
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
	
	


	private EstoqueDBHelper dao = null; 
	protected EstoqueDBHelper getDao() {
		if (dao==null) {
			dao = new EstoqueDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<Estoque> listaSincronizadaAlteracao(Context contexto) {
		List<Estoque> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<Estoque> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<Estoque> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<Estoque> getAll(Context contexto) {
		List<Estoque> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<Estoque> getAllTela(Context contexto) {
		List<Estoque> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(Estoque item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(Estoque item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<Estoque> lista, Context contexto) {
		EstoqueDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<Estoque> lista, Context contexto) {
		EstoqueDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		EstoqueDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		EstoqueDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		EstoqueDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	

	
	// Servicos Wifi
	@Override
	public void entrouWifi() {
	}

	@Override
	public void saiuWifi() {
	}	
	
	// Itens Tela itemTela
	private Estoque ultimoInicializado = null;
	public final Estoque inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final Estoque inicializaItemTela(Estoque itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(Estoque itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(Estoque itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final Estoque getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final Estoque getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected Estoque inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em EstoqueServicoSqliteImpl ");
	}
	protected Estoque inicializaItemTelaImpl(Estoque itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em EstoqueServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(Estoque itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em EstoqueServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(Estoque itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em EstoqueServicoSqliteImpl ");
	}
	
	public Estoque ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		EstoqueDBHelper dao = getDao();
		List<Estoque> listaItem = lista;
		for (Estoque item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public Estoque atribuiUsuario(Estoque item) {
		
		return item;
	}
}