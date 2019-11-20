
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
import coletapreco.dao.LojaNaturezaDBHelper;
import coletapreco.servico.LojaNaturezaServico;

public abstract class LojaNaturezaServicoSqliteBase extends  LojaNaturezaServico 
	implements ServicoLocal<LojaNatureza>, WifiServicoI{


	@Override
	public List<LojaNatureza> listaSincronizadaAlteracaoV2(Context contexto) {
		throw new RuntimeException();
	}


	@Override
	public void insertLocal(LojaNatureza item) {
		LojaNaturezaDBHelper dao = getDao();
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
	
	


	private LojaNaturezaDBHelper dao = null; 
	protected LojaNaturezaDBHelper getDao() {
		if (dao==null) {
			dao = new LojaNaturezaDBHelper();
		}
		return dao;
	}
	
	@Override
	public List<LojaNatureza> listaSincronizadaAlteracao(Context contexto) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getAllSinc();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<LojaNatureza> listaSincronizadaDependenteSoAlteracao(Context contexto) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getAllSincSoAlteracao();
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "listaSincronizadaDependenteSoAlteracao : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	
	
	




	public List<LojaNatureza> getAll(Context contexto) {
		List<LojaNatureza> saida = getDao().getAll();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAll : " +  saida.size() + " itens" );
		//dao.cleanup(); ??? que isso ?
		return saida;
	}
	public List<LojaNatureza> getAllTela(Context contexto) {
		List<LojaNatureza> saida = getDao().getAllTela();
		DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getAllTela : " +  saida.size() + " itens" );
		return saida;
	}
	public void alteraParaSincronizacao(LojaNatureza item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "updateSinc:" + item.JSon());
		getDao().updateSinc(item);
	}
	public void insereParaSincronizacao(LojaNatureza item) {
		//DCLog.d(DCLog.DATABASE_ADM, this, "insertSinc:" + item.JSon());
		
		getDao().insertSinc(item);
	}
	public void insertAll(List<LojaNatureza> lista, Context contexto) {
		LojaNaturezaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			dao.insert(lista.get(i));
		}
		//dao.cleanup();
	}	
	public void insertSincAll(List<LojaNatureza> lista, Context contexto) {
		LojaNaturezaDBHelper dao = getDao();
		for (int i=0;i<lista.size();i++) {
			getDao().insertSinc(lista.get(i));
		}
		//dao.cleanup();
	}
	public void criaTabelaSincronizacao() {
		LojaNaturezaDBHelper dao = getDao();
		dao.criaTabelaSincronizacao();
	}
	
	public void CriaSeNaoExiste(Context contexto) {
		LojaNaturezaDBHelper dao = getDao();
		dao.criaTabela();
		dao.criaTabelaSincronizacao();
	}	
	public void dropCreate(Context contexto) {
		LojaNaturezaDBHelper dao = getDao();
		dao.dropTable();
		dao.criaTabela();
		if (dao.tabelaSincVazia()) {
			dao.dropTableSincronizacao();
			dao.criaTabelaSincronizacao();
		}
		//dao.cleanup();
	}
		
	
	@Override
	public List<LojaNatureza> getPorReferenteALojaVirtual(Context contexto, long id) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getPorReferenteALojaVirtual(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteALojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<LojaNatureza> getPorReferenteALojaVirtual(Context contexto, long id, int qtde) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getPorReferenteALojaVirtual(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteALojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<LojaNatureza> getPorReferenteALojaVirtual(long id) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getPorReferenteALojaVirtual(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteALojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<LojaNatureza> getPorReferenteALojaVirtual(long id, int qtde) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getPorReferenteALojaVirtual(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteALojaVirtual : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	
	@Override
	public List<LojaNatureza> getPorReferenteANaturezaProduto(Context contexto, long id) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getPorReferenteANaturezaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<LojaNatureza> getPorReferenteANaturezaProduto(Context contexto, long id, int qtde) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getPorReferenteANaturezaProduto(contexto, id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	@Override
	public List<LojaNatureza> getPorReferenteANaturezaProduto(long id) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getPorReferenteANaturezaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteANaturezaProduto : " +  saida.size() + " itens" );
		} catch (DaoException e) {
			DCLog.e(DCLog.DATABASE_ERRO, this, e);
		}
		return saida;
	}
	public List<LojaNatureza> getPorReferenteANaturezaProduto(long id, int qtde) {
		List<LojaNatureza> saida = null; 
		try {
			saida = getDao().getPorReferenteANaturezaProduto(id);
			DCLog.dStack(DCLog.SERVICO_OPERACAO_PADRAO, this, "getPorReferenteANaturezaProduto : " +  saida.size() + " itens" );
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
	private LojaNatureza ultimoInicializado = null;
	public final LojaNatureza inicializaItemTela(DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(contexto);
		return ultimoInicializado;
	}
	public final LojaNatureza inicializaItemTela(LojaNatureza itemTela, DigicomContexto contexto) {
		ultimoInicializado = inicializaItemTelaImpl(itemTela, contexto);
		return ultimoInicializado;
	}
	public final void finalizaItemTela(LojaNatureza itemTela, boolean insercao, DigicomContexto contexto){
		
		finalizaItemTelaImpl(itemTela, insercao, contexto);
	}
	public final void processaItemTela(LojaNatureza itemTela, DigicomContexto contexto){
		processaItemTelaImpl(itemTela, contexto);
	}
	
	public final LojaNatureza getById(long id, Context contexto) {
		return getDao().getById(id);
	}
	public final LojaNatureza getById(long id) {
		return getDao().getById(id);
	}
	
	@Deprecated
	protected LojaNatureza inicializaItemTelaImpl(DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em LojaNaturezaServicoSqliteImpl ");
	}
	protected LojaNatureza inicializaItemTelaImpl(LojaNatureza itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException("Fazer override de inicializaItemTelaImpl em LojaNaturezaServicoSqliteImpl ");
	}
	protected void finalizaItemTelaImpl(LojaNatureza itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de finalizaItemTelaImpl em LojaNaturezaServicoSqliteImpl ");
	}
	protected void processaItemTelaImpl(LojaNatureza itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException("Fazer override de processaItemTelaImpl em LojaNaturezaServicoSqliteImpl ");
	}
	
	public LojaNatureza ultimoInicializado(){
		return ultimoInicializado;
	}
	
	// Operacoes de Servico
	
	@Override
	public void limpaSinc(List lista) {
		LojaNaturezaDBHelper dao = getDao();
		List<LojaNatureza> listaItem = lista;
		for (LojaNatureza item : listaItem) {
			dao.limpaSinc(item);
		}
	}
	
	
	
	
	public void atualizaRelacionamento(NaturezaProduto item, List<DCIObjetoDominio> listaEscolhidos) {
		LojaNatureza novo = FabricaVo.criaLojaNatureza();
		List<LojaNatureza> listaBanco = this.getPorReferenteANaturezaProduto(null, item.getId());
		// lista insercao
		List<LojaNatureza> listaInsercao = new ArrayList<LojaNatureza>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (LojaNatureza rel : listaBanco) {
				if (obj.getId()==rel.getIdLojaVirtualRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				LojaNatureza novoRel = FabricaVo.criaLojaNatureza();
				novoRel.setNaturezaProduto_ReferenteA(item);
				novoRel.setLojaVirtual_ReferenteA((LojaVirtual)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<LojaNatureza> listaExclusao = new ArrayList<LojaNatureza>();
		for (LojaNatureza itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdLojaVirtualRa()) {
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
		LojaNaturezaDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			LojaNatureza novoRel = (LojaNatureza) obj;
			novoRel.setNaturezaProduto_ReferenteA(item);
			dao.insertSinc(novoRel);
		}	
		for (LojaNatureza obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	
	public void atualizaRelacionamento(LojaVirtual item, List<DCIObjetoDominio> listaEscolhidos) {
		LojaNatureza novo = FabricaVo.criaLojaNatureza();
		List<LojaNatureza> listaBanco = this.getPorReferenteALojaVirtual(null, item.getId());
		// lista insercao
		List<LojaNatureza> listaInsercao = new ArrayList<LojaNatureza>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (LojaNatureza rel : listaBanco) {
				if (obj.getId()==rel.getIdNaturezaProdutoRa()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				LojaNatureza novoRel = FabricaVo.criaLojaNatureza();
				novoRel.setLojaVirtual_ReferenteA(item);
				novoRel.setNaturezaProduto_ReferenteA((NaturezaProduto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<LojaNatureza> listaExclusao = new ArrayList<LojaNatureza>();
		for (LojaNatureza itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdNaturezaProdutoRa()) {
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
		LojaNaturezaDBHelper dao = getDao();
		for (DCIObjetoDominio obj : listaInsercao) {
			LojaNatureza novoRel = (LojaNatureza) obj;
			novoRel.setLojaVirtual_ReferenteA(item);
			dao.insertSinc(novoRel);
		}	
		for (LojaNatureza obj : listaExclusao) {
			dao.deleteSinc(obj);
		}	
	}
	
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		boolean saida = false;
		if (item instanceof LojaVirtual) {
			LojaVirtual obj = (LojaVirtual) item;
			LojaNatureza rel = (LojaNatureza) relacionamento;
			saida = rel.getIdLojaVirtualRa()== obj.getId();
		}
		if (item instanceof NaturezaProduto) {
			NaturezaProduto obj = (NaturezaProduto) item;
			LojaNatureza rel = (LojaNatureza) relacionamento;
			saida = rel.getIdNaturezaProdutoRa()== obj.getId();
		}
		return saida;
	}


	@Override
	public LojaNatureza atribuiUsuario(LojaNatureza item) {
		
		return item;
	}
}