
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
import treinoacademia.dao.ExecucaoItemSerieDBHelper;
import treinoacademia.servico.ExecucaoItemSerieServico;

public abstract class ExecucaoItemSerieServicoSqliteBase extends  ExecucaoItemSerieServico 
	implements ServicoLocal<ExecucaoItemSerie>, WifiServicoI{


	@Override
	public List<ExecucaoItemSerie> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(ExecucaoItemSerie item) {
		ExecucaoItemSerieDBHelper dao = getDao();
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
	
	


	private ExecucaoItemSerieDBHelper dao = null; 
	protected ExecucaoItemSerieDBHelper getDao() {
		if (dao==null) {
			dao = new ExecucaoItemSerieDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<ExecucaoItemSerie> listaSincronizadaAlteracao(Context contexto) {
		List<ExecucaoItemSerie> saida = null; 
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
	public List<ExecucaoItemSerie> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	
	//public List<ExecucaoItemSerie> ObtemPorDiaItemSerie(Context contexto){}
	//public List<ExecucaoItemSerie> UltimasExecucoesUsuarioExercicio(Context contexto){}
	//public List<ExecucaoItemSerie> UltimasExecucoesItemSerie(Context contexto){}
	//public List<ExecucaoItemSerie> CarregaCompletoPorDia(Context contexto){}
	//public List<ExecucaoItemSerie> PrimeiraPorDia(Context contexto){}
	//public List<ExecucaoItemSerie> UltimaPorDia(Context contexto){}
	//public List<ExecucaoItemSerie> PrimeiraPorSerie(Context contexto){}
	
	




	public List<ExecucaoItemSerie> getAll(Context contexto) {
		List<ExecucaoItemSerie> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<ExecucaoItemSerie> getAllTela(Context contexto) {
		List<ExecucaoItemSerie> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(ExecucaoItemSerie item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void excluiParaSincronizacao(ExecucaoItemSerie item) {
		getDao().deleteSinc(item);
	}
	public void insereParaSincronizacao(ExecucaoItemSerie item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
		item.setSomenteMemoria(false);
	}
	public void insertAll(List<ExecucaoItemSerie> lista, Context contexto) {
		ExecucaoItemSerieDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<ExecucaoItemSerie> lista, Context contexto) {
		ExecucaoItemSerieDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ExecucaoItemSerieDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ExecucaoItemSerieDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ExecucaoItemSerieDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(Context contexto, long id) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorReferenteAItemSerie(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAItemSerie : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(Context contexto, long id, int qtde) {
		List<ExecucaoItemSerie> saida = null; 
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
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(long id) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorReferenteAItemSerie(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAItemSerie : " +  saida.size() + " itens (id_execucao_item_serie=" + id + ")" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(long id, int qtde) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorReferenteAItemSerie(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAItemSerie : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	@Override
	public List<ExecucaoItemSerie> getPorEmDiaTreino(Context contexto, long id) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorEmDiaTreino(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorEmDiaTreino : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ExecucaoItemSerie> getPorEmDiaTreino(Context contexto, long id, int qtde) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorEmDiaTreino(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorEmDiaTreino : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	@Override
	public List<ExecucaoItemSerie> getPorEmDiaTreino(long id) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorEmDiaTreino(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorEmDiaTreino : " +  saida.size() + " itens (id_execucao_item_serie=" + id + ")" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ExecucaoItemSerie> getPorEmDiaTreino(long id, int qtde) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorEmDiaTreino(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorEmDiaTreino : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(Context contexto, long id) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorReferenteAExercicio(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAExercicio : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(Context contexto, long id, int qtde) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorReferenteAExercicio(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAExercicio : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(long id) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorReferenteAExercicio(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAExercicio : " +  saida.size() + " itens (id_execucao_item_serie=" + id + ")" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(long id, int qtde) {
		List<ExecucaoItemSerie> saida = null; 
		try {
			saida = getDao().getPorReferenteAExercicio(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAExercicio : " +  saida.size() + " itens" );
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
	private ExecucaoItemSerie ultimoInicializado = null;
	public final ExecucaoItemSerie inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final ExecucaoItemSerie inicializaItemTela(ExecucaoItemSerie itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(ExecucaoItemSerie itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(ExecucaoItemSerie itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final ExecucaoItemSerie getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final ExecucaoItemSerie getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected ExecucaoItemSerie inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ExecucaoItemSerieServicoSqliteImpl ");
	}
	protected ExecucaoItemSerie inicializaItemTelaImpl(ExecucaoItemSerie itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ExecucaoItemSerieServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(ExecucaoItemSerie itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ExecucaoItemSerieServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(ExecucaoItemSerie itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ExecucaoItemSerieServicoSqliteImpl ");
	}
	
	public ExecucaoItemSerie ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final List<ExecucaoItemSerie> ObtemPorDiaItemSerie(DigicomContexto contexto ) {
		List<ExecucaoItemSerie> saida = null;
		try {
			saida =  ObtemPorDiaItemSerieImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ObtemPorDiaItemSerie : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<ExecucaoItemSerie> ObtemPorDiaItemSerieImpl(DigicomContexto contexto ) throws DaoException;
	public final List<ExecucaoItemSerie> UltimasExecucoesUsuarioExercicio(DigicomContexto contexto ) {
		List<ExecucaoItemSerie> saida = null;
		try {
			saida =  UltimasExecucoesUsuarioExercicioImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "UltimasExecucoesUsuarioExercicio : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<ExecucaoItemSerie> UltimasExecucoesUsuarioExercicioImpl(DigicomContexto contexto ) throws DaoException;
	public final List<ExecucaoItemSerie> UltimasExecucoesItemSerie(DigicomContexto contexto ) {
		List<ExecucaoItemSerie> saida = null;
		try {
			saida =  UltimasExecucoesItemSerieImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "UltimasExecucoesItemSerie : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<ExecucaoItemSerie> UltimasExecucoesItemSerieImpl(DigicomContexto contexto ) throws DaoException;
	public final List<ExecucaoItemSerie> CarregaCompletoPorDia(DigicomContexto contexto ) {
		List<ExecucaoItemSerie> saida = null;
		try {
			saida =  CarregaCompletoPorDiaImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "CarregaCompletoPorDia : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<ExecucaoItemSerie> CarregaCompletoPorDiaImpl(DigicomContexto contexto ) throws DaoException;
	public final ExecucaoItemSerie PrimeiraPorDia(DigicomContexto contexto ) {
		ExecucaoItemSerie saida = null;
		try {
			saida =  PrimeiraPorDiaImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "PrimeiraPorDia : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract ExecucaoItemSerie PrimeiraPorDiaImpl(DigicomContexto contexto ) throws DaoException;
	public final ExecucaoItemSerie UltimaPorDia(DigicomContexto contexto ) {
		ExecucaoItemSerie saida = null;
		try {
			saida =  UltimaPorDiaImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "UltimaPorDia : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract ExecucaoItemSerie UltimaPorDiaImpl(DigicomContexto contexto ) throws DaoException;
	public final ExecucaoItemSerie PrimeiraPorSerie(DigicomContexto contexto ) {
		ExecucaoItemSerie saida = null;
		try {
			saida =  PrimeiraPorSerieImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "PrimeiraPorSerie : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract ExecucaoItemSerie PrimeiraPorSerieImpl(DigicomContexto contexto ) throws DaoException;
	@Override
	public void limpaSinc(List lista) {
		ExecucaoItemSerieDBHelper dao = getDao();
		List<ExecucaoItemSerie> listaItem = lista;
		for (ExecucaoItemSerie item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public ExecucaoItemSerie atribuiUsuario(ExecucaoItemSerie item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}