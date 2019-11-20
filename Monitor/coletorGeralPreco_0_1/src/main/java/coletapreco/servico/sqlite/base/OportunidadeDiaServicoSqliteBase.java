
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
import coletapreco.dao.OportunidadeDiaDBHelper;
import coletapreco.servico.OportunidadeDiaServico;

public abstract class OportunidadeDiaServicoSqliteBase extends  OportunidadeDiaServico 
	implements ServicoLocal<OportunidadeDia>, WifiServicoI{


	@Override
	public List<OportunidadeDia> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(OportunidadeDia item) {
		OportunidadeDiaDBHelper dao = getDao();
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
	
	


	private OportunidadeDiaDBHelper dao = null; 
	protected OportunidadeDiaDBHelper getDao() {
		if (dao==null) {
			dao = new OportunidadeDiaDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<OportunidadeDia> listaSincronizadaAlteracao(Context contexto) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<OportunidadeDia> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	//public List<OportunidadeDia> ListaPorNatureza(Context contexto){}
	
	




	public List<OportunidadeDia> getAll(Context contexto) {
		List<OportunidadeDia> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<OportunidadeDia> getAllTela(Context contexto) {
		List<OportunidadeDia> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(OportunidadeDia item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(OportunidadeDia item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<OportunidadeDia> lista, Context contexto) {
		OportunidadeDiaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<OportunidadeDia> lista, Context contexto) {
		OportunidadeDiaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		OportunidadeDiaDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		OportunidadeDiaDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		OportunidadeDiaDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<OportunidadeDia> getPorReferenteAProduto(Context contexto, long id) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<OportunidadeDia> getPorReferenteAProduto(Context contexto, long id, int qtde) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<OportunidadeDia> getPorReferenteAProduto(long id) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<OportunidadeDia> getPorReferenteAProduto(long id, int qtde) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(Context contexto, long id) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getPorPertenceANaturezaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(Context contexto, long id, int qtde) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getPorPertenceANaturezaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(long id) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getPorPertenceANaturezaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(long id, int qtde) {
		List<OportunidadeDia> saida = null; 
		try {
			saida = getDao().getPorPertenceANaturezaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertenceANaturezaProduto : " +  saida.size() + " itens" );
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
	private OportunidadeDia ultimoInicializado = null;
	public final OportunidadeDia inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final OportunidadeDia inicializaItemTela(OportunidadeDia itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(OportunidadeDia itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(OportunidadeDia itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final OportunidadeDia getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final OportunidadeDia getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected OportunidadeDia inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em OportunidadeDiaServicoSqliteImpl ");
	}
	protected OportunidadeDia inicializaItemTelaImpl(OportunidadeDia itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em OportunidadeDiaServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(OportunidadeDia itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em OportunidadeDiaServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(OportunidadeDia itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em OportunidadeDiaServicoSqliteImpl ");
	}
	
	public OportunidadeDia ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final List<OportunidadeDia> ListaPorNatureza(DigicomContexto contexto ) {
		List<OportunidadeDia> saida = ListaPorNaturezaImpl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ListaPorNatureza : " +  saida.size() + " itens" );
		return saida;
	}
	protected abstract List<OportunidadeDia> ListaPorNaturezaImpl(DigicomContexto contexto );
	@Override
	public void limpaSinc(List lista) {
		OportunidadeDiaDBHelper dao = getDao();
		List<OportunidadeDia> listaItem = lista;
		for (OportunidadeDia item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public OportunidadeDia atribuiUsuario(OportunidadeDia item) {
		
		return item;
	}
}