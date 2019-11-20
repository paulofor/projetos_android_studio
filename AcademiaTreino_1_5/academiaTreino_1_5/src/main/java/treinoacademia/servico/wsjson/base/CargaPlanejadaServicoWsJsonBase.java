

package treinoacademia.servico.wsjson.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import treinoacademia.dao.datasource.DataSourceRemoto;
import treinoacademia.modelo.*;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.CargaPlanejadaServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.app.Constantes;
import treinoacademia.app.TrataErro;
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


public abstract class CargaPlanejadaServicoWsJsonBase 
		extends  CargaPlanejadaServico 
		implements ServicoRemoto<CargaPlanejada>{

	private List<CargaPlanejada> listaSaida = null;
	private String ultimaUrl = null;
	private DataSourceRemotoI dataSource = null;
	
	//String server = "192.168.1.2";
	//String aplicacao = "CadVideoCli";
		
	public void setDataSource(DataSourceRemotoI dataSource) {
		this.dataSource = dataSource;
	}
	public CargaPlanejadaServicoWsJsonBase() {
		setDataSource(new DataSourceRemoto());
	}
	
	
	@Override
	public void listaSincronizadaAlteracao(List<CargaPlanejada> listaSinc, Context contexto) throws JSONException {
		Map<String,String> params = new HashMap<String,String>();
		String url = getUrl("ListaSincronizadaAlteracao");
		String saida = " [ ";
		boolean primeiro = true;
		for (CargaPlanejada item:listaSinc) {
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
		DCLog.d(DCLog.SINCRONIZACAO_JSON,this,"Enviando...lista<CargaPlanejada>:" + saida);
		String resultado = HTTPRequestHelper.getHttpResponse(url,params);
	}
	
	
	private String getUrl(String metodo) {
		String objeto = "CargaPlanejada";
		ultimaUrl = "http://" + dataSource.getServer() + "/" + (dataSource.getAplicacao()!=null?dataSource.getAplicacao() + "/":"") + objeto + "WS/" + metodo + getFiltro().getRequest();
		return ultimaUrl;
		//return "http://" + server + "/" + aplicacao + "/" + objeto + "/" + metodo + getFiltro().getRequest();
  	}
	
	
	private static final String URL_GET_POSTS_RECENT = 
		"http://www.lojadigicom.com.br/treinoacademia/page/CargaPlanejadaPage.aspx";
	private JSONObject json;
	
	/*
	private final Handler handler = new Handler() {
		public void handleMessage(final Message msg) {
			//progressDialog.dismiss();
			String bundleResult = msg.getData().getString("RESPONSE");
			//output.setText(bundleResult);
			listaSaida = new ArrayList<CargaPlanejada>();
			try {
				json = new JSONObject(bundleResult);
				JSONArray lista = json.getJSONArray("CargaPlanejada");
				int tam = lista.length();
				JSONObject item = null;
				String nome = null;
				for (int i=0;i<lista.length();i++) {
					item = (JSONObject) lista.get(i);
					listaSaida.add(new CargaPlanejada(item));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	*/
	 
	public List<CargaPlanejada> ListaCargaPorExercicio(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("ListaCargaPorExercicio"));
		return processaResultado(resultado);
	} 
	public List<CargaPlanejada> ListaCargaAtivaPorExercicio(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("ListaCargaAtivaPorExercicio"));
		return processaResultado(resultado);
	}

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
			CargaPlanejada obj = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				obj = FabricaVo.criaCargaPlanejada(item);
				daoSinc.insere(obj);
			}
			lista = null;
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return tam;
	}

	@Deprecated
	private List<CargaPlanejada> processaResultado(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		List<CargaPlanejada> listaSaida = new LinkedList<CargaPlanejada>();
		
		//ServicoLocal<CargaPlanejada> servicoLocal = (ServicoLocal<CargaPlanejada>) FabricaServico.getInstancia().getCargaPlanejadaServico(FabricaServico.TIPO_SQLITE);
		//CargaPlanejada corrente = null;
		
		try {
			json = new JSONObject(resultado);
			JSONArray lista = json.getJSONArray("Lista");
			int tam = lista.length();
			JSONObject item = null;
			String nome = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				//corrente = FabricaVo.criaCargaPlanejada(item);
				//servicoLocal.insertLocal(corrente);
				listaSaida.add(FabricaVo.criaCargaPlanejada(item));
			}
			lista = null;			
		} catch (Exception e) {
			//Log.e("CargaPlanejadaServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		
		return listaSaida;	
	}

	private CargaPlanejada processaResultadoObjeto(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		CargaPlanejada saida = null;
		
		
		try {
			json = new JSONObject(resultado);
			JSONObject item = json.getJSONObject("Lista");
			String nome = null;
			saida = FabricaVo.criaCargaPlanejada(item);
			
		} catch (Exception e) {
			//Log.e("CargaPlanejadaServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		
		return saida;	
	}

	public void dropCreate(Context contexto) {
	}
	public void insertAll(List<CargaPlanejada> lista, Context contexto) {
	}
	public void insertSincAll(List<CargaPlanejada> lista, Context contexto) {
	}
	
	public final void alteraParaSincronizacao(CargaPlanejada item) {
		throw new UnsupportedOperationException();
	}
	public final void insereParaSincronizacao(CargaPlanejada item) {
		throw new UnsupportedOperationException();
	}
	public final void excluiParaSincronizacao(CargaPlanejada item) {
		throw new UnsupportedOperationException();
	}
	public final void criaTabelaSincronizacao() {
	}
	
	
	// Itens Tela
	public final CargaPlanejada inicializaItemTela(DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final CargaPlanejada inicializaItemTela(CargaPlanejada itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final void finalizaItemTela(CargaPlanejada itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	public final void processaItemTela(CargaPlanejada itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	
	public final CargaPlanejada ultimoInicializado() {
		throw new UnsupportedOperationException();
	}
	
	public final CargaPlanejada getById(long id, Context contexto) {
		throw new UnsupportedOperationException();
	}
	public final CargaPlanejada getById(long id) {
		throw new UnsupportedOperationException();
	}
	
	public List<CargaPlanejada> getAll(Context contexto) {
		String url = getUrl("ListaCorrente");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	public List<CargaPlanejada> getAllTela(Context contexto) {
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
	public List<CargaPlanejada> listaSincronizada(Context contexto) {
		List<CargaPlanejada> listaSaida = null;
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
	
	
	public List<CargaPlanejada> listaSincronizadaUsuario(Context contexto) {
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
	public List<CargaPlanejada> getPorReferenteAItemSerie(Context contexto, long id) {
		String url = getUrl("ReferenteAItemSerie");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<CargaPlanejada> getPorReferenteAItemSerie(Context contexto, long id, int qtde) {
		String url = getUrl("ReferenteAItemSerie");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<CargaPlanejada> getPorReferenteAItemSerie(long id) {
		String url = getUrl("ReferenteAItemSerie");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<CargaPlanejada> getPorReferenteAItemSerie(long id, int qtde) {
		String url = getUrl("ReferenteAItemSerie");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	



	// ------------- Sincronismo Vers?o 2 ---------------------
	

	@Deprecated
	public List<CargaPlanejada> listaSincronizadaUsuarioV2(Context contexto) {
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
	private List<CargaPlanejada> processaResultadoLista(List<String> lista) {
		List<CargaPlanejada> listaSaida = new LinkedList<CargaPlanejada>();
		try {
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				CargaPlanejada item = FabricaVo.criaCargaPlanejada(json);
				listaSaida.add(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return listaSaida;
	}
	private int processaResultadoLista(List<String> lista , DaoSincronismo daoSinc) {
		CargaPlanejada item = null;
		try {
			daoSinc.dropCreate();
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				item = FabricaVo.criaCargaPlanejada(json);
				daoSinc.insere(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return lista.size();
	}
}