

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
import coletapreco.servico.OportunidadeDiaServico;
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


public abstract class OportunidadeDiaServicoWsJsonBase 
		extends  OportunidadeDiaServico 
		implements ServicoRemoto<OportunidadeDia>{

	private List<OportunidadeDia> listaSaida = null;
	private String ultimaUrl = null;
	private DataSourceRemotoI dataSource = null;
	
	//String server = "192.168.1.2";
	//String aplicacao = "CadVideoCli";
		
	public void setDataSource(DataSourceRemotoI dataSource) {
		this.dataSource = dataSource;
	}
	public OportunidadeDiaServicoWsJsonBase() {
		setDataSource(new DataSourceRemoto());
	}
	
	
	@Override
	public void listaSincronizadaAlteracao(List<OportunidadeDia> listaSinc, Context contexto) throws JSONException {
		Map<String,String> params = new HashMap<String,String>();
		String url = getUrl("ListaSincronizadaAlteracao");
		String saida = " [ ";
		boolean primeiro = true;
		for (OportunidadeDia item:listaSinc) {
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
		DCLog.d(DCLog.SINCRONIZACAO_JSON,this,"Enviando...lista<OportunidadeDia>:" + saida);
		String resultado = HTTPRequestHelper.getHttpResponse(url,params);
	}
	
	
	private String getUrl(String metodo) {
		String objeto = "OportunidadeDia";
		ultimaUrl = "http://" + dataSource.getServer() + "/" + (dataSource.getAplicacao()!=null?dataSource.getAplicacao() + "/":"") + objeto + "WS/" + metodo + getFiltro().getRequest();
		return ultimaUrl;
		//return "http://" + server + "/" + aplicacao + "/" + objeto + "/" + metodo + getFiltro().getRequest();
  	}
	
	
	private static final String URL_GET_POSTS_RECENT = 
		"http://www.lojadigicom.com.br/coletapreco/page/OportunidadeDiaPage.aspx";
	private JSONObject json;
	
	/*
	private final Handler handler = new Handler() {
		public void handleMessage(final Message msg) {
			//progressDialog.dismiss();
			String bundleResult = msg.getData().getString("RESPONSE");
			//output.setText(bundleResult);
			listaSaida = new ArrayList<OportunidadeDia>();
			try {
				json = new JSONObject(bundleResult);
				JSONArray lista = json.getJSONArray("OportunidadeDia");
				int tam = lista.length();
				JSONObject item = null;
				String nome = null;
				for (int i=0;i<lista.length();i++) {
					item = (JSONObject) lista.get(i);
					listaSaida.add(new OportunidadeDia(item));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	*/
	 
	public List<OportunidadeDia> ListaPorNatureza(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("ListaPorNatureza"));
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
			OportunidadeDia obj = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				obj = FabricaVo.criaOportunidadeDia(item);
				daoSinc.insere(obj);
			}
			lista = null;
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		return tam;
	}

	@Deprecated
	private List<OportunidadeDia> processaResultado(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		List<OportunidadeDia> listaSaida = new LinkedList<OportunidadeDia>();
		
		//ServicoLocal<OportunidadeDia> servicoLocal = (ServicoLocal<OportunidadeDia>) FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
		//OportunidadeDia corrente = null;
		
		try {
			json = new JSONObject(resultado);
			JSONArray lista = json.getJSONArray("Lista");
			int tam = lista.length();
			JSONObject item = null;
			String nome = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				//corrente = FabricaVo.criaOportunidadeDia(item);
				//servicoLocal.insertLocal(corrente);
				listaSaida.add(FabricaVo.criaOportunidadeDia(item));
			}
			lista = null;			
		} catch (Exception e) {
			//Log.e("OportunidadeDiaServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		
		return listaSaida;	
	}

	private OportunidadeDia processaResultadoObjeto(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		OportunidadeDia saida = null;
		
		
		try {
			json = new JSONObject(resultado);
			JSONObject item = json.getJSONObject("Lista");
			String nome = null;
			saida = FabricaVo.criaOportunidadeDia(item);
			
		} catch (Exception e) {
			//Log.e("OportunidadeDiaServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		
		return saida;	
	}

	public void dropCreate(Context contexto) {
	}
	public void insertAll(List<OportunidadeDia> lista, Context contexto) {
	}
	public void insertSincAll(List<OportunidadeDia> lista, Context contexto) {
	}
	
	public final void alteraParaSincronizacao(OportunidadeDia item) {
		throw new UnsupportedOperationException();
	}
	public final void insereParaSincronizacao(OportunidadeDia item) {
		throw new UnsupportedOperationException();
	}
	public final void criaTabelaSincronizacao() {
	}
	
	
	// Itens Tela
	public final OportunidadeDia inicializaItemTela(DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final OportunidadeDia inicializaItemTela(OportunidadeDia itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final void finalizaItemTela(OportunidadeDia itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	public final void processaItemTela(OportunidadeDia itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	
	public final OportunidadeDia ultimoInicializado() {
		throw new UnsupportedOperationException();
	}
	
	public final OportunidadeDia getById(long id, Context contexto) {
		throw new UnsupportedOperationException();
	}
	public final OportunidadeDia getById(long id) {
		throw new UnsupportedOperationException();
	}
	
	public List<OportunidadeDia> getAll(Context contexto) {
		String url = getUrl("ListaCorrente");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	public List<OportunidadeDia> getAllTela(Context contexto) {
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
	public List<OportunidadeDia> listaSincronizada(Context contexto) {
		List<OportunidadeDia> listaSaida = null;
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
	
	
	public List<OportunidadeDia> listaSincronizadaUsuario(Context contexto) {
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
	public List<OportunidadeDia> getPorReferenteAProduto(Context contexto, long id) {
		String url = getUrl("ReferenteAProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<OportunidadeDia> getPorReferenteAProduto(Context contexto, long id, int qtde) {
		String url = getUrl("ReferenteAProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<OportunidadeDia> getPorReferenteAProduto(long id) {
		String url = getUrl("ReferenteAProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<OportunidadeDia> getPorReferenteAProduto(long id, int qtde) {
		String url = getUrl("ReferenteAProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	@Override
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(Context contexto, long id) {
		String url = getUrl("PertenceANaturezaProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(Context contexto, long id, int qtde) {
		String url = getUrl("PertenceANaturezaProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(long id) {
		String url = getUrl("PertenceANaturezaProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<OportunidadeDia> getPorPertenceANaturezaProduto(long id, int qtde) {
		String url = getUrl("PertenceANaturezaProduto");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	



	// ------------- Sincronismo Vers?o 2 ---------------------
	

	@Deprecated
	public List<OportunidadeDia> listaSincronizadaUsuarioV2(Context contexto) {
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
	private List<OportunidadeDia> processaResultadoLista(List<String> lista) {
		List<OportunidadeDia> listaSaida = new LinkedList<OportunidadeDia>();
		try {
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				OportunidadeDia item = FabricaVo.criaOportunidadeDia(json);
				listaSaida.add(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		return listaSaida;
	}
	private int processaResultadoLista(List<String> lista , DaoSincronismo daoSinc) {
		OportunidadeDia item = null;
		try {
			daoSinc.dropCreate();
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				item = FabricaVo.criaOportunidadeDia(json);
				daoSinc.insere(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
		}
		return lista.size();
	}
}