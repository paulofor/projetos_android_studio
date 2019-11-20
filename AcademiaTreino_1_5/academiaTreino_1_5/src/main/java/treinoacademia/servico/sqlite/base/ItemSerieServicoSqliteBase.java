
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
import treinoacademia.dao.ItemSerieDBHelper;
import treinoacademia.servico.ItemSerieServico;

public abstract class ItemSerieServicoSqliteBase extends  ItemSerieServico 
	implements ServicoLocal<ItemSerie>, WifiServicoI{


	@Override
	public List<ItemSerie> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(ItemSerie item) {
		ItemSerieDBHelper dao = getDao();
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
	
	


	private ItemSerieDBHelper dao = null; 
	protected ItemSerieDBHelper getDao() {
		if (dao==null) {
			dao = new ItemSerieDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<ItemSerie> listaSincronizadaAlteracao(Context contexto) {
		List<ItemSerie> saida = null; 
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
	public List<ItemSerie> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	
	//public List<ItemSerie> ListaPorDiaComExecucao(Context contexto){}
	//public List<ItemSerie> FinalizaItemSerie(Context contexto){}
	//public List<ItemSerie> CarregaCompleto(Context contexto){}
	//public List<ItemSerie> CarregaUltimasExecucoes(Context contexto){}
	//public List<ItemSerie> AtualizaCarga(Context contexto){}
	
	




	public List<ItemSerie> getAll(Context contexto) {
		List<ItemSerie> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<ItemSerie> getAllTela(Context contexto) {
		List<ItemSerie> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(ItemSerie item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void excluiParaSincronizacao(ItemSerie item) {
		getDao().deleteSinc(item);
	}
	public void insereParaSincronizacao(ItemSerie item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		getDao().insertSinc(item);
		item.setSomenteMemoria(false);
	}
	public void insertAll(List<ItemSerie> lista, Context contexto) {
		ItemSerieDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<ItemSerie> lista, Context contexto) {
		ItemSerieDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ItemSerieDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ItemSerieDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ItemSerieDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<ItemSerie> getPorExecucaoDeExercicio(Context contexto, long id) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getPorExecucaoDeExercicio(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorExecucaoDeExercicio : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ItemSerie> getPorExecucaoDeExercicio(Context contexto, long id, int qtde) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getPorExecucaoDeExercicio(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorExecucaoDeExercicio : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	@Override
	public List<ItemSerie> getPorExecucaoDeExercicio(long id) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getPorExecucaoDeExercicio(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorExecucaoDeExercicio : " +  saida.size() + " itens (id_item_serie=" + id + ")" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ItemSerie> getPorExecucaoDeExercicio(long id, int qtde) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getPorExecucaoDeExercicio(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorExecucaoDeExercicio : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	
	@Override
	public List<ItemSerie> getPorPertencenteASerieTreino(Context contexto, long id) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getPorPertencenteASerieTreino(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertencenteASerieTreino : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ItemSerie> getPorPertencenteASerieTreino(Context contexto, long id, int qtde) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getPorPertencenteASerieTreino(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertencenteASerieTreino : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	@Override
	public List<ItemSerie> getPorPertencenteASerieTreino(long id) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getPorPertencenteASerieTreino(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertencenteASerieTreino : " +  saida.size() + " itens (id_item_serie=" + id + ")" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	public List<ItemSerie> getPorPertencenteASerieTreino(long id, int qtde) {
		List<ItemSerie> saida = null; 
		try {
			saida = getDao().getPorPertencenteASerieTreino(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorPertencenteASerieTreino : " +  saida.size() + " itens" );
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
	private ItemSerie ultimoInicializado = null;
	public final ItemSerie inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final ItemSerie inicializaItemTela(ItemSerie itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(ItemSerie itemTela, boolean insercao, DigicomContexto contexto){
		
		if (insercao) {
			UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			Usuario user = userSrv.getCorrente();
			itemTela.setUsuario_Sincroniza(user);
		}
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(ItemSerie itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final ItemSerie getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final ItemSerie getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected ItemSerie inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ItemSerieServicoSqliteImpl ");
	}
	protected ItemSerie inicializaItemTelaImpl(ItemSerie itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ItemSerieServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(ItemSerie itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ItemSerieServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(ItemSerie itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ItemSerieServicoSqliteImpl ");
	}
	
	public ItemSerie ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	public final List<ItemSerie> ListaPorDiaComExecucao(DigicomContexto contexto ) {
		List<ItemSerie> saida = null;
		try {
			saida =  ListaPorDiaComExecucaoImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "ListaPorDiaComExecucao : " +  saida.size() + " itens" );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract List<ItemSerie> ListaPorDiaComExecucaoImpl(DigicomContexto contexto ) throws DaoException;
	public final ItemSerie FinalizaItemSerie(DigicomContexto contexto ) {
		ItemSerie saida = null;
		try {
			saida =  FinalizaItemSerieImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "FinalizaItemSerie : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract ItemSerie FinalizaItemSerieImpl(DigicomContexto contexto ) throws DaoException;
	public final ItemSerie CarregaCompleto(DigicomContexto contexto ) {
		ItemSerie saida = null;
		try {
			saida =  CarregaCompletoImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "CarregaCompleto : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract ItemSerie CarregaCompletoImpl(DigicomContexto contexto ) throws DaoException;
	public final ItemSerie CarregaUltimasExecucoes(DigicomContexto contexto ) {
		ItemSerie saida = null;
		try {
			saida =  CarregaUltimasExecucoesImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "CarregaUltimasExecucoes : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract ItemSerie CarregaUltimasExecucoesImpl(DigicomContexto contexto ) throws DaoException;
	public final ItemSerie AtualizaCarga(DigicomContexto contexto ) {
		ItemSerie saida = null;
		try {
			saida =  AtualizaCargaImpl(contexto);
			DCLog.dStack(DCLog.SERVICO_OPERACAO, this, "AtualizaCarga : " +  (saida!=null?saida.toString():"null") );
		} catch (Exception e) {
			TrataErro.getInstancia().setErro(e);
		}
		return saida;
	}
	protected abstract ItemSerie AtualizaCargaImpl(DigicomContexto contexto ) throws DaoException;
	@Override
	public void limpaSinc(List lista) {
		ItemSerieDBHelper dao = getDao();
		List<ItemSerie> listaItem = lista;
		for (ItemSerie item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	


	@Override
	public ItemSerie atribuiUsuario(ItemSerie item) {
		
		UsuarioServico userSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = userSrv.getCorrente();
		item.setUsuario_Sincroniza(usuario);
		
		return item;
	}
}