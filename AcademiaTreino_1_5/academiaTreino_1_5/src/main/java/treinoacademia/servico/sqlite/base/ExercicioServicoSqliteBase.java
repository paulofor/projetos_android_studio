
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
import treinoacademia.dao.ExercicioDBHelper;
import treinoacademia.servico.ExercicioServico;

public abstract class ExercicioServicoSqliteBase extends  ExercicioServico 
	implements ServicoLocal<Exercicio>, WifiServicoI{


	@Override
	public List<Exercicio> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(Exercicio item) {
		ExercicioDBHelper dao = getDao();
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
	
	


	private ExercicioDBHelper dao = null; 
	protected ExercicioDBHelper getDao() {
		if (dao==null) {
			dao = new ExercicioDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<Exercicio> listaSincronizadaAlteracao(Context contexto) {
		List<Exercicio> saida = null; 
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
	public List<Exercicio> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<Exercicio> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	
	//public List<Exercicio> ListaAtivosNoMomento(Context contexto){}
	
	




	public List<Exercicio> getAll(Context contexto) {
		List<Exercicio> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<Exercicio> getAllTela(Context contexto) {
		List<Exercicio> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(Exercicio item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void excluiParaSincronizacao(Exercicio item) {
		getDao().deleteSinc(item);
	}
	public void insereParaSincronizacao(Exercicio item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
		item.setSomenteMemoria(false);
	}
	public void insertAll(List<Exercicio> lista, Context contexto) {
		ExercicioDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<Exercicio> lista, Context contexto) {
		ExercicioDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ExercicioDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ExercicioDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ExercicioDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<Exercicio> getPorParaGrupoMuscular(Context contexto, long id) {
		List<Exercicio> saida = null; 
		try {
			saida = getDao().getPorParaGrupoMuscular(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorParaGrupoMuscular : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<Exercicio> getPorParaGrupoMuscular(Context contexto, long id, int qtde) {
		List<Exercicio> saida = null; 
		try {
			saida = getDao().getPorParaGrupoMuscular(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorParaGrupoMuscular : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	@Override
	public List<Exercicio> getPorParaGrupoMuscular(long id) {
		List<Exercicio> saida = null; 
		try {
			saida = getDao().getPorParaGrupoMuscular(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorParaGrupoMuscular : " +  saida.size() + " itens (id_exercicio=" + id + ")" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<Exercicio> getPorParaGrupoMuscular(long id, int qtde) {
		List<Exercicio> saida = null; 
		try {
			saida = getDao().getPorParaGrupoMuscular(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorParaGrupoMuscular : " +  saida.size() + " itens" );
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
	private Exercicio ultimoInicializado = null;
	public final Exercicio inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final Exercicio inicializaItemTela(Exercicio itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(Exercicio itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(Exercicio itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final Exercicio getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final Exercicio getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected Exercicio inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ExercicioServicoSqliteImpl ");
	}
	protected Exercicio inicializaItemTelaImpl(Exercicio itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ExercicioServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(Exercicio itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ExercicioServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(Exercicio itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ExercicioServicoSqliteImpl ");
	}
	
	public Exercicio ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final List<Exercicio> ListaAtivosNoMomento(DigicomContexto contexto ) {
		List<Exercicio> saida = null;
		try {
			saida =  ListaAtivosNoMomentoImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ListaAtivosNoMomento : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<Exercicio> ListaAtivosNoMomentoImpl(DigicomContexto contexto ) throws DaoException;
	@Override
	public void limpaSinc(List lista) {
		ExercicioDBHelper dao = getDao();
		List<Exercicio> listaItem = lista;
		for (Exercicio item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public Exercicio atribuiUsuario(Exercicio item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}