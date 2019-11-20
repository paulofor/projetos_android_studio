
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
import repcom.dao.PedidoFornecedorDBHelper;
import repcom.servico.PedidoFornecedorServico;

public abstract class PedidoFornecedorServicoSqliteBase extends  PedidoFornecedorServico 
	implements ServicoLocal<PedidoFornecedor>, WifiServicoI{


	@Override
	public List<PedidoFornecedor> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(PedidoFornecedor item) {
		PedidoFornecedorDBHelper dao = getDao();
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
	
	


	private PedidoFornecedorDBHelper dao = null; 
	protected PedidoFornecedorDBHelper getDao() {
		if (dao==null) {
			dao = new PedidoFornecedorDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<PedidoFornecedor> listaSincronizadaAlteracao(Context contexto) {
		List<PedidoFornecedor> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<PedidoFornecedor> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<PedidoFornecedor> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<PedidoFornecedor> getAll(Context contexto) {
		List<PedidoFornecedor> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<PedidoFornecedor> getAllTela(Context contexto) {
		List<PedidoFornecedor> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(PedidoFornecedor item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(PedidoFornecedor item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<PedidoFornecedor> lista, Context contexto) {
		PedidoFornecedorDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<PedidoFornecedor> lista, Context contexto) {
		PedidoFornecedorDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		PedidoFornecedorDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		PedidoFornecedorDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		PedidoFornecedorDBHelper dao = getDao();
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
	private PedidoFornecedor ultimoInicializado = null;
	public final PedidoFornecedor inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final PedidoFornecedor inicializaItemTela(PedidoFornecedor itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(PedidoFornecedor itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(PedidoFornecedor itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final PedidoFornecedor getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final PedidoFornecedor getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected PedidoFornecedor inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em PedidoFornecedorServicoSqliteImpl ");
	}
	protected PedidoFornecedor inicializaItemTelaImpl(PedidoFornecedor itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em PedidoFornecedorServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(PedidoFornecedor itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em PedidoFornecedorServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(PedidoFornecedor itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em PedidoFornecedorServicoSqliteImpl ");
	}
	
	public PedidoFornecedor ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		PedidoFornecedorDBHelper dao = getDao();
		List<PedidoFornecedor> listaItem = lista;
		for (PedidoFornecedor item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public PedidoFornecedor atribuiUsuario(PedidoFornecedor item) {
		
		return item;
	}
}