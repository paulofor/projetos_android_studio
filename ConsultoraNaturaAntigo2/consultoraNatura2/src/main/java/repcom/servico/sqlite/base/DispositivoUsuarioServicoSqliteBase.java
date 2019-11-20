
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
import repcom.dao.DispositivoUsuarioDBHelper;
import repcom.servico.DispositivoUsuarioServico;

public abstract class DispositivoUsuarioServicoSqliteBase extends  DispositivoUsuarioServico 
	implements ServicoLocal<DispositivoUsuario>, WifiServicoI{


	@Override
	public List<DispositivoUsuario> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(DispositivoUsuario item) {
		DispositivoUsuarioDBHelper dao = getDao();
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
	
	
	private DispositivoUsuario corrente = null;
	public DispositivoUsuario getCorrente() {
		if (corrente==null) {
			corrente = getDao().getCorrente();
		}
		return corrente;
	}
	


	private DispositivoUsuarioDBHelper dao = null; 
	protected DispositivoUsuarioDBHelper getDao() {
		if (dao==null) {
			dao = new DispositivoUsuarioDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<DispositivoUsuario> listaSincronizadaAlteracao(Context contexto) {
		List<DispositivoUsuario> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<DispositivoUsuario> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<DispositivoUsuario> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<DispositivoUsuario> getAll(Context contexto) {
		List<DispositivoUsuario> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<DispositivoUsuario> getAllTela(Context contexto) {
		List<DispositivoUsuario> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(DispositivoUsuario item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(DispositivoUsuario item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<DispositivoUsuario> lista, Context contexto) {
		DispositivoUsuarioDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<DispositivoUsuario> lista, Context contexto) {
		DispositivoUsuarioDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		DispositivoUsuarioDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		DispositivoUsuarioDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		DispositivoUsuarioDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<DispositivoUsuario> getPorReferenteAUsuario(Context contexto, long id) {
		List<DispositivoUsuario> saida = null; 
		try {
			saida = getDao().getPorReferenteAUsuario(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAUsuario : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<DispositivoUsuario> getPorReferenteAUsuario(Context contexto, long id, int qtde) {
		List<DispositivoUsuario> saida = null; 
		try {
			saida = getDao().getPorReferenteAUsuario(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAUsuario : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<DispositivoUsuario> getPorReferenteAUsuario(long id) {
		List<DispositivoUsuario> saida = null; 
		try {
			saida = getDao().getPorReferenteAUsuario(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAUsuario : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<DispositivoUsuario> getPorReferenteAUsuario(long id, int qtde) {
		List<DispositivoUsuario> saida = null; 
		try {
			saida = getDao().getPorReferenteAUsuario(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAUsuario : " +  saida.size() + " itens" );
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
	private DispositivoUsuario ultimoInicializado = null;
	public final DispositivoUsuario inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final DispositivoUsuario inicializaItemTela(DispositivoUsuario itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(DispositivoUsuario itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(DispositivoUsuario itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final DispositivoUsuario getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final DispositivoUsuario getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected DispositivoUsuario inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em DispositivoUsuarioServicoSqliteImpl ");
	}
	protected DispositivoUsuario inicializaItemTelaImpl(DispositivoUsuario itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em DispositivoUsuarioServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(DispositivoUsuario itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em DispositivoUsuarioServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(DispositivoUsuario itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em DispositivoUsuarioServicoSqliteImpl ");
	}
	
	public DispositivoUsuario ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		DispositivoUsuarioDBHelper dao = getDao();
		List<DispositivoUsuario> listaItem = lista;
		for (DispositivoUsuario item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public DispositivoUsuario atribuiUsuario(DispositivoUsuario item) {
		
		return item;
	}
}