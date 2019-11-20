
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
import repcom.dao.ClienteDBHelper;
import repcom.servico.ClienteServico;

public abstract class ClienteServicoSqliteBase extends  ClienteServico 
	implements ServicoLocal<Cliente>, WifiServicoI{


	@Override
	public List<Cliente> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(Cliente item) {
		ClienteDBHelper dao = getDao();
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
	
	


	private ClienteDBHelper dao = null; 
	protected ClienteDBHelper getDao() {
		if (dao==null) {
			dao = new ClienteDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<Cliente> listaSincronizadaAlteracao(Context contexto) {
		List<Cliente> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<Cliente> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<Cliente> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	//public List<Cliente> ObtemListaAgendaTel(Context contexto){}
	//public List<Cliente> SincronizaAgendaTel(Context contexto){}
	//public List<Cliente> ObtemPorIdAgendaTel(Context contexto){}
	//public List<Cliente> PreencheDerivadaAgendaTel(Context contexto){}
	//public List<Cliente> DesativaPorId(Context contexto){}
	//public List<Cliente> ListaAtivos(Context contexto){}
	//public List<Cliente> PesquisaTrechoNome(Context contexto){}
	
	




	public List<Cliente> getAll(Context contexto) {
		List<Cliente> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<Cliente> getAllTela(Context contexto) {
		List<Cliente> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(Cliente item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(Cliente item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<Cliente> lista, Context contexto) {
		ClienteDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<Cliente> lista, Context contexto) {
		ClienteDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ClienteDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ClienteDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ClienteDBHelper dao = getDao();
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
	private Cliente ultimoInicializado = null;
	public final Cliente inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final Cliente inicializaItemTela(Cliente itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(Cliente itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(Cliente itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final Cliente getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final Cliente getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected Cliente inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ClienteServicoSqliteImpl ");
	}
	protected Cliente inicializaItemTelaImpl(Cliente itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ClienteServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(Cliente itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ClienteServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(Cliente itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ClienteServicoSqliteImpl ");
	}
	
	public Cliente ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final List<Cliente> ObtemListaAgendaTel(DigicomContexto contexto ) {
		List<Cliente> saida = ObtemListaAgendaTelImpl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ObtemListaAgendaTel : " +  saida.size() + " itens" );
		return saida;
	}
	protected abstract List<Cliente> ObtemListaAgendaTelImpl(DigicomContexto contexto );
	public final List<Cliente> SincronizaAgendaTel(DigicomContexto contexto ) {
		List<Cliente> saida = SincronizaAgendaTelImpl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "SincronizaAgendaTel : " +  saida.size() + " itens" );
		return saida;
	}
	protected abstract List<Cliente> SincronizaAgendaTelImpl(DigicomContexto contexto );
	public final Cliente ObtemPorIdAgendaTel(DigicomContexto contexto ) {
		Cliente saida = ObtemPorIdAgendaTelImpl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ObtemPorIdAgendaTel : " +  (saida!=null?saida.toString():"null") );
		return saida;
	}
	protected abstract Cliente ObtemPorIdAgendaTelImpl(DigicomContexto contexto );
	public final Cliente PreencheDerivadaAgendaTel(DigicomContexto contexto ) {
		Cliente saida = PreencheDerivadaAgendaTelImpl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "PreencheDerivadaAgendaTel : " +  (saida!=null?saida.toString():"null") );
		return saida;
	}
	protected abstract Cliente PreencheDerivadaAgendaTelImpl(DigicomContexto contexto );
	public final Cliente DesativaPorId(DigicomContexto contexto ) {
		Cliente saida = DesativaPorIdImpl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "DesativaPorId : " +  (saida!=null?saida.toString():"null") );
		return saida;
	}
	protected abstract Cliente DesativaPorIdImpl(DigicomContexto contexto );
	public final List<Cliente> ListaAtivos(DigicomContexto contexto ) {
		List<Cliente> saida = ListaAtivosImpl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ListaAtivos : " +  saida.size() + " itens" );
		return saida;
	}
	protected abstract List<Cliente> ListaAtivosImpl(DigicomContexto contexto );
	public final List<Cliente> PesquisaTrechoNome(DigicomContexto contexto ) {
		List<Cliente> saida = PesquisaTrechoNomeImpl(contexto);
		DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "PesquisaTrechoNome : " +  saida.size() + " itens" );
		return saida;
	}
	protected abstract List<Cliente> PesquisaTrechoNomeImpl(DigicomContexto contexto );
	@Override
	public void limpaSinc(List lista) {
		ClienteDBHelper dao = getDao();
		List<Cliente> listaItem = lista;
		for (Cliente item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public Cliente atribuiUsuario(Cliente item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}