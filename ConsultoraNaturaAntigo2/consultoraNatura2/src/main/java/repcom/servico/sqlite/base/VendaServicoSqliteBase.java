
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
import repcom.dao.VendaDBHelper;
import repcom.servico.VendaServico;

public abstract class VendaServicoSqliteBase extends  VendaServico 
	implements ServicoLocal<Venda>, WifiServicoI{


	@Override
	public List<Venda> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(Venda item) {
		VendaDBHelper dao = getDao();
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
	
	


	private VendaDBHelper dao = null; 
	protected VendaDBHelper getDao() {
		if (dao==null) {
			dao = new VendaDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<Venda> listaSincronizadaAlteracao(Context contexto) {
		List<Venda> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<Venda> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<Venda> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<Venda> getAll(Context contexto) {
		List<Venda> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<Venda> getAllTela(Context contexto) {
		List<Venda> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(Venda item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(Venda item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<Venda> lista, Context contexto) {
		VendaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<Venda> lista, Context contexto) {
		VendaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		VendaDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		VendaDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		VendaDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<Venda> getPorFeitaParaCliente(Context contexto, long id) {
		List<Venda> saida = null; 
		try {
			saida = getDao().getPorFeitaParaCliente(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorFeitaParaCliente : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<Venda> getPorFeitaParaCliente(Context contexto, long id, int qtde) {
		List<Venda> saida = null; 
		try {
			saida = getDao().getPorFeitaParaCliente(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorFeitaParaCliente : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<Venda> getPorFeitaParaCliente(long id) {
		List<Venda> saida = null; 
		try {
			saida = getDao().getPorFeitaParaCliente(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorFeitaParaCliente : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<Venda> getPorFeitaParaCliente(long id, int qtde) {
		List<Venda> saida = null; 
		try {
			saida = getDao().getPorFeitaParaCliente(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorFeitaParaCliente : " +  saida.size() + " itens" );
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
	private Venda ultimoInicializado = null;
	public final Venda inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final Venda inicializaItemTela(Venda itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(Venda itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(Venda itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final Venda getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final Venda getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected Venda inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em VendaServicoSqliteImpl ");
	}
	protected Venda inicializaItemTelaImpl(Venda itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em VendaServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(Venda itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em VendaServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(Venda itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em VendaServicoSqliteImpl ");
	}
	
	public Venda ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		VendaDBHelper dao = getDao();
		List<Venda> listaItem = lista;
		for (Venda item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public Venda atribuiUsuario(Venda item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}