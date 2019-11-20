
package treinoacademia.servico.sqlite.base;


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
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.modelo.*;
import treinoacademia.servico.*;
import treinoacademia.app.TrataErro;
import android.content.Context;
import android.util.Log;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import treinoacademia.dao.CargaPlanejadaDBHelper;
import treinoacademia.servico.CargaPlanejadaServico;

public abstract class CargaPlanejadaServicoSqliteBase extends  CargaPlanejadaServico 
	implements ServicoLocal<CargaPlanejada>, WifiServicoI{


	@Override
	public List<CargaPlanejada> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(CargaPlanejada item) {
		CargaPlanejadaDBHelper dao = getDao();
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
	
	


	private CargaPlanejadaDBHelper dao = null; 
	protected CargaPlanejadaDBHelper getDao() {
		if (dao==null) {
			dao = new CargaPlanejadaDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<CargaPlanejada> listaSincronizadaAlteracao(Context contexto) {
		List<CargaPlanejada> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	@Override
	public List<CargaPlanejada> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<CargaPlanejada> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	
	//public List<CargaPlanejada> ListaCargaPorExercicio(Context contexto){}
	//public List<CargaPlanejada> ListaCargaAtivaPorExercicio(Context contexto){}
	
	




	public List<CargaPlanejada> getAll(Context contexto) {
		List<CargaPlanejada> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<CargaPlanejada> getAllTela(Context contexto) {
		List<CargaPlanejada> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(CargaPlanejada item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void excluiParaSincronizacao(CargaPlanejada item) {
		getDao().deleteSinc(item);
	}
	public void insereParaSincronizacao(CargaPlanejada item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
		item.setSomenteMemoria(false);
	}
	public void insertAll(List<CargaPlanejada> lista, Context contexto) {
		CargaPlanejadaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<CargaPlanejada> lista, Context contexto) {
		CargaPlanejadaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		CargaPlanejadaDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		CargaPlanejadaDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		CargaPlanejadaDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<CargaPlanejada> getPorReferenteAItemSerie(Context contexto, long id) {
		List<CargaPlanejada> saida = null; 
		try {
			saida = getDao().getPorReferenteAItemSerie(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAItemSerie : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<CargaPlanejada> getPorReferenteAItemSerie(Context contexto, long id, int qtde) {
		List<CargaPlanejada> saida = null; 
		try {
			saida = getDao().getPorReferenteAItemSerie(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAItemSerie : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	@Override
	public List<CargaPlanejada> getPorReferenteAItemSerie(long id) {
		List<CargaPlanejada> saida = null; 
		try {
			saida = getDao().getPorReferenteAItemSerie(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAItemSerie : " +  saida.size() + " itens (id_carga_planejada=" + id + ")" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<CargaPlanejada> getPorReferenteAItemSerie(long id, int qtde) {
		List<CargaPlanejada> saida = null; 
		try {
			saida = getDao().getPorReferenteAItemSerie(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAItemSerie : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
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
	private CargaPlanejada ultimoInicializado = null;
	public final CargaPlanejada inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final CargaPlanejada inicializaItemTela(CargaPlanejada itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(CargaPlanejada itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(CargaPlanejada itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final CargaPlanejada getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final CargaPlanejada getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected CargaPlanejada inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CargaPlanejadaServicoSqliteImpl ");
	}
	protected CargaPlanejada inicializaItemTelaImpl(CargaPlanejada itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CargaPlanejadaServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(CargaPlanejada itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em CargaPlanejadaServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(CargaPlanejada itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em CargaPlanejadaServicoSqliteImpl ");
	}
	
	public CargaPlanejada ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final List<CargaPlanejada> ListaCargaPorExercicio(DigicomContexto contexto ) {
		List<CargaPlanejada> saida = null;
		try {
			saida =  ListaCargaPorExercicioImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ListaCargaPorExercicio : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<CargaPlanejada> ListaCargaPorExercicioImpl(DigicomContexto contexto ) throws DaoException;
	public final List<CargaPlanejada> ListaCargaAtivaPorExercicio(DigicomContexto contexto ) {
		List<CargaPlanejada> saida = null;
		try {
			saida =  ListaCargaAtivaPorExercicioImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ListaCargaAtivaPorExercicio : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<CargaPlanejada> ListaCargaAtivaPorExercicioImpl(DigicomContexto contexto ) throws DaoException;
	@Override
	public void limpaSinc(List lista) {
		CargaPlanejadaDBHelper dao = getDao();
		List<CargaPlanejada> listaItem = lista;
		for (CargaPlanejada item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public CargaPlanejada atribuiUsuario(CargaPlanejada item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}