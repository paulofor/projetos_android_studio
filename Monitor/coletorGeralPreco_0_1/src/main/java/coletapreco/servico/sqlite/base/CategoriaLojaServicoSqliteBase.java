
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
import coletapreco.dao.CategoriaLojaDBHelper;
import coletapreco.servico.CategoriaLojaServico;

public abstract class CategoriaLojaServicoSqliteBase extends  CategoriaLojaServico 
	implements ServicoLocal<CategoriaLoja>, WifiServicoI{


	@Override
	public List<CategoriaLoja> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(CategoriaLoja item) {
		CategoriaLojaDBHelper dao = getDao();
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
	
	


	private CategoriaLojaDBHelper dao = null; 
	protected CategoriaLojaDBHelper getDao() {
		if (dao==null) {
			dao = new CategoriaLojaDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<CategoriaLoja> listaSincronizadaAlteracao(Context contexto) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaLoja> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<CategoriaLoja> getAll(Context contexto) {
		List<CategoriaLoja> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<CategoriaLoja> getAllTela(Context contexto) {
		List<CategoriaLoja> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(CategoriaLoja item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(CategoriaLoja item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<CategoriaLoja> lista, Context contexto) {
		CategoriaLojaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<CategoriaLoja> lista, Context contexto) {
		CategoriaLojaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		CategoriaLojaDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		CategoriaLojaDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		CategoriaLojaDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<CategoriaLoja> getPorFilhoCategoriaLoja(Context contexto, long id) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorFilhoCategoriaLoja(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorFilhoCategoriaLoja : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLoja> getPorFilhoCategoriaLoja(Context contexto, long id, int qtde) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorFilhoCategoriaLoja(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorFilhoCategoriaLoja : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaLoja> getPorFilhoCategoriaLoja(long id) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorFilhoCategoriaLoja(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorFilhoCategoriaLoja : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLoja> getPorFilhoCategoriaLoja(long id, int qtde) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorFilhoCategoriaLoja(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorFilhoCategoriaLoja : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<CategoriaLoja> getPorReferenteANaturezaProduto(Context contexto, long id) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorReferenteANaturezaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLoja> getPorReferenteANaturezaProduto(Context contexto, long id, int qtde) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorReferenteANaturezaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaLoja> getPorReferenteANaturezaProduto(long id) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorReferenteANaturezaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLoja> getPorReferenteANaturezaProduto(long id, int qtde) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorReferenteANaturezaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<CategoriaLoja> getPorPertenceALojaVirtual(Context contexto, long id) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorPertenceALojaVirtual(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceALojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLoja> getPorPertenceALojaVirtual(Context contexto, long id, int qtde) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorPertenceALojaVirtual(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceALojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaLoja> getPorPertenceALojaVirtual(long id) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorPertenceALojaVirtual(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceALojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLoja> getPorPertenceALojaVirtual(long id, int qtde) {
		List<CategoriaLoja> saida = null; 
		try {
			saida = getDao().getPorPertenceALojaVirtual(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceALojaVirtual : " +  saida.size() + " itens" );
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
	private CategoriaLoja ultimoInicializado = null;
	public final CategoriaLoja inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final CategoriaLoja inicializaItemTela(CategoriaLoja itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(CategoriaLoja itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(CategoriaLoja itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final CategoriaLoja getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final CategoriaLoja getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected CategoriaLoja inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CategoriaLojaServicoSqliteImpl ");
	}
	protected CategoriaLoja inicializaItemTelaImpl(CategoriaLoja itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CategoriaLojaServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(CategoriaLoja itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em CategoriaLojaServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(CategoriaLoja itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em CategoriaLojaServicoSqliteImpl ");
	}
	
	public CategoriaLoja ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		CategoriaLojaDBHelper dao = getDao();
		List<CategoriaLoja> listaItem = lista;
		for (CategoriaLoja item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public CategoriaLoja atribuiUsuario(CategoriaLoja item) {
		
		return item;
	}
}