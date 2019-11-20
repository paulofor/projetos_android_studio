
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
import coletapreco.dao.ModeloProdutoProdutoDBHelper;
import coletapreco.servico.ModeloProdutoProdutoServico;

public abstract class ModeloProdutoProdutoServicoSqliteBase extends  ModeloProdutoProdutoServico 
	implements ServicoLocal<ModeloProdutoProduto>, WifiServicoI{


	@Override
	public List<ModeloProdutoProduto> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(ModeloProdutoProduto item) {
		ModeloProdutoProdutoDBHelper dao = getDao();
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
	
	


	private ModeloProdutoProdutoDBHelper dao = null; 
	protected ModeloProdutoProdutoDBHelper getDao() {
		if (dao==null) {
			dao = new ModeloProdutoProdutoDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<ModeloProdutoProduto> listaSincronizadaAlteracao(Context contexto) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ModeloProdutoProduto> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<ModeloProdutoProduto> getAll(Context contexto) {
		List<ModeloProdutoProduto> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<ModeloProdutoProduto> getAllTela(Context contexto) {
		List<ModeloProdutoProduto> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(ModeloProdutoProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(ModeloProdutoProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<ModeloProdutoProduto> lista, Context contexto) {
		ModeloProdutoProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<ModeloProdutoProduto> lista, Context contexto) {
		ModeloProdutoProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		ModeloProdutoProdutoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		ModeloProdutoProdutoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		ModeloProdutoProdutoDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<ModeloProdutoProduto> getPorReferenteAModeloProduto(Context contexto, long id) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAModeloProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAModeloProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ModeloProdutoProduto> getPorReferenteAModeloProduto(Context contexto, long id, int qtde) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAModeloProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAModeloProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ModeloProdutoProduto> getPorReferenteAModeloProduto(long id) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAModeloProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAModeloProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ModeloProdutoProduto> getPorReferenteAModeloProduto(long id, int qtde) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAModeloProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAModeloProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<ModeloProdutoProduto> getPorReferenteAProduto(Context contexto, long id) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ModeloProdutoProduto> getPorReferenteAProduto(Context contexto, long id, int qtde) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<ModeloProdutoProduto> getPorReferenteAProduto(long id) {
		List<ModeloProdutoProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<ModeloProdutoProduto> getPorReferenteAProduto(long id, int qtde) {
		List<ModeloProdutoProduto> saida = null; 
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
	private ModeloProdutoProduto ultimoInicializado = null;
	public final ModeloProdutoProduto inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final ModeloProdutoProduto inicializaItemTela(ModeloProdutoProduto itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(ModeloProdutoProduto itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(ModeloProdutoProduto itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final ModeloProdutoProduto getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final ModeloProdutoProduto getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected ModeloProdutoProduto inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ModeloProdutoProdutoServicoSqliteImpl ");
	}
	protected ModeloProdutoProduto inicializaItemTelaImpl(ModeloProdutoProduto itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em ModeloProdutoProdutoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(ModeloProdutoProduto itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em ModeloProdutoProdutoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(ModeloProdutoProduto itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em ModeloProdutoProdutoServicoSqliteImpl ");
	}
	
	public ModeloProdutoProduto ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		ModeloProdutoProdutoDBHelper dao = getDao();
		List<ModeloProdutoProduto> listaItem = lista;
		for (ModeloProdutoProduto item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	
	
	public void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos) {
		ModeloProdutoProduto novo = FabricaVo.criaModeloProdutoProduto();
		List<ModeloProdutoProduto> listaBanco = this.getPorReferenteAProduto(null, item.getId());
		// lista insercao
		List<ModeloProdutoProduto> listaInsercao = new ArrayList<ModeloProdutoProduto>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ModeloProdutoProduto rel : listaBanco) {
				if (obj.getId()==rel.getIdModeloProdutoRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ModeloProdutoProduto novoRel = FabricaVo.criaModeloProdutoProduto();
				novoRel.setProduto_ReferenteA(item);
				novoRel.setModeloProduto_ReferenteA((ModeloProduto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ModeloProdutoProduto> listaExclusao = new ArrayList<ModeloProdutoProduto>();
		for (ModeloProdutoProduto itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdModeloProdutoRa()) {
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
		ModeloProdutoProdutoDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			ModeloProdutoProduto novoRel = (ModeloProdutoProduto) obj;
			novoRel.setProduto_ReferenteA(item);
			dao.insertSinc(novoRel);
		}	
		for (ModeloProdutoProduto obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	
	public void atualizaRelacionamento(ModeloProduto item, List<DCIObjetoDominio> listaEscolhidos) {
		ModeloProdutoProduto novo = FabricaVo.criaModeloProdutoProduto();
		List<ModeloProdutoProduto> listaBanco = this.getPorReferenteAModeloProduto(null, item.getId());
		// lista insercao
		List<ModeloProdutoProduto> listaInsercao = new ArrayList<ModeloProdutoProduto>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ModeloProdutoProduto rel : listaBanco) {
				if (obj.getId()==rel.getIdProdutoRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ModeloProdutoProduto novoRel = FabricaVo.criaModeloProdutoProduto();
				novoRel.setModeloProduto_ReferenteA(item);
				novoRel.setProduto_ReferenteA((Produto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ModeloProdutoProduto> listaExclusao = new ArrayList<ModeloProdutoProduto>();
		for (ModeloProdutoProduto itemBanco : listaBanco) {
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
		ModeloProdutoProdutoDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			ModeloProdutoProduto novoRel = (ModeloProdutoProduto) obj;
			novoRel.setModeloProduto_ReferenteA(item);
			dao.insertSinc(novoRel);
		}	
		for (ModeloProdutoProduto obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		boolean saida = false;
		if (item instanceof ModeloProduto) {
			ModeloProduto obj = (ModeloProduto) item;
			ModeloProdutoProduto rel = (ModeloProdutoProduto) relacionamento;
			saida = rel.getIdModeloProdutoRa()== obj.getId();
		}
		if (item instanceof Produto) {
			Produto obj = (Produto) item;
			ModeloProdutoProduto rel = (ModeloProdutoProduto) relacionamento;
			saida = rel.getIdProdutoRa()== obj.getId();
		}
		return saida;
	}


	@Override
	public ModeloProdutoProduto atribuiUsuario(ModeloProdutoProduto item) {
		
		return item;
	}
}