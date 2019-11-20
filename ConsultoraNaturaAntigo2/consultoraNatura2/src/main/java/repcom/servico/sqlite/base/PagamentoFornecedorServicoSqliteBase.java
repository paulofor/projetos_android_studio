
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
import repcom.dao.PagamentoFornecedorDBHelper;
import repcom.servico.PagamentoFornecedorServico;

public abstract class PagamentoFornecedorServicoSqliteBase extends  PagamentoFornecedorServico 
	implements ServicoLocal<PagamentoFornecedor>, WifiServicoI{


	@Override
	public List<PagamentoFornecedor> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(PagamentoFornecedor item) {
		PagamentoFornecedorDBHelper dao = getDao();
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
	
	


	private PagamentoFornecedorDBHelper dao = null; 
	protected PagamentoFornecedorDBHelper getDao() {
		if (dao==null) {
			dao = new PagamentoFornecedorDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<PagamentoFornecedor> listaSincronizadaAlteracao(Context contexto) {
		List<PagamentoFornecedor> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<PagamentoFornecedor> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<PagamentoFornecedor> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<PagamentoFornecedor> getAll(Context contexto) {
		List<PagamentoFornecedor> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<PagamentoFornecedor> getAllTela(Context contexto) {
		List<PagamentoFornecedor> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(PagamentoFornecedor item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(PagamentoFornecedor item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<PagamentoFornecedor> lista, Context contexto) {
		PagamentoFornecedorDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<PagamentoFornecedor> lista, Context contexto) {
		PagamentoFornecedorDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		PagamentoFornecedorDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		PagamentoFornecedorDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		PagamentoFornecedorDBHelper dao = getDao();
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
	private PagamentoFornecedor ultimoInicializado = null;
	public final PagamentoFornecedor inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final PagamentoFornecedor inicializaItemTela(PagamentoFornecedor itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(PagamentoFornecedor itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(PagamentoFornecedor itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final PagamentoFornecedor getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final PagamentoFornecedor getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected PagamentoFornecedor inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em PagamentoFornecedorServicoSqliteImpl ");
	}
	protected PagamentoFornecedor inicializaItemTelaImpl(PagamentoFornecedor itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em PagamentoFornecedorServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(PagamentoFornecedor itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em PagamentoFornecedorServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(PagamentoFornecedor itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em PagamentoFornecedorServicoSqliteImpl ");
	}
	
	public PagamentoFornecedor ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		PagamentoFornecedorDBHelper dao = getDao();
		List<PagamentoFornecedor> listaItem = lista;
		for (PagamentoFornecedor item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public PagamentoFornecedor atribuiUsuario(PagamentoFornecedor item) {
		
		return item;
	}
}