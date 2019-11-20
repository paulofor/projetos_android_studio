

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
import treinoacademia.servico.ExecucaoItemSerieServico;
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


public abstract class ExecucaoItemSerieServicoWsJsonBase 
		extends  ExecucaoItemSerieServico 
		implements ServicoRemoto<ExecucaoItemSerie>{

	private List<ExecucaoItemSerie> listaSaida = null;
	private String ultimaUrl = null;
	private DataSourceRemotoI dataSource = null;
	
	//String server = "192.168.1.2";
	//String aplicacao = "CadVideoCli";
		
	public void setDataSource(DataSourceRemotoI dataSource) {
		this.dataSource = dataSource;
	}
	public ExecucaoItemSerieServicoWsJsonBase() {
		setDataSource(new DataSourceRemoto());
	}
	
	
	@Override
	public void listaSincronizadaAlteracao(List<ExecucaoItemSerie> listaSinc, Context contexto) throws JSONException {
		Map<String,String> params = new HashMap<String,String>();
		String url = getUrl("ListaSincronizadaAlteracao");
		String saida = " [ ";
		boolean primeiro = true;
		for (ExecucaoItemSerie item:listaSinc) {
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
		DCLog.d(DCLog.SINCRONIZACAO_JSON,this,"Enviando...lista<ExecucaoItemSerie>:" + saida);
		String resultado = HTTPRequestHelper.getHttpResponse(url,params);
	}
	
	
	private String getUrl(String metodo) {
		String objeto = "ExecucaoItemSerie";
		ultimaUrl = "http://" + dataSource.getServer() + "/" + (dataSource.getAplicacao()!=null?dataSource.getAplicacao() + "/":"") + objeto + "WS/" + metodo + getFiltro().getRequest();
		return ultimaUrl;
		//return "http://" + server + "/" + aplicacao + "/" + objeto + "/" + metodo + getFiltro().getRequest();
  	}
	
	
	private static final String URL_GET_POSTS_RECENT = 
		"http://www.lojadigicom.com.br/treinoacademia/page/ExecucaoItemSeriePage.aspx";
	private JSONObject json;
	
	/*
	private final Handler handler = new Handler() {
		public void handleMessage(final Message msg) {
			//progressDialog.dismiss();
			String bundleResult = msg.getData().getString("RESPONSE");
			//output.setText(bundleResult);
			listaSaida = new ArrayList<ExecucaoItemSerie>();
			try {
				json = new JSONObject(bundleResult);
				JSONArray lista = json.getJSONArray("ExecucaoItemSerie");
				int tam = lista.length();
				JSONObject item = null;
				String nome = null;
				for (int i=0;i<lista.length();i++) {
					item = (JSONObject) lista.get(i);
					listaSaida.add(new ExecucaoItemSerie(item));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	*/
	 
	public List<ExecucaoItemSerie> ObtemPorDiaItemSerie(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("ObtemPorDiaItemSerie"));
		return processaResultado(resultado);
	} 
	public List<ExecucaoItemSerie> UltimasExecucoesUsuarioExercicio(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("UltimasExecucoesUsuarioExercicio"));
		return processaResultado(resultado);
	} 
	public List<ExecucaoItemSerie> UltimasExecucoesItemSerie(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("UltimasExecucoesItemSerie"));
		return processaResultado(resultado);
	} 
	public List<ExecucaoItemSerie> CarregaCompletoPorDia(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("CarregaCompletoPorDia"));
		return processaResultado(resultado);
	}
	public ExecucaoItemSerie PrimeiraPorDia(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("PrimeiraPorDia"));
		return processaResultadoObjeto(resultado);
	}
	public ExecucaoItemSerie UltimaPorDia(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("UltimaPorDia"));
		return processaResultadoObjeto(resultado);
	}
	public ExecucaoItemSerie PrimeiraPorSerie(DigicomContexto ctx){
		String resultado = HTTPRequestHelper.getHttpResponse(getUrl("PrimeiraPorSerie"));
		return processaResultadoObjeto(resultado);
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
			ExecucaoItemSerie obj = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				obj = FabricaVo.criaExecucaoItemSerie(item);
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
	private List<ExecucaoItemSerie> processaResultado(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		List<ExecucaoItemSerie> listaSaida = new LinkedList<ExecucaoItemSerie>();
		
		//ServicoLocal<ExecucaoItemSerie> servicoLocal = (ServicoLocal<ExecucaoItemSerie>) FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		//ExecucaoItemSerie corrente = null;
		
		try {
			json = new JSONObject(resultado);
			JSONArray lista = json.getJSONArray("Lista");
			int tam = lista.length();
			JSONObject item = null;
			String nome = null;
			for (int i=0;i<lista.length();i++) {
				item = (JSONObject) lista.get(i);
				//corrente = FabricaVo.criaExecucaoItemSerie(item);
				//servicoLocal.insertLocal(corrente);
				listaSaida.add(FabricaVo.criaExecucaoItemSerie(item));
			}
			lista = null;			
		} catch (Exception e) {
			//Log.e("ExecucaoItemSerieServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		
		return listaSaida;	
	}

	private ExecucaoItemSerie processaResultadoObjeto(String resultado) {
		int inicio = resultado.indexOf('{');
		resultado = resultado.substring(inicio);
		ExecucaoItemSerie saida = null;
		
		
		try {
			json = new JSONObject(resultado);
			JSONObject item = json.getJSONObject("Lista");
			String nome = null;
			saida = FabricaVo.criaExecucaoItemSerie(item);
			
		} catch (Exception e) {
			//Log.e("ExecucaoItemSerieServicoWsJson", e.getMessage() + " [ " + ultimaUrl + " ]");
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		
		return saida;	
	}

	public void dropCreate(Context contexto) {
	}
	public void insertAll(List<ExecucaoItemSerie> lista, Context contexto) {
	}
	public void insertSincAll(List<ExecucaoItemSerie> lista, Context contexto) {
	}
	
	public final void alteraParaSincronizacao(ExecucaoItemSerie item) {
		throw new UnsupportedOperationException();
	}
	public final void insereParaSincronizacao(ExecucaoItemSerie item) {
		throw new UnsupportedOperationException();
	}
	public final void excluiParaSincronizacao(ExecucaoItemSerie item) {
		throw new UnsupportedOperationException();
	}
	public final void criaTabelaSincronizacao() {
	}
	
	
	// Itens Tela
	public final ExecucaoItemSerie inicializaItemTela(DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final ExecucaoItemSerie inicializaItemTela(ExecucaoItemSerie itemTela, DigicomContexto contexto) {
		throw new UnsupportedOperationException();
	}
	public final void finalizaItemTela(ExecucaoItemSerie itemTela, boolean insercao, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	public final void processaItemTela(ExecucaoItemSerie itemTela, DigicomContexto contexto){
		throw new UnsupportedOperationException();
	}
	
	public final ExecucaoItemSerie ultimoInicializado() {
		throw new UnsupportedOperationException();
	}
	
	public final ExecucaoItemSerie getById(long id, Context contexto) {
		throw new UnsupportedOperationException();
	}
	public final ExecucaoItemSerie getById(long id) {
		throw new UnsupportedOperationException();
	}
	
	public List<ExecucaoItemSerie> getAll(Context contexto) {
		String url = getUrl("ListaCorrente");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	public List<ExecucaoItemSerie> getAllTela(Context contexto) {
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
	public List<ExecucaoItemSerie> listaSincronizada(Context contexto) {
		List<ExecucaoItemSerie> listaSaida = null;
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
	
	
	public List<ExecucaoItemSerie> listaSincronizadaUsuario(Context contexto) {
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
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(Context contexto, long id) {
		String url = getUrl("ReferenteAItemSerie");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(Context contexto, long id, int qtde) {
		String url = getUrl("ReferenteAItemSerie");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(long id) {
		String url = getUrl("ReferenteAItemSerie");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAItemSerie(long id, int qtde) {
		String url = getUrl("ReferenteAItemSerie");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	@Override
	public List<ExecucaoItemSerie> getPorEmDiaTreino(Context contexto, long id) {
		String url = getUrl("EmDiaTreino");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<ExecucaoItemSerie> getPorEmDiaTreino(Context contexto, long id, int qtde) {
		String url = getUrl("EmDiaTreino");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<ExecucaoItemSerie> getPorEmDiaTreino(long id) {
		String url = getUrl("EmDiaTreino");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<ExecucaoItemSerie> getPorEmDiaTreino(long id, int qtde) {
		String url = getUrl("EmDiaTreino");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(Context contexto, long id) {
		String url = getUrl("ReferenteAExercicio");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(Context contexto, long id, int qtde) {
		String url = getUrl("ReferenteAExercicio");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(long id) {
		String url = getUrl("ReferenteAExercicio");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	// Indo buscar a mesma informa??o ( alterar depois )
	@Override
	public List<ExecucaoItemSerie> getPorReferenteAExercicio(long id, int qtde) {
		String url = getUrl("ReferenteAExercicio");
		String resultado = HTTPRequestHelper.getHttpResponse(url);
		return processaResultado(resultado);
	}
	
	



	// ------------- Sincronismo Vers?o 2 ---------------------
	

	@Deprecated
	public List<ExecucaoItemSerie> listaSincronizadaUsuarioV2(Context contexto) {
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
	private List<ExecucaoItemSerie> processaResultadoLista(List<String> lista) {
		List<ExecucaoItemSerie> listaSaida = new LinkedList<ExecucaoItemSerie>();
		try {
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				ExecucaoItemSerie item = FabricaVo.criaExecucaoItemSerie(json);
				listaSaida.add(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return listaSaida;
	}
	private int processaResultadoLista(List<String> lista , DaoSincronismo daoSinc) {
		ExecucaoItemSerie item = null;
		try {
			daoSinc.dropCreate();
			for (String palavra : lista) {
				JSONObject json = new JSONObject(palavra);
				item = FabricaVo.criaExecucaoItemSerie(json);
				daoSinc.insere(item);
			}
		} catch (Exception e) {
			DCLog.e(DCLog.SINCRONIZACAO_JSON, this, e);
			TrataErro.getInstancia().setErro(e);
		}
		return lista.size();
	}
}