
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
import repcom.dao.CategoriaProdutoProdutoDBHelper;
import repcom.servico.CategoriaProdutoProdutoServico;

public abstract class CategoriaProdutoProdutoServicoSqliteBase extends  CategoriaProdutoProdutoServico 
	implements ServicoLocal<CategoriaProdutoProduto>, WifiServicoI{


	@Override
	public List<CategoriaProdutoProduto> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(CategoriaProdutoProduto item) {
		CategoriaProdutoProdutoDBHelper dao = getDao();
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
	
	


	private CategoriaProdutoProdutoDBHelper dao = null; 
	protected CategoriaProdutoProdutoDBHelper getDao() {
		if (dao==null) {
			dao = new CategoriaProdutoProdutoDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<CategoriaProdutoProduto> listaSincronizadaAlteracao(Context contexto) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaProdutoProduto> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<CategoriaProdutoProduto> getAll(Context contexto) {
		List<CategoriaProdutoProduto> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<CategoriaProdutoProduto> getAllTela(Context contexto) {
		List<CategoriaProdutoProduto> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(CategoriaProdutoProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(CategoriaProdutoProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<CategoriaProdutoProduto> lista, Context contexto) {
		CategoriaProdutoProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<CategoriaProdutoProduto> lista, Context contexto) {
		CategoriaProdutoProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		CategoriaProdutoProdutoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		CategoriaProdutoProdutoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		CategoriaProdutoProdutoDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(Context contexto, long id) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteACategoriaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteACategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(Context contexto, long id, int qtde) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteACategoriaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteACategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(long id) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteACategoriaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteACategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaProdutoProduto> getPorReferenteACategoriaProduto(long id, int qtde) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteACategoriaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteACategoriaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<CategoriaProdutoProduto> getPorReferenteAProduto(Context contexto, long id) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaProdutoProduto> getPorReferenteAProduto(Context contexto, long id, int qtde) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaProdutoProduto> getPorReferenteAProduto(long id) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaProdutoProduto> getPorReferenteAProduto(long id, int qtde) {
		List<CategoriaProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
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
	private CategoriaProdutoProduto ultimoInicializado = null;
	public final CategoriaProdutoProduto inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final CategoriaProdutoProduto inicializaItemTela(CategoriaProdutoProduto itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(CategoriaProdutoProduto itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(CategoriaProdutoProduto itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final CategoriaProdutoProduto getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final CategoriaProdutoProduto getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected CategoriaProdutoProduto inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CategoriaProdutoProdutoServicoSqliteImpl ");
	}
	protected CategoriaProdutoProduto inicializaItemTelaImpl(CategoriaProdutoProduto itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CategoriaProdutoProdutoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(CategoriaProdutoProduto itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em CategoriaProdutoProdutoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(CategoriaProdutoProduto itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em CategoriaProdutoProdutoServicoSqliteImpl ");
	}
	
	public CategoriaProdutoProduto ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		CategoriaProdutoProdutoDBHelper dao = getDao();
		List<CategoriaProdutoProduto> listaItem = lista;
		for (CategoriaProdutoProduto item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	
	
	public void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos) {
		CategoriaProdutoProduto novo = FabricaVo.criaCategoriaProdutoProduto();
		List<CategoriaProdutoProduto> listaBanco = this.getPorReferenteAProduto(null, item.getId());
		// lista insercao
		List<CategoriaProdutoProduto> listaInsercao = new ArrayList<CategoriaProdutoProduto>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (CategoriaProdutoProduto rel : listaBanco) {
				if (obj.getId()==rel.getIdCategoriaProdutoRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				CategoriaProdutoProduto novoRel = FabricaVo.criaCategoriaProdutoProduto();
				novoRel.setProduto_ReferenteA(item);
				novoRel.setCategoriaProduto_ReferenteA((CategoriaProduto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<CategoriaProdutoProduto> listaExclusao = new ArrayList<CategoriaProdutoProduto>();
		for (CategoriaProdutoProduto itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdCategoriaProdutoRa()) {
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
		CategoriaProdutoProdutoDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			CategoriaProdutoProduto novoRel = (CategoriaProdutoProduto) obj;
			novoRel.setProduto_ReferenteA(item);
			dao.insertSinc(novoRel);
		}	
		for (CategoriaProdutoProduto obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	
	public void atualizaRelacionamento(CategoriaProduto item, List<DCIObjetoDominio> listaEscolhidos) {
		CategoriaProdutoProduto novo = FabricaVo.criaCategoriaProdutoProduto();
		List<CategoriaProdutoProduto> listaBanco = this.getPorReferenteACategoriaProduto(null, item.getId());
		// lista insercao
		List<CategoriaProdutoProduto> listaInsercao = new ArrayList<CategoriaProdutoProduto>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (CategoriaProdutoProduto rel : listaBanco) {
				if (obj.getId()==rel.getIdProdutoRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				CategoriaProdutoProduto novoRel = FabricaVo.criaCategoriaProdutoProduto();
				novoRel.setCategoriaProduto_ReferenteA(item);
				novoRel.setProduto_ReferenteA((Produto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<CategoriaProdutoProduto> listaExclusao = new ArrayList<CategoriaProdutoProduto>();
		for (CategoriaProdutoProduto itemBanco : listaBanco) {
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
		CategoriaProdutoProdutoDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			CategoriaProdutoProduto novoRel = (CategoriaProdutoProduto) obj;
			novoRel.setCategoriaProduto_ReferenteA(item);
			dao.insertSinc(novoRel);
		}	
		for (CategoriaProdutoProduto obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		boolean saida = false;
		if (item instanceof CategoriaProduto) {
			CategoriaProduto obj = (CategoriaProduto) item;
			CategoriaProdutoProduto rel = (CategoriaProdutoProduto) relacionamento;
			saida = rel.getIdCategoriaProdutoRa()== obj.getId();
		}
		if (item instanceof Produto) {
			Produto obj = (Produto) item;
			CategoriaProdutoProduto rel = (CategoriaProdutoProduto) relacionamento;
			saida = rel.getIdProdutoRa()== obj.getId();
		}
		return saida;
	}


	@Override
	public CategoriaProdutoProduto atribuiUsuario(CategoriaProdutoProduto item) {
		
		return item;
	}
}