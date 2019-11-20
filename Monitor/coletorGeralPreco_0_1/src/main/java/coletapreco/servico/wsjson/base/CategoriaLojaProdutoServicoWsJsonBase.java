

package coletapreco.servico.wsjson.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import coletapreco.dao.datasource.DataSourceRemoto;
import coletapreco.modelo.*;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.servico.CategoriaLojaProdutoServico;
import coletapreco.servico.FabricaServico;
import coletapreco.app.Constantes;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.modelo.ObjetoSinc;
import br.com.digicom.network.HTTPRequestHelper;
import br.com.digicom.servico.DataSourceRemotoI;
import br.com.digicom.servico.ServicoLocal;
import br.com.digicom.servico.ServicoRemoto;
import br.com.digicom.so.Dispositivo;
import br.com.digicom.telefonia.Telefone;
import br.com.digicom.dao.DaoSincronismo;


public abstract class CategoriaLojaProdutoServicoWsJsonBase 
		extends  CategoriaLojaProdutoServico 
		implements ServicoRemoto<CategoriaLojaProduto>{

	private List<CategoriaLojaProduto> listaSaida = null;
	private String ultimaUrl = null;
	private DataSourceRemotoI dataSource = null;
	
	//String server = "192.168.1.2";
	//String aplicacao = "CadVideoCli";
		
	public void setDataSource(DataSourceRemotoI dataSource) {
		this.dataSource = dataSource;
	}
	public CategoriaLojaProdutoServicoWsJsonBase() {
		setDataSource(new DataSourceRemoto());
	}
	
	
	@Override
	public void listaSincronizadaAlteracao(List<CategoriaLojaProduto> listaSinc, Context contexto) throws JSONException {
		Map<String,String> params = new HashMap<String,String>();
		String url = getUrl("ListaSincronizadaAlteracao");
		String saida = " [ ";
		boolean primeiro = true;
		for (CategoriaLojaProduto item:listaSinc) {
			if (primeiro) {
				primeiro = false;
			} else {
				saida += " , ";
			}
			saida += " " + ((ObjetoSinc)item).JSonSinc() + " ";
		}
		saida += " ] ";
		params.put("lista", saida);
		params.put("versao",String.valueOf(getVersaoSincronizacao()));
		if (!Dispositivo.isTablet(contexto)) {
			params.put("tmp",Telefone.getNumero(contexto));
		} else {
			params.put("cod",Dispositivo.getId(contexto));
		}	
		DCLog.d(DCLog.SINCRONIZACAO_JSON,this,"Enviando...lista<CategoriaLojaProduto>:" + saida);
		String resultado = HTTPRequestHelper.getHttpResponse(url,params);
	}
	
	
	private String getUrl(String metodo) {
		String objeto = "CategoriaLojaProduto";
		ultimaUrl = "http://" + dataSource.getServer() + "/" + (dataSource.getAplicacao()!=null?dataSource.getAplicacao() + "/":"") + objeto + "WS/" + metodo + getFiltro().getRequest();
		return ultimaUrl;
		//return "http://" + server + "/" + aplicacao + "/" + objeto + "/" + metodo + getFiltro().getRequest();
  	}
	
	
	private static final String URL_GET_POSTS_RECENT = 
		"http://www.lojadigicom.com.br/coletapreco/page/CategoriaLojaProdutoPage.aspx";
	private JSONObject json;
	
	/*
	private final Handler handler = new Handler() {
		public void handleMessage(final Message msg) {
			//progressDialog.dismiss();
			String bundleResult = msg.getData().getString("RESPONSE");
			//output.setText(bundleResult);
			listaSaida = new ArrayList<CategoriaLojaProduto>();
			try {
				json = new JSONObject(bundleResult);
				JSONArray lista = json.getJSONArray("CategoriaLojaProduto");
				int tam = lista.length();
				JSONObject item = null;
				String nome = null;
				for (int i=0;i<lista.length();i++) {
					item = (JSONObject) lista.get(i);
					listaSaida.add(new CategoriaLojaProduto(item));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	*/
	

	private int processaResultado(String resultado, DaoSincronismo daoSinc) {
		int tam = 0;
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		try {
			daoSinc.dropCreate();
			json = new JSONObject(resultado);
			JSONArray lista = json.getJSONArray("Lista");
			tam = lista.length();
			JSONObject item = null;
			CategoriaLojaProduto obj = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				obj = FabricaVo.criaCategoriaLojaProduto(item);
				daoSinc.insere(obj);
			}
			lista = null;
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		return tam;
	}

	@Deprecated
	private List<CategoriaLojaProduto> processaResultado(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		List<CategoriaLojaProduto> listaSaida = new LinkedList<CategoriaLojaProduto>();
		
		//ServicoLocal<CategoriaLojaProduto> servicoLocal = (ServicoLocal<CategoriaLojaProduto>) FabricaServico.getInstancia().getCategoriaLojaProdutoServico(FabricaServico.TIPO_SQLITE);
		//CategoriaLojaProduto corrente = null;
		
		try {
			json = new JSONObject(resultado);
			JSONArray lista = json.getJSONArray("Lista");
			int tam = lista.length();
			JSONObject item = null;
			String nome = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				//corrente = FabricaVo.criaCategoriaLojaProduto(item);
				//servicoLocal.insertLocal(corrente);
				listaSaida.add(FabricaVo.criaCategoriaLojaProduto(item));
			}
			lista = null;			
		} catch (Exception e) {
			//Log.e("CategoriaLojaProdutoServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		
		return listaSaida;	
	}

	private CategoriaLojaProduto processaResultadoObjeto(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		CategoriaLojaProduto saida = null;
		
		
		try {
			json = new JSONObject(resultado);
			JSONObject item = json.getJSONObject("Lista");
			String nome = null;
			saida = FabricaVo.criaCategoriaLojaProduto(item);
			
		} catch (Exception e) {
			//Log.e("CategoriaLojaProdutoServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		
		return saida;	
	}

	public void dropCreate(Context contexto) {
	}
	public void insertAll(List<CategoriaLojaProduto> lista, Context contexto) {
	}
	public void insertSincAll(List<CategoriaLojaProduto> lista, Context contexto) {
	}
	
	public final void alteraParaSincronizacao(CategoriaLojaProduto item) {
		throw new UnsupportedOperationException();
	}
	public final void insereParaSincronizacao(CategoriaLojaProduto item) {
		throw new UnsupportedOperationException();
	}
	public final void criaTabelaSincronizacao() {
	}
	
	
	// Itens Tela
	public final CategoriaLojaProduto inicializaItemTela(DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final CategoriaLojaProduto inicializaItemTela(CategoriaLojaProduto itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final void finalizaItemTela(CategoriaLojaProduto itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	public final void processaItemTela(CategoriaLojaProduto itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	
	public final CategoriaLojaProduto ultimoInicializado() {
		throw new UnsupportedOperationException();
	}
	
	public final CategoriaLojaProduto getById(long id, Context contexto) {
		throw new UnsupportedOperationException();
	}
	public final CategoriaLojaProduto getById(long id) {
		throw new UnsupportedOperationException();
	}
	
	public List<CategoriaLojaProduto> getAll(Context contexto) {
		String url = getUrl("ListaCorrente");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	public List<CategoriaLojaProduto> getAllTela(Context contexto) {
		return getAll(contexto);
	}
	
	/*
	* Essa nova vers?o da operacao de sincronizacao tem o objetivo de
    * reduzir passos colocando a lista vinda do remoto direto no sqlite
    * e uma opcao otimizada(Tatico) e o sincronizador gerencia(Estrategico) sua chamada.
	* Estrategico -> sincronizador
	* Tatico -> Json
	* Operacional -> http
	*/
	public int listaSincronizadaDao(DaoSincronismo dao, Context contexto) {
		int tam = 0;
		String url = getUrl("ListaSincronizada");
		if (!Dispositivo.isTablet(contexto)) {
			String numeroTel = Telefone.getNumero(contexto);
			url += "?tmp=" + numeroTel + "&versao=" + getVersaoSincronizacao();
		} else {
			String codId = Dispositivo.getId(contexto);
			url += "?cod=" + codId + "&versao=" + getVersaoSincronizacao(); 
		}	
		if (getVersaoSincronizacao()==3) {
			List<String> listaPalavra = HTTPRequestHelper.getHttpResponseList(url); 
			tam = processaResultadoLista(listaPalavra, dao);
		} else {
			String resultado = HTTPRequestHelper.getHttpResponse(url);
			tam = processaResultado(resultado,dao);
		}
		return tam;
	}
		
	// Passar para POST depois
	public List<CategoriaLojaProduto> listaSincronizada(Context contexto) {
		List<CategoriaLojaProduto> listaSaida = null;
		String url = getUrl("ListaSincronizada");
		if (!Dispositivo.isTablet(contexto)) {
			String numeroTel = Telefone.getNumero(contexto);
			url += "?tmp=" + numeroTel + "&versao=" + getVersaoSincronizacao();
		} else {
			String codId = Dispositivo.getId(contexto);
			url += "?cod=" + codId + "&versao=" + getVersaoSincronizacao(); 
		}	
		if (getVersaoSincronizacao()==3) {
			List<String> listaPalavra = HTTPRequestHelper.getHttpResponseList(url); 
			listaSaida = this.processaResultadoLista(listaPalavra);
		} else {
			String resultado = HTTPRequestHelper.getHttpResponse(url);
			listaSaida = processaResultado(resultado);
		}
		return listaSaida;
	}
	
	
	public List<CategoriaLojaProduto> listaSincronizadaUsuario(Context contexto) {
		String url = getUrl("ListaSincronizadaUsuario");
		if (!Dispositivo.isTablet(contexto)) {
			String numeroTel = Telefone.getNumero(contexto);
			url += "?tmp=" + numeroTel + "&versao=" + getVersaoSincronizacao();
		} else {
			String codId = Dispositivo.getId(contexto);
			url += "?cod=" + codId + "&versao=" + getVersaoSincronizacao(); 
		}	
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	

	// Come?ando com vers?o um fazer override ao mudar vers?o.
	protected int getVersaoSincronizacao() {
		return Constantes.VERSAO_SINCRONISMO;
	}
	
	
	@Override
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(Context contexto, long id) {
		String url = getUrl("ReferenteACategoriaLoja");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(Context contexto, long id, int qtde) {
		String url = getUrl("ReferenteACategoriaLoja");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(long id) {
		String url = getUrl("ReferenteACategoriaLoja");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<CategoriaLojaProduto> getPorReferenteACategoriaLoja(long id, int qtde) {
		String url = getUrl("ReferenteACategoriaLoja");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	@Override
	public List<CategoriaLojaProduto> getPorReferenteAProduto(Context contexto, long id) {
		String url = getUrl("ReferenteAProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<CategoriaLojaProduto> getPorReferenteAProduto(Context contexto, long id, int qtde) {
		String url = getUrl("ReferenteAProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<CategoriaLojaProduto> getPorReferenteAProduto(long id) {
		String url = getUrl("ReferenteAProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<CategoriaLojaProduto> getPorReferenteAProduto(long id, int qtde) {
		String url = getUrl("ReferenteAProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	


	public void atualizaRelacionamento(Produto item, List<DCIObjetoDominio> listaEscolhidos) {
		throw new UnsupportedOperationException();	
	}
	public void atualizaRelacionamento(CategoriaLoja item, List<DCIObjetoDominio> listaEscolhidos) {
		throw new UnsupportedOperationException();	
	}
	public boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		throw new UnsupportedOperationException();	
	}

	// ------------- Sincronismo Vers?o 2 ---------------------
	

	@Deprecated
	public List<CategoriaLojaProduto> listaSincronizadaUsuarioV2(Context contexto) {
		String url = getUrl("listaSincronizadaUsuarioV2");
		if (!Dispositivo.isTablet(contexto)) {
			String numeroTel = Telefone.getNumero(contexto);
			url += "?tmp=" + numeroTel + "&versao=" + getVersaoSincronizacao();
		} else {
			String codId = Dispositivo.getId(contexto);
			url += "?cod=" + codId + "&versao=" + getVersaoSincronizacao(); 
		}	
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	// ----------------------------------------------------------------
	
	
	// Para versao 3 de Sincronismo
	private List<CategoriaLojaProduto> processaResultadoLista(List<String> lista) {
		List<CategoriaLojaProduto> listaSaida = new LinkedList<CategoriaLojaProduto>();
		try {
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				CategoriaLojaProduto item = FabricaVo.criaCategoriaLojaProduto(json);
				listaSaida.add(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		return listaSaida;
	}
	private int processaResultadoLista(List<String> lista , DaoSincronismo daoSinc) {
		CategoriaLojaProduto item = null;
		try {
			daoSinc.dropCreate();
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				item = FabricaVo.criaCategoriaLojaProduto(json);
				daoSinc.insere(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		return lista.size();
	}
}