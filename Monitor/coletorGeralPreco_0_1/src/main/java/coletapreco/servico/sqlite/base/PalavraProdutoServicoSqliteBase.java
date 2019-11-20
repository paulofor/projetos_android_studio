
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
import coletapreco.dao.PalavraProdutoDBHelper;
import coletapreco.servico.PalavraProdutoServico;

public abstract class PalavraProdutoServicoSqliteBase extends  PalavraProdutoServico 
	implements ServicoLocal<PalavraProduto>, WifiServicoI{


	@Override
	public List<PalavraProduto> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(PalavraProduto item) {
		PalavraProdutoDBHelper dao = getDao();
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
	
	


	private PalavraProdutoDBHelper dao = null; 
	protected PalavraProdutoDBHelper getDao() {
		if (dao==null) {
			dao = new PalavraProdutoDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<PalavraProduto> listaSincronizadaAlteracao(Context contexto) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<PalavraProduto> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<PalavraProduto> getAll(Context contexto) {
		List<PalavraProduto> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<PalavraProduto> getAllTela(Context contexto) {
		List<PalavraProduto> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(PalavraProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(PalavraProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<PalavraProduto> lista, Context contexto) {
		PalavraProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<PalavraProduto> lista, Context contexto) {
		PalavraProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		PalavraProdutoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		PalavraProdutoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		PalavraProdutoDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<PalavraProduto> getPorRelaciondoAPalavra(Context contexto, long id) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getPorRelaciondoAPalavra(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorRelaciondoAPalavra : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<PalavraProduto> getPorRelaciondoAPalavra(Context contexto, long id, int qtde) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getPorRelaciondoAPalavra(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorRelaciondoAPalavra : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<PalavraProduto> getPorRelaciondoAPalavra(long id) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getPorRelaciondoAPalavra(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorRelaciondoAPalavra : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<PalavraProduto> getPorRelaciondoAPalavra(long id, int qtde) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getPorRelaciondoAPalavra(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorRelaciondoAPalavra : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<PalavraProduto> getPorRelaciondoAProduto(Context contexto, long id) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getPorRelaciondoAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorRelaciondoAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<PalavraProduto> getPorRelaciondoAProduto(Context contexto, long id, int qtde) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getPorRelaciondoAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorRelaciondoAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<PalavraProduto> getPorRelaciondoAProduto(long id) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getPorRelaciondoAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorRelaciondoAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<PalavraProduto> getPorRelaciondoAProduto(long id, int qtde) {
		List<PalavraProduto> saida = null; 
		try {
			saida = getDao().getPorRelaciondoAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorRelaciondoAProduto : " +  saida.size() + " itens" );
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
	private PalavraProduto ultimoInicializado = null;
	public final PalavraProduto inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final PalavraProduto inicializaItemTela(PalavraProduto itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(PalavraProduto itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(PalavraProduto itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final PalavraProduto getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final PalavraProduto getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected PalavraProduto inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em PalavraProdutoServicoSqliteImpl ");
	}
	protected PalavraProduto inicializaItemTelaImpl(PalavraProduto itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em PalavraProdutoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(PalavraProduto itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em PalavraProdutoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(PalavraProduto itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em PalavraProdutoServicoSqliteImpl ");
	}
	
	public PalavraProduto ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		PalavraProdutoDBHelper dao = getDao();
		List<PalavraProduto> listaItem = lista;
		for (PalavraProduto item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	
	
	public void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos) {
		PalavraProduto novo = FabricaVo.criaPalavraProduto();
		List<PalavraProduto> listaBanco = this.getPorRelaciondoAProduto(null, item.getId());
		// lista insercao
		List<PalavraProduto> listaInsercao = new ArrayList<PalavraProduto>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (PalavraProduto rel : listaBanco) {
				if (obj.getId()==rel.getIdPalavraRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				PalavraProduto novoRel = FabricaVo.criaPalavraProduto();
				novoRel.setProduto_RelaciondoA(item);
				novoRel.setPalavra_RelaciondoA((Palavra)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<PalavraProduto> listaExclusao = new ArrayList<PalavraProduto>();
		for (PalavraProduto itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdPalavraRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				listaExclusao.add(itemBanco);
			}
		}
		//  ***********************************************************************************************
		DCLog.d(DCLog.SERVICO_TRATAMENTO_DADOS_TELA, this, "ListaInsercao: " + listaInsercao.size());
		DCLog.d(DCLog.SERVICO_TRATAMENTO_DADOS_TELA, this, "ListaExclusao: " + listaExclusao.size());
		//  ***********************************************************************************************
		PalavraProdutoDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			PalavraProduto novoRel = (PalavraProduto) obj;
			novoRel.setProduto_RelaciondoA(item);
			dao.insertSinc(novoRel);
		}	
		for (PalavraProduto obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	
	public void atualizaRelacionamento(Palavra item, List<DCIObjetoDominio> listaEscolhidos) {
		PalavraProduto novo = FabricaVo.criaPalavraProduto();
		List<PalavraProduto> listaBanco = this.getPorRelaciondoAPalavra(null, item.getId());
		// lista insercao
		List<PalavraProduto> listaInsercao = new ArrayList<PalavraProduto>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (PalavraProduto rel : listaBanco) {
				if (obj.getId()==rel.getIdProdutoRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				PalavraProduto novoRel = FabricaVo.criaPalavraProduto();
				novoRel.setPalavra_RelaciondoA(item);
				novoRel.setProduto_RelaciondoA((Produto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<PalavraProduto> listaExclusao = new ArrayList<PalavraProduto>();
		for (PalavraProduto itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdProdutoRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				listaExclusao.add(itemBanco);
			}
		}
		//  ***********************************************************************************************
		DCLog.d(DCLog.SERVICO_TRATAMENTO_DADOS_TELA, this, "ListaInsercao: " + listaInsercao.size());
		DCLog.d(DCLog.SERVICO_TRATAMENTO_DADOS_TELA, this, "ListaExclusao: " + listaExclusao.size());
		//  ***********************************************************************************************
		PalavraProdutoDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			PalavraProduto novoRel = (PalavraProduto) obj;
			novoRel.setPalavra_RelaciondoA(item);
			dao.insertSinc(novoRel);
		}	
		for (PalavraProduto obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		boolean saida = false;
		if (item instanceof Palavra) {
			Palavra obj = (Palavra) item;
			PalavraProduto rel = (PalavraProduto) relacionamento;
			saida = rel.getIdPalavraRa()== obj.getId();
		}
		if (item instanceof Produto) {
			Produto obj = (Produto) item;
			PalavraProduto rel = (PalavraProduto) relacionamento;
			saida = rel.getIdProdutoRa()== obj.getId();
		}
		return saida;
	}


	@Override
	public PalavraProduto atribuiUsuario(PalavraProduto item) {
		
		return item;
	}
}