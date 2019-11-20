
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
import treinoacademia.dao.SerieTreinoDBHelper;
import treinoacademia.servico.SerieTreinoServico;

public abstract class SerieTreinoServicoSqliteBase extends  SerieTreinoServico 
	implements ServicoLocal<SerieTreino>, WifiServicoI{


	@Override
	public List<SerieTreino> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(SerieTreino item) {
		SerieTreinoDBHelper dao = getDao();
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
	
	


	private SerieTreinoDBHelper dao = null; 
	protected SerieTreinoDBHelper getDao() {
		//if (dao==null) {
			dao = new SerieTreinoDBHelper();
		//}
		return dao;
	}
	
	@Override
	public List<SerieTreino> listaSincronizadaAlteracao(Context contexto) {
		List<SerieTreino> saida = null; 
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
	public List<SerieTreino> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<SerieTreino> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	
	//public List<SerieTreino> ObtemProxima(Context contexto){}
	//public List<SerieTreino> ObtemMontadaPorId(Context contexto){}
	//public List<SerieTreino> CriaSerieCompleta(Context contexto){}
	//public List<SerieTreino> CarregaItemSerie(Context contexto){}
	
	




	public List<SerieTreino> getAll(Context contexto) {
		List<SerieTreino> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<SerieTreino> getAllTela(Context contexto) {
		List<SerieTreino> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(SerieTreino item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void excluiParaSincronizacao(SerieTreino item) {
		getDao().deleteSinc(item);
	}
	public void insereParaSincronizacao(SerieTreino item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
		item.setSomenteMemoria(false);
	}
	public void insertAll(List<SerieTreino> lista, Context contexto) {
		SerieTreinoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<SerieTreino> lista, Context contexto) {
		SerieTreinoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		SerieTreinoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		SerieTreinoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		SerieTreinoDBHelper dao = getDao();
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
	private SerieTreino ultimoInicializado = null;
	public final SerieTreino inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final SerieTreino inicializaItemTela(SerieTreino itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(SerieTreino itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(SerieTreino itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final SerieTreino getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final SerieTreino getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected SerieTreino inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em SerieTreinoServicoSqliteImpl ");
	}
	protected SerieTreino inicializaItemTelaImpl(SerieTreino itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em SerieTreinoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(SerieTreino itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em SerieTreinoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(SerieTreino itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em SerieTreinoServicoSqliteImpl ");
	}
	
	public SerieTreino ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final SerieTreino ObtemProxima(DigicomContexto contexto ) {
		SerieTreino saida = null;
		try {
			saida =  ObtemProximaImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ObtemProxima : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract SerieTreino ObtemProximaImpl(DigicomContexto contexto ) throws DaoException;
	public final SerieTreino ObtemMontadaPorId(DigicomContexto contexto ) {
		SerieTreino saida = null;
		try {
			saida =  ObtemMontadaPorIdImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ObtemMontadaPorId : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract SerieTreino ObtemMontadaPorIdImpl(DigicomContexto contexto ) throws DaoException;
	public final SerieTreino CriaSerieCompleta(DigicomContexto contexto ) {
		SerieTreino saida = null;
		try {
			saida =  CriaSerieCompletaImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "CriaSerieCompleta : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract SerieTreino CriaSerieCompletaImpl(DigicomContexto contexto ) throws DaoException;
	public final SerieTreino CarregaItemSerie(DigicomContexto contexto ) {
		SerieTreino saida = null;
		try {
			saida =  CarregaItemSerieImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "CarregaItemSerie : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract SerieTreino CarregaItemSerieImpl(DigicomContexto contexto ) throws DaoException;
	@Override
	public void limpaSinc(List lista) {
		SerieTreinoDBHelper dao = getDao();
		List<SerieTreino> listaItem = lista;
		for (SerieTreino item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public SerieTreino atribuiUsuario(SerieTreino item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}