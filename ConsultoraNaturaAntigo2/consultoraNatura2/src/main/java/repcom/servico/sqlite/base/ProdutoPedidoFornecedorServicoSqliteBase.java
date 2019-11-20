
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
import repcom.dao.ProdutoPedidoFornecedorDBHelper;
import repcom.servico.ProdutoPedidoFornecedorServico;

public abstract class ProdutoPedidoFornecedorServicoSqliteBase extends  ProdutoPedidoFornecedorServico 
	implements ServicoLocal<ProdutoPedidoFornecedor>, WifiServicoI{


	@Override
	public List<ProdutoPedidoFornecedor> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(ProdutoPedidoFornecedor item) {
		ProdutoPedidoFornecedorDBHelper dao = getDao();
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
	
	


	private ProdutoPedidoFornecedorDBHelper dao = null; 
	protected ProdutoPedidoFornecedorDBHelper getDao() {
		if (dao==null) {
			dao = new ProdutoPedidoFornecedorDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<ProdutoPedidoFornecedor> listaSincronizadaAlteracao(Context contexto) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ProdutoPedidoFornecedor> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<ProdutoPedidoFornecedor> getAll(Context contexto) {
		List<ProdutoPedidoFornecedor> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<ProdutoPedidoFornecedor> getAllTela(Context contexto) {
		List<ProdutoPedidoFornecedor> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(ProdutoPedidoFornecedor item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(ProdutoPedidoFornecedor item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<ProdutoPedidoFornecedor> lista, Context contexto) {
		ProdutoPedidoFornecedorDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<ProdutoPedidoFornecedor> lista, Context contexto) {
		ProdutoPedidoFornecedorDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ProdutoPedidoFornecedorDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ProdutoPedidoFornecedorDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ProdutoPedidoFornecedorDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(Context contexto, long id) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getPorAssociadaPedidoFornecedor(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaPedidoFornecedor : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(Context contexto, long id, int qtde) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getPorAssociadaPedidoFornecedor(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaPedidoFornecedor : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(long id) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getPorAssociadaPedidoFornecedor(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaPedidoFornecedor : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ProdutoPedidoFornecedor> getPorAssociadaPedidoFornecedor(long id, int qtde) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getPorAssociadaPedidoFornecedor(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaPedidoFornecedor : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<ProdutoPedidoFornecedor> getPorAssociadaProduto(Context contexto, long id) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getPorAssociadaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ProdutoPedidoFornecedor> getPorAssociadaProduto(Context contexto, long id, int qtde) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getPorAssociadaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ProdutoPedidoFornecedor> getPorAssociadaProduto(long id) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getPorAssociadaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ProdutoPedidoFornecedor> getPorAssociadaProduto(long id, int qtde) {
		List<ProdutoPedidoFornecedor> saida = null; 
		try {
			saida = getDao().getPorAssociadaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaProduto : " +  saida.size() + " itens" );
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
	private ProdutoPedidoFornecedor ultimoInicializado = null;
	public final ProdutoPedidoFornecedor inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final ProdutoPedidoFornecedor inicializaItemTela(ProdutoPedidoFornecedor itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(ProdutoPedidoFornecedor itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(ProdutoPedidoFornecedor itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final ProdutoPedidoFornecedor getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final ProdutoPedidoFornecedor getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected ProdutoPedidoFornecedor inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ProdutoPedidoFornecedorServicoSqliteImpl ");
	}
	protected ProdutoPedidoFornecedor inicializaItemTelaImpl(ProdutoPedidoFornecedor itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ProdutoPedidoFornecedorServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(ProdutoPedidoFornecedor itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ProdutoPedidoFornecedorServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(ProdutoPedidoFornecedor itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ProdutoPedidoFornecedorServicoSqliteImpl ");
	}
	
	public ProdutoPedidoFornecedor ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		ProdutoPedidoFornecedorDBHelper dao = getDao();
		List<ProdutoPedidoFornecedor> listaItem = lista;
		for (ProdutoPedidoFornecedor item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	
	
	public void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos) {
		ProdutoPedidoFornecedor novo = FabricaVo.criaProdutoPedidoFornecedor();
		List<ProdutoPedidoFornecedor> listaBanco = this.getPorAssociadaProduto(null, item.getId());
		// lista insercao
		List<ProdutoPedidoFornecedor> listaInsercao = new ArrayList<ProdutoPedidoFornecedor>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ProdutoPedidoFornecedor rel : listaBanco) {
				if (obj.getId()==rel.getIdPedidoFornecedorA()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ProdutoPedidoFornecedor novoRel = FabricaVo.criaProdutoPedidoFornecedor();
				novoRel.setProduto_Associada(item);
				novoRel.setPedidoFornecedor_Associada((PedidoFornecedor)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ProdutoPedidoFornecedor> listaExclusao = new ArrayList<ProdutoPedidoFornecedor>();
		for (ProdutoPedidoFornecedor itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdPedidoFornecedorA()) {
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
		ProdutoPedidoFornecedorDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			ProdutoPedidoFornecedor novoRel = (ProdutoPedidoFornecedor) obj;
			novoRel.setProduto_Associada(item);
			dao.insertSinc(novoRel);
		}	
		for (ProdutoPedidoFornecedor obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	
	public void atualizaRelacionamento(PedidoFornecedor item, List<DCIObjetoDominio> listaEscolhidos) {
		ProdutoPedidoFornecedor novo = FabricaVo.criaProdutoPedidoFornecedor();
		List<ProdutoPedidoFornecedor> listaBanco = this.getPorAssociadaPedidoFornecedor(null, item.getId());
		// lista insercao
		List<ProdutoPedidoFornecedor> listaInsercao = new ArrayList<ProdutoPedidoFornecedor>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ProdutoPedidoFornecedor rel : listaBanco) {
				if (obj.getId()==rel.getIdProdutoA()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ProdutoPedidoFornecedor novoRel = FabricaVo.criaProdutoPedidoFornecedor();
				novoRel.setPedidoFornecedor_Associada(item);
				novoRel.setProduto_Associada((Produto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ProdutoPedidoFornecedor> listaExclusao = new ArrayList<ProdutoPedidoFornecedor>();
		for (ProdutoPedidoFornecedor itemBanco : listaBanco) {
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
		ProdutoPedidoFornecedorDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			ProdutoPedidoFornecedor novoRel = (ProdutoPedidoFornecedor) obj;
			novoRel.setPedidoFornecedor_Associada(item);
			dao.insertSinc(novoRel);
		}	
		for (ProdutoPedidoFornecedor obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		boolean saida = false;
		if (item instanceof PedidoFornecedor) {
			PedidoFornecedor obj = (PedidoFornecedor) item;
			ProdutoPedidoFornecedor rel = (ProdutoPedidoFornecedor) relacionamento;
			saida = rel.getIdPedidoFornecedorA()== obj.getId();
		}
		if (item instanceof Produto) {
			Produto obj = (Produto) item;
			ProdutoPedidoFornecedor rel = (ProdutoPedidoFornecedor) relacionamento;
			saida = rel.getIdProdutoA()== obj.getId();
		}
		return saida;
	}


	@Override
	public ProdutoPedidoFornecedor atribuiUsuario(ProdutoPedidoFornecedor item) {
		
		return item;
	}
}