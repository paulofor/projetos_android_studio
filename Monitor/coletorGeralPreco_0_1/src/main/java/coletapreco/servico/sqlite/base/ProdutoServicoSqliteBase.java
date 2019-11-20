
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
import coletapreco.dao.ProdutoDBHelper;
import coletapreco.servico.ProdutoServico;

public abstract class ProdutoServicoSqliteBase extends  ProdutoServico 
	implements ServicoLocal<Produto>, WifiServicoI{


	@Override
	public List<Produto> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(Produto item) {
		ProdutoDBHelper dao = getDao();
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
	
	


	private ProdutoDBHelper dao = null; 
	protected ProdutoDBHelper getDao() {
		if (dao==null) {
			dao = new ProdutoDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<Produto> listaSincronizadaAlteracao(Context contexto) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<Produto> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<Produto> getAll(Context contexto) {
		List<Produto> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<Produto> getAllTela(Context contexto) {
		List<Produto> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(Produto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(Produto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<Produto> lista, Context contexto) {
		ProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<Produto> lista, Context contexto) {
		ProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ProdutoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ProdutoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ProdutoDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<Produto> getPorLidoEmLojaVirtual(Context contexto, long id) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getPorLidoEmLojaVirtual(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorLidoEmLojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<Produto> getPorLidoEmLojaVirtual(Context contexto, long id, int qtde) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getPorLidoEmLojaVirtual(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorLidoEmLojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<Produto> getPorLidoEmLojaVirtual(long id) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getPorLidoEmLojaVirtual(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorLidoEmLojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<Produto> getPorLidoEmLojaVirtual(long id, int qtde) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getPorLidoEmLojaVirtual(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorLidoEmLojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<Produto> getPorPossuiMarca(Context contexto, long id) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getPorPossuiMarca(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPossuiMarca : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<Produto> getPorPossuiMarca(Context contexto, long id, int qtde) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getPorPossuiMarca(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPossuiMarca : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<Produto> getPorPossuiMarca(long id) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getPorPossuiMarca(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPossuiMarca : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<Produto> getPorPossuiMarca(long id, int qtde) {
		List<Produto> saida = null; 
		try {
			saida = getDao().getPorPossuiMarca(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPossuiMarca : " +  saida.size() + " itens" );
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
	private Produto ultimoInicializado = null;
	public final Produto inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final Produto inicializaItemTela(Produto itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(Produto itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(Produto itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final Produto getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final Produto getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected Produto inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ProdutoServicoSqliteImpl ");
	}
	protected Produto inicializaItemTelaImpl(Produto itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ProdutoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(Produto itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ProdutoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(Produto itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ProdutoServicoSqliteImpl ");
	}
	
	public Produto ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		ProdutoDBHelper dao = getDao();
		List<Produto> listaItem = lista;
		for (Produto item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public Produto atribuiUsuario(Produto item) {
		
		return item;
	}
}