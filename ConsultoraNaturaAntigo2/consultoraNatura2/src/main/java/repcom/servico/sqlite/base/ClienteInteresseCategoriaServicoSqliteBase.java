
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
import repcom.dao.ClienteInteresseCategoriaDBHelper;
import repcom.servico.ClienteInteresseCategoriaServico;

public abstract class ClienteInteresseCategoriaServicoSqliteBase extends  ClienteInteresseCategoriaServico 
	implements ServicoLocal<ClienteInteresseCategoria>, WifiServicoI{


	@Override
	public List<ClienteInteresseCategoria> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(ClienteInteresseCategoria item) {
		ClienteInteresseCategoriaDBHelper dao = getDao();
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
	
	


	private ClienteInteresseCategoriaDBHelper dao = null; 
	protected ClienteInteresseCategoriaDBHelper getDao() {
		if (dao==null) {
			dao = new ClienteInteresseCategoriaDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<ClienteInteresseCategoria> listaSincronizadaAlteracao(Context contexto) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ClienteInteresseCategoria> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<ClienteInteresseCategoria> getAll(Context contexto) {
		List<ClienteInteresseCategoria> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<ClienteInteresseCategoria> getAllTela(Context contexto) {
		List<ClienteInteresseCategoria> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(ClienteInteresseCategoria item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(ClienteInteresseCategoria item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<ClienteInteresseCategoria> lista, Context contexto) {
		ClienteInteresseCategoriaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<ClienteInteresseCategoria> lista, Context contexto) {
		ClienteInteresseCategoriaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ClienteInteresseCategoriaDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ClienteInteresseCategoriaDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ClienteInteresseCategoriaDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<ClienteInteresseCategoria> getPorAssociadaCliente(Context contexto, long id) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getPorAssociadaCliente(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaCliente : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ClienteInteresseCategoria> getPorAssociadaCliente(Context contexto, long id, int qtde) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getPorAssociadaCliente(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaCliente : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ClienteInteresseCategoria> getPorAssociadaCliente(long id) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getPorAssociadaCliente(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaCliente : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ClienteInteresseCategoria> getPorAssociadaCliente(long id, int qtde) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getPorAssociadaCliente(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaCliente : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(Context contexto, long id) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getPorAssociadaCategoriaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaCategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(Context contexto, long id, int qtde) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getPorAssociadaCategoriaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaCategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(long id) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getPorAssociadaCategoriaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaCategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ClienteInteresseCategoria> getPorAssociadaCategoriaProduto(long id, int qtde) {
		List<ClienteInteresseCategoria> saida = null; 
		try {
			saida = getDao().getPorAssociadaCategoriaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorAssociadaCategoriaProduto : " +  saida.size() + " itens" );
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
	private ClienteInteresseCategoria ultimoInicializado = null;
	public final ClienteInteresseCategoria inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final ClienteInteresseCategoria inicializaItemTela(ClienteInteresseCategoria itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(ClienteInteresseCategoria itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(ClienteInteresseCategoria itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final ClienteInteresseCategoria getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final ClienteInteresseCategoria getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected ClienteInteresseCategoria inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ClienteInteresseCategoriaServicoSqliteImpl ");
	}
	protected ClienteInteresseCategoria inicializaItemTelaImpl(ClienteInteresseCategoria itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ClienteInteresseCategoriaServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(ClienteInteresseCategoria itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ClienteInteresseCategoriaServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(ClienteInteresseCategoria itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ClienteInteresseCategoriaServicoSqliteImpl ");
	}
	
	public ClienteInteresseCategoria ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		ClienteInteresseCategoriaDBHelper dao = getDao();
		List<ClienteInteresseCategoria> listaItem = lista;
		for (ClienteInteresseCategoria item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	
	
	public void atualizaRelacionamento(CategoriaProduto item, List<DCIObjetoDominio> listaEscolhidos) {
		ClienteInteresseCategoria novo = FabricaVo.criaClienteInteresseCategoria();
		List<ClienteInteresseCategoria> listaBanco = this.getPorAssociadaCategoriaProduto(null, item.getId());
		// lista insercao
		List<ClienteInteresseCategoria> listaInsercao = new ArrayList<ClienteInteresseCategoria>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ClienteInteresseCategoria rel : listaBanco) {
				if (obj.getId()==rel.getIdClienteA()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ClienteInteresseCategoria novoRel = FabricaVo.criaClienteInteresseCategoria();
				novoRel.setCategoriaProduto_Associada(item);
				novoRel.setCliente_Associada((Cliente)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ClienteInteresseCategoria> listaExclusao = new ArrayList<ClienteInteresseCategoria>();
		for (ClienteInteresseCategoria itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdClienteA()) {
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
		ClienteInteresseCategoriaDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			ClienteInteresseCategoria novoRel = (ClienteInteresseCategoria) obj;
			novoRel.setCategoriaProduto_Associada(item);
			dao.insertSinc(novoRel);
		}	
		for (ClienteInteresseCategoria obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	
	public void atualizaRelacionamento(Cliente item, List<DCIObjetoDominio> listaEscolhidos) {
		ClienteInteresseCategoria novo = FabricaVo.criaClienteInteresseCategoria();
		List<ClienteInteresseCategoria> listaBanco = this.getPorAssociadaCliente(null, item.getId());
		// lista insercao
		List<ClienteInteresseCategoria> listaInsercao = new ArrayList<ClienteInteresseCategoria>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ClienteInteresseCategoria rel : listaBanco) {
				if (obj.getId()==rel.getIdCategoriaProdutoA()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ClienteInteresseCategoria novoRel = FabricaVo.criaClienteInteresseCategoria();
				novoRel.setCliente_Associada(item);
				novoRel.setCategoriaProduto_Associada((CategoriaProduto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ClienteInteresseCategoria> listaExclusao = new ArrayList<ClienteInteresseCategoria>();
		for (ClienteInteresseCategoria itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdCategoriaProdutoA()) {
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
		ClienteInteresseCategoriaDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			ClienteInteresseCategoria novoRel = (ClienteInteresseCategoria) obj;
			novoRel.setCliente_Associada(item);
			dao.insertSinc(novoRel);
		}	
		for (ClienteInteresseCategoria obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		boolean saida = false;
		if (item instanceof Cliente) {
			Cliente obj = (Cliente) item;
			ClienteInteresseCategoria rel = (ClienteInteresseCategoria) relacionamento;
			saida = rel.getIdClienteA()== obj.getId();
		}
		if (item instanceof CategoriaProduto) {
			CategoriaProduto obj = (CategoriaProduto) item;
			ClienteInteresseCategoria rel = (ClienteInteresseCategoria) relacionamento;
			saida = rel.getIdCategoriaProdutoA()== obj.getId();
		}
		return saida;
	}


	@Override
	public ClienteInteresseCategoria atribuiUsuario(ClienteInteresseCategoria item) {
		
		return item;
	}
}