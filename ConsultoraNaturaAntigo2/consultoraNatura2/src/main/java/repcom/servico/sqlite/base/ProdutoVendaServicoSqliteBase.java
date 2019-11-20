
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
import repcom.dao.ProdutoVendaDBHelper;
import repcom.servico.ProdutoVendaServico;

public abstract class ProdutoVendaServicoSqliteBase extends  ProdutoVendaServico 
	implements ServicoLocal<ProdutoVenda>, WifiServicoI{


	@Override
	public List<ProdutoVenda> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(ProdutoVenda item) {
		ProdutoVendaDBHelper dao = getDao();
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
	
	


	private ProdutoVendaDBHelper dao = null; 
	protected ProdutoVendaDBHelper getDao() {
		if (dao==null) {
			dao = new ProdutoVendaDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<ProdutoVenda> listaSincronizadaAlteracao(Context contexto) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ProdutoVenda> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<ProdutoVenda> getAll(Context contexto) {
		List<ProdutoVenda> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<ProdutoVenda> getAllTela(Context contexto) {
		List<ProdutoVenda> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(ProdutoVenda item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(ProdutoVenda item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<ProdutoVenda> lista, Context contexto) {
		ProdutoVendaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<ProdutoVenda> lista, Context contexto) {
		ProdutoVendaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ProdutoVendaDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ProdutoVendaDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ProdutoVendaDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<ProdutoVenda> getPorAssociadaProduto(Context contexto, long id) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getPorAssociadaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ProdutoVenda> getPorAssociadaProduto(Context contexto, long id, int qtde) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getPorAssociadaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ProdutoVenda> getPorAssociadaProduto(long id) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getPorAssociadaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ProdutoVenda> getPorAssociadaProduto(long id, int qtde) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getPorAssociadaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<ProdutoVenda> getPorAssociadaVenda(Context contexto, long id) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getPorAssociadaVenda(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaVenda : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ProdutoVenda> getPorAssociadaVenda(Context contexto, long id, int qtde) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getPorAssociadaVenda(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaVenda : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ProdutoVenda> getPorAssociadaVenda(long id) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getPorAssociadaVenda(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaVenda : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ProdutoVenda> getPorAssociadaVenda(long id, int qtde) {
		List<ProdutoVenda> saida = null; 
		try {
			saida = getDao().getPorAssociadaVenda(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaVenda : " +  saida.size() + " itens" );
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
	private ProdutoVenda ultimoInicializado = null;
	public final ProdutoVenda inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final ProdutoVenda inicializaItemTela(ProdutoVenda itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(ProdutoVenda itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(ProdutoVenda itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final ProdutoVenda getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final ProdutoVenda getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected ProdutoVenda inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ProdutoVendaServicoSqliteImpl ");
	}
	protected ProdutoVenda inicializaItemTelaImpl(ProdutoVenda itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ProdutoVendaServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(ProdutoVenda itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ProdutoVendaServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(ProdutoVenda itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ProdutoVendaServicoSqliteImpl ");
	}
	
	public ProdutoVenda ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		ProdutoVendaDBHelper dao = getDao();
		List<ProdutoVenda> listaItem = lista;
		for (ProdutoVenda item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	
	
	public void atualizaRelacionamento(Venda item, List<DCIObjetoDominio> listaEscolhidos) {
		ProdutoVenda novo = FabricaVo.criaProdutoVenda();
		List<ProdutoVenda> listaBanco = this.getPorAssociadaVenda(null, item.getId());
		// lista insercao
		List<ProdutoVenda> listaInsercao = new ArrayList<ProdutoVenda>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ProdutoVenda rel : listaBanco) {
				if (obj.getId()==rel.getIdProdutoA()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ProdutoVenda novoRel = FabricaVo.criaProdutoVenda();
				novoRel.setVenda_Associada(item);
				novoRel.setProduto_Associada((Produto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ProdutoVenda> listaExclusao = new ArrayList<ProdutoVenda>();
		for (ProdutoVenda itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdProdutoA()) {
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
		ProdutoVendaDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			ProdutoVenda novoRel = (ProdutoVenda) obj;
			novoRel.setVenda_Associada(item);
			dao.insertSinc(novoRel);
		}	
		for (ProdutoVenda obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	
	public void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos) {
		ProdutoVenda novo = FabricaVo.criaProdutoVenda();
		List<ProdutoVenda> listaBanco = this.getPorAssociadaProduto(null, item.getId());
		// lista insercao
		List<ProdutoVenda> listaInsercao = new ArrayList<ProdutoVenda>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ProdutoVenda rel : listaBanco) {
				if (obj.getId()==rel.getIdVendaA()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ProdutoVenda novoRel = FabricaVo.criaProdutoVenda();
				novoRel.setProduto_Associada(item);
				novoRel.setVenda_Associada((Venda)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ProdutoVenda> listaExclusao = new ArrayList<ProdutoVenda>();
		for (ProdutoVenda itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdVendaA()) {
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
		ProdutoVendaDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			ProdutoVenda novoRel = (ProdutoVenda) obj;
			novoRel.setProduto_Associada(item);
			dao.insertSinc(novoRel);
		}	
		for (ProdutoVenda obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		boolean saida = false;
		if (item instanceof Produto) {
			Produto obj = (Produto) item;
			ProdutoVenda rel = (ProdutoVenda) relacionamento;
			saida = rel.getIdProdutoA()== obj.getId();
		}
		if (item instanceof Venda) {
			Venda obj = (Venda) item;
			ProdutoVenda rel = (ProdutoVenda) relacionamento;
			saida = rel.getIdVendaA()== obj.getId();
		}
		return saida;
	}


	@Override
	public ProdutoVenda atribuiUsuario(ProdutoVenda item) {
		
		return item;
	}
}