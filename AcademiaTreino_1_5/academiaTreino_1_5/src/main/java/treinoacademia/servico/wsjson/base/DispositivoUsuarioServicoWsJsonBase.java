

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
import treinoacademia.servico.DispositivoUsuarioServico;
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


public abstract class DispositivoUsuarioServicoWsJsonBase 
		extends  DispositivoUsuarioServico 
		implements ServicoRemoto<DispositivoUsuario>{

	private List<DispositivoUsuario> listaSaida = null;
	private String ultimaUrl = null;
	private DataSourceRemotoI dataSource = null;
	
	//String server = "192.168.1.2";
	//String aplicacao = "CadVideoCli";
		
	public void setDataSource(DataSourceRemotoI dataSource) {
		this.dataSource = dataSource;
	}
	public DispositivoUsuarioServicoWsJsonBase() {
		setDataSource(new DataSourceRemoto());
	}
	
	
	@Override
	public void listaSincronizadaAlteracao(List<DispositivoUsuario> listaSinc, Context contexto) throws JSONException {
		Map<String,String> params = new HashMap<String,String>();
		String url = getUrl("ListaSincronizadaAlteracao");
		String saida = " [ ";
		boolean primeiro = true;
		for (DispositivoUsuario item:listaSinc) {
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
		DCLog.d(DCLog.SINCRONIZACAO_JSON,this,"Enviando...lista<DispositivoUsuario>:" + saida);
		String resultado = HTTPRequestHelper.getHttpResponse(url,params);
	}
	
	
	private String getUrl(String metodo) {
		String objeto = "DispositivoUsuario";
		ultimaUrl = "http://" + dataSource.getServer() + "/" + (dataSource.getAplicacao()!=null?dataSource.getAplicacao() + "/":"") + objeto + "WS/" + metodo + getFiltro().getRequest();
		return ultimaUrl;
		//return "http://" + server + "/" + aplicacao + "/" + objeto + "/" + metodo + getFiltro().getRequest();
  	}
	
	
	private static final String URL_GET_POSTS_RECENT = 
		"http://www.lojadigicom.com.br/treinoacademia/page/DispositivoUsuarioPage.aspx";
	private JSONObject json;
	
	/*
	private final Handler handler = new Handler() {
		public void handleMessage(final Message msg) {
			//progressDialog.dismiss();
			String bundleResult = msg.getData().getString("RESPONSE");
			//output.setText(bundleResult);
			listaSaida = new ArrayList<DispositivoUsuario>();
			try {
				json = new JSONObject(bundleResult);
				JSONArray lista = json.getJSONArray("DispositivoUsuario");
				int tam = lista.length();
				JSONObject item = null;
				String nome = null;
				for (int i=0;i<lista.length();i++) {
					item = (JSONObject) lista.get(i);
					listaSaida.add(new DispositivoUsuario(item));
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
			DispositivoUsuario obj = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				obj = FabricaVo.criaDispositivoUsuario(item);
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
	private List<DispositivoUsuario> processaResultado(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		List<DispositivoUsuario> listaSaida = new LinkedList<DispositivoUsuario>();
		
		//ServicoLocal<DispositivoUsuario> servicoLocal = (ServicoLocal<DispositivoUsuario>) FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
		//DispositivoUsuario corrente = null;
		
		try {
			json = new JSONObject(resultado);
			JSONArray lista = json.getJSONArray("Lista");
			int tam = lista.length();
			JSONObject item = null;
			String nome = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				//corrente = FabricaVo.criaDispositivoUsuario(item);
				//servicoLocal.insertLocal(corrente);
				listaSaida.add(FabricaVo.criaDispositivoUsuario(item));
			}
			lista = null;			
		} catch (Exception e) {
			//Log.e("DispositivoUsuarioServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		
		return listaSaida;	
	}

	private DispositivoUsuario processaResultadoObjeto(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		DispositivoUsuario saida = null;
		
		
		try {
			json = new JSONObject(resultado);
			JSONObject item = json.getJSONObject("Lista");
			String nome = null;
			saida = FabricaVo.criaDispositivoUsuario(item);
			
		} catch (Exception e) {
			//Log.e("DispositivoUsuarioServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		
		return saida;	
	}

	public void dropCreate(Context contexto) {
	}
	public void insertAll(List<DispositivoUsuario> lista, Context contexto) {
	}
	public void insertSincAll(List<DispositivoUsuario> lista, Context contexto) {
	}
	
	public final void alteraParaSincronizacao(DispositivoUsuario item) {
		throw new UnsupportedOperationException();
	}
	public final void insereParaSincronizacao(DispositivoUsuario item) {
		throw new UnsupportedOperationException();
	}
	public final void excluiParaSincronizacao(DispositivoUsuario item) {
		throw new UnsupportedOperationException();
	}
	public final void criaTabelaSincronizacao() {
	}
	
	public final DispositivoUsuario getCorrente() {
		throw new UnsupportedOperationException();
	}
	
	
	// Itens Tela
	public final DispositivoUsuario inicializaItemTela(DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final DispositivoUsuario inicializaItemTela(DispositivoUsuario itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final void finalizaItemTela(DispositivoUsuario itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	public final void processaItemTela(DispositivoUsuario itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	
	public final DispositivoUsuario ultimoInicializado() {
		throw new UnsupportedOperationException();
	}
	
	public final DispositivoUsuario getById(long id, Context contexto) {
		throw new UnsupportedOperationException();
	}
	public final DispositivoUsuario getById(long id) {
		throw new UnsupportedOperationException();
	}
	
	public List<DispositivoUsuario> getAll(Context contexto) {
		String url = getUrl("ListaCorrente");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	public List<DispositivoUsuario> getAllTela(Context contexto) {
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
	public List<DispositivoUsuario> listaSincronizada(Context contexto) {
		List<DispositivoUsuario> listaSaida = null;
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
	
	
	public List<DispositivoUsuario> listaSincronizadaUsuario(Context contexto) {
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
	public List<DispositivoUsuario> getPorReferenteAUsuario(Context contexto, long id) {
		String url = getUrl("ReferenteAUsuario");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<DispositivoUsuario> getPorReferenteAUsuario(Context contexto, long id, int qtde) {
		String url = getUrl("ReferenteAUsuario");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<DispositivoUsuario> getPorReferenteAUsuario(long id) {
		String url = getUrl("ReferenteAUsuario");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<DispositivoUsuario> getPorReferenteAUsuario(long id, int qtde) {
		String url = getUrl("ReferenteAUsuario");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	



	// ------------- Sincronismo Vers?o 2 ---------------------
	

	@Deprecated
	public List<DispositivoUsuario> listaSincronizadaUsuarioV2(Context contexto) {
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
	private List<DispositivoUsuario> processaResultadoLista(List<String> lista) {
		List<DispositivoUsuario> listaSaida = new LinkedList<DispositivoUsuario>();
		try {
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				DispositivoUsuario item = FabricaVo.criaDispositivoUsuario(json);
				listaSaida.add(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return listaSaida;
	}
	private int processaResultadoLista(List<String> lista , DaoSincronismo daoSinc) {
		DispositivoUsuario item = null;
		try {
			daoSinc.dropCreate();
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				item = FabricaVo.criaDispositivoUsuario(json);
				daoSinc.insere(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return lista.size();
	}
}