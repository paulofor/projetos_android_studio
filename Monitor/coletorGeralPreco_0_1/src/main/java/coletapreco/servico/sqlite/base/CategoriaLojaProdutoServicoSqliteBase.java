
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
import coletapreco.dao.CategoriaLojaProdutoDBHelper;
import coletapreco.servico.CategoriaLojaProdutoServico;

public abstract class CategoriaLojaProdutoServicoSqliteBase extends  CategoriaLojaProdutoServico 
	implements ServicoLocal<CategoriaLojaProduto>, WifiServicoI{


	@Override
	public List<CategoriaLojaProduto> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(CategoriaLojaProduto item) {
		CategoriaLojaProdutoDBHelper dao = getDao();
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
	
	


	private CategoriaLojaProdutoDBHelper dao = null; 
	protected CategoriaLojaProdutoDBHelper getDao() {
		if (dao==null) {
			dao = new CategoriaLojaProdutoDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<CategoriaLojaProduto> listaSincronizadaAlteracao(Context contexto) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaLojaProduto> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<CategoriaLojaProduto> getAll(Context contexto) {
		List<CategoriaLojaProduto> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<CategoriaLojaProduto> getAllTela(Context contexto) {
		List<CategoriaLojaProduto> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(CategoriaLojaProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(CategoriaLojaProduto item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<CategoriaLojaProduto> lista, Context contexto) {
		CategoriaLojaProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<CategoriaLojaProduto> lista, Context contexto) {
		CategoriaLojaProdutoDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		CategoriaLojaProdutoDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		CategoriaLojaProdutoDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		CategoriaLojaProdutoDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(Context contexto, long id) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteACategoriaLoja(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteACategoriaLoja : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(Context contexto, long id, int qtde) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteACategoriaLoja(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteACategoriaLoja : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(long id) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteACategoriaLoja(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteACategoriaLoja : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(long id, int qtde) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteACategoriaLoja(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteACategoriaLoja : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<CategoriaLojaProduto> getPorReferenteAProduto(Context contexto, long id) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLojaProduto> getPorReferenteAProduto(Context contexto, long id, int qtde) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<CategoriaLojaProduto> getPorReferenteAProduto(long id) {
		List<CategoriaLojaProduto> saida = null; 
		try {
			saida = getDao().getPorReferenteAProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteAProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<CategoriaLojaProduto> getPorReferenteAProduto(long id, int qtde) {
		List<CategoriaLojaProduto> saida = null; 
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
	private CategoriaLojaProduto ultimoInicializado = null;
	public final CategoriaLojaProduto inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final CategoriaLojaProduto inicializaItemTela(CategoriaLojaProduto itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(CategoriaLojaProduto itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(CategoriaLojaProduto itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final CategoriaLojaProduto getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final CategoriaLojaProduto getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected CategoriaLojaProduto inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CategoriaLojaProdutoServicoSqliteImpl ");
	}
	protected CategoriaLojaProduto inicializaItemTelaImpl(CategoriaLojaProduto itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em CategoriaLojaProdutoServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(CategoriaLojaProduto itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em CategoriaLojaProdutoServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(CategoriaLojaProduto itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em CategoriaLojaProdutoServicoSqliteImpl ");
	}
	
	public CategoriaLojaProduto ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		CategoriaLojaProdutoDBHelper dao = getDao();
		List<CategoriaLojaProduto> listaItem = lista;
		for (CategoriaLojaProduto item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	
	
	public void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos) {
		CategoriaLojaProduto novo = FabricaVo.criaCategoriaLojaProduto();
		List<CategoriaLojaProduto> listaBanco = this.getPorReferenteAProduto(null, item.getId());
		// lista insercao
		List<CategoriaLojaProduto> listaInsercao = new ArrayList<CategoriaLojaProduto>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (CategoriaLojaProduto rel : listaBanco) {
				if (obj.getId()==rel.getIdCategoriaLojaRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				CategoriaLojaProduto novoRel = FabricaVo.criaCategoriaLojaProduto();
				novoRel.setProduto_ReferenteA(item);
				novoRel.setCategoriaLoja_ReferenteA((CategoriaLoja)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<CategoriaLojaProduto> listaExclusao = new ArrayList<CategoriaLojaProduto>();
		for (CategoriaLojaProduto itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdCategoriaLojaRa()) {
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
		CategoriaLojaProdutoDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			CategoriaLojaProduto novoRel = (CategoriaLojaProduto) obj;
			novoRel.setProduto_ReferenteA(item);
			dao.insertSinc(novoRel);
		}	
		for (CategoriaLojaProduto obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	
	public void atualizaRelacionamento(CategoriaLoja item, List<DCIObjetoDominio> listaEscolhidos) {
		CategoriaLojaProduto novo = FabricaVo.criaCategoriaLojaProduto();
		List<CategoriaLojaProduto> listaBanco = this.getPorReferenteACategoriaLoja(null, item.getId());
		// lista insercao
		List<CategoriaLojaProduto> listaInsercao = new ArrayList<CategoriaLojaProduto>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (CategoriaLojaProduto rel : listaBanco) {
				if (obj.getId()==rel.getIdProdutoRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				CategoriaLojaProduto novoRel = FabricaVo.criaCategoriaLojaProduto();
				novoRel.setCategoriaLoja_ReferenteA(item);
				novoRel.setProduto_ReferenteA((Produto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<CategoriaLojaProduto> listaExclusao = new ArrayList<CategoriaLojaProduto>();
		for (CategoriaLojaProduto itemBanco : listaBanco) {
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
		CategoriaLojaProdutoDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			CategoriaLojaProduto novoRel = (CategoriaLojaProduto) obj;
			novoRel.setCategoriaLoja_ReferenteA(item);
			dao.insertSinc(novoRel);
		}	
		for (CategoriaLojaProduto obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		boolean saida = false;
		if (item instanceof CategoriaLoja) {
			CategoriaLoja obj = (CategoriaLoja) item;
			CategoriaLojaProduto rel = (CategoriaLojaProduto) relacionamento;
			saida = rel.getIdCategoriaLojaRa()== obj.getId();
		}
		if (item instanceof Produto) {
			Produto obj = (Produto) item;
			CategoriaLojaProduto rel = (CategoriaLojaProduto) relacionamento;
			saida = rel.getIdProdutoRa()== obj.getId();
		}
		return saida;
	}


	@Override
	public CategoriaLojaProduto atribuiUsuario(CategoriaLojaProduto item) {
		
		return item;
	}
}