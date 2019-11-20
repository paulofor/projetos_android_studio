
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
import treinoacademia.dao.DiaTreinoDBHelper;
import treinoacademia.servico.DiaTreinoServico;

public abstract class DiaTreinoServicoSqliteBase extends  DiaTreinoServico 
	implements ServicoLocal<DiaTreino>, WifiServicoI{


	@Override
	public List<DiaTreino> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(DiaTreino item) {
		DiaTreinoDBHelper dao = getDao();
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
	
	


	private DiaTreinoDBHelper dao = null; 
	protected DiaTreinoDBHelper getDao() {
		if (dao==null) {
			dao = new DiaTreinoDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<DiaTreino> listaSincronizadaAlteracao(Context contexto) {
		List<DiaTreino> saida = null; 
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
	public List<DiaTreino> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<DiaTreino> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	
	//public List<DiaTreino> EncerraDiaTreino(Context contexto){}
	//public List<DiaTreino> ObtemPorData(Context contexto){}
	//public List<DiaTreino> LimpezaBase(Context contexto){}
	//public List<DiaTreino> HistoricoExecucaoPorIdExercicio(Context contexto){}
	//public List<DiaTreino> TreinosPosDataPesquisa(Context contexto){}
	
	




	public List<DiaTreino> getAll(Context contexto) {
		List<DiaTreino> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<DiaTreino> getAllTela(Context contexto) {
		List<DiaTreino> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(DiaTreino item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void excluiParaSincronizacao(DiaTreino item) {
		getDao().deleteSinc(item);
	}
	public void insereParaSincronizacao(DiaTreino item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
		item.setSomenteMemoria(false);
	}
	public void insertAll(List<DiaTreino> lista, Context contexto) {
		DiaTreinoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<DiaTreino> lista, Context contexto) {
		DiaTreinoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		DiaTreinoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		DiaTreinoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		DiaTreinoDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<DiaTreino> getPorSerieDiaSerieTreino(Context contexto, long id) {
		List<DiaTreino> saida = null; 
		try {
			saida = getDao().getPorSerieDiaSerieTreino(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorSerieDiaSerieTreino : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<DiaTreino> getPorSerieDiaSerieTreino(Context contexto, long id, int qtde) {
		List<DiaTreino> saida = null; 
		try {
			saida = getDao().getPorSerieDiaSerieTreino(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorSerieDiaSerieTreino : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	@Override
	public List<DiaTreino> getPorSerieDiaSerieTreino(long id) {
		List<DiaTreino> saida = null; 
		try {
			saida = getDao().getPorSerieDiaSerieTreino(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorSerieDiaSerieTreino : " +  saida.size() + " itens (id_dia_treino=" + id + ")" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<DiaTreino> getPorSerieDiaSerieTreino(long id, int qtde) {
		List<DiaTreino> saida = null; 
		try {
			saida = getDao().getPorSerieDiaSerieTreino(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorSerieDiaSerieTreino : " +  saida.size() + " itens" );
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
	private DiaTreino ultimoInicializado = null;
	public final DiaTreino inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final DiaTreino inicializaItemTela(DiaTreino itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(DiaTreino itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(DiaTreino itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final DiaTreino getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final DiaTreino getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected DiaTreino inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em DiaTreinoServicoSqliteImpl ");
	}
	protected DiaTreino inicializaItemTelaImpl(DiaTreino itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em DiaTreinoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(DiaTreino itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em DiaTreinoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(DiaTreino itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em DiaTreinoServicoSqliteImpl ");
	}
	
	public DiaTreino ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final DiaTreino EncerraDiaTreino(DigicomContexto contexto ) {
		DiaTreino saida = null;
		try {
			saida =  EncerraDiaTreinoImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "EncerraDiaTreino : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract DiaTreino EncerraDiaTreinoImpl(DigicomContexto contexto ) throws DaoException;
	public final DiaTreino ObtemPorData(DigicomContexto contexto ) {
		DiaTreino saida = null;
		try {
			saida =  ObtemPorDataImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ObtemPorData : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract DiaTreino ObtemPorDataImpl(DigicomContexto contexto ) throws DaoException;
	public final DiaTreino LimpezaBase(DigicomContexto contexto ) {
		DiaTreino saida = null;
		try {
			saida =  LimpezaBaseImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "LimpezaBase : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract DiaTreino LimpezaBaseImpl(DigicomContexto contexto ) throws DaoException;
	public final List<DiaTreino> HistoricoExecucaoPorIdExercicio(DigicomContexto contexto ) {
		List<DiaTreino> saida = null;
		try {
			saida =  HistoricoExecucaoPorIdExercicioImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "HistoricoExecucaoPorIdExercicio : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<DiaTreino> HistoricoExecucaoPorIdExercicioImpl(DigicomContexto contexto ) throws DaoException;
	public final List<DiaTreino> TreinosPosDataPesquisa(DigicomContexto contexto ) {
		List<DiaTreino> saida = null;
		try {
			saida =  TreinosPosDataPesquisaImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "TreinosPosDataPesquisa : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<DiaTreino> TreinosPosDataPesquisaImpl(DigicomContexto contexto ) throws DaoException;
	@Override
	public void limpaSinc(List lista) {
		DiaTreinoDBHelper dao = getDao();
		List<DiaTreino> listaItem = lista;
		for (DiaTreino item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public DiaTreino atribuiUsuario(DiaTreino item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}