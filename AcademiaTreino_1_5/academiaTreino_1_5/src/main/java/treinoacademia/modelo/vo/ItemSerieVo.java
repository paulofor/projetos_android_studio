package treinoacademia.modelo.vo;

import android.view.View;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.ObjetoSinc;
import br.com.digicom.util.ConversorJson;
import br.com.digicom.activity.DigicomContexto;
import treinoacademia.modelo.*;
import treinoacademia.modelo.agregado.ItemSerieAgregado;
import treinoacademia.modelo.derivada.impl.ItemSerieDerivada;
import treinoacademia.modelo.display.impl.ItemSerieDisplay;
import br.com.digicom.modelo.DCIObjetoDominio;


public class ItemSerieVo implements ItemSerie , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idItemSerie;
    }
  
  
  	private DigicomContexto _contexto = null;
    public DigicomContexto getContexto() {
    	if (_contexto==null) 
    		throw new RuntimeException("DigicomContexto n?o inicializado");
    	return _contexto;
    }
  	public void setContexto(DigicomContexto ctx) {
  		_contexto = ctx;
  	}
  	
  
  	public ItemSerieVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdItemSerie\" : \"" + idItemSerie + "\" "
		+ ", \"Repeticoes\" : \"" + repeticoes + "\" "
		+ ", \"Serie\" : \"" + serie + "\" "
		+ ", \"Parametros\" : \"" + parametros + "\" "
		+ ", \"OrdemExecucao\" : \"" + ordemExecucao + "\" "
		+ ", \"IdExercicioEd\" : \"" + idExercicioEd + "\" "
		+ ", \"IdSerieTreinoPa\" : \"" + idSerieTreinoPa + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdItemSerie",idItemSerie);
			json.put("Repeticoes",repeticoes);
			json.put("Serie",serie);
			json.put("Parametros",parametros);
			json.put("OrdemExecucao",ordemExecucao);
			json.put("IdExercicioEd",idExercicioEd);
			json.put("IdSerieTreinoPa",idSerieTreinoPa);
			json.put("IdUsuarioS",idUsuarioS);
		
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}

	public JSONObject JSonSimples() throws JSONException{
		return JSonAtributos();
	}

	// apagar em 04-05-2015
	/**
 	* @deprecated  Substituir por JSonSimples()
 	*/
	@Deprecated
	public JSONObject JSon() throws JSONException{
		JSONObject json = JSonAtributos();
		//try {
		JSONArray listaCargaPlanejada_Possui = JSonListaCargaPlanejada_Possui();
		if (listaCargaPlanejada_Possui!=null) {
			json.put("ListaCargaPlanejadaVo_Possui",listaCargaPlanejada_Possui);
		} 
		JSONArray listaExecucaoItemSerie_Gera = JSonListaExecucaoItemSerie_Gera();
		if (listaExecucaoItemSerie_Gera!=null) {
			json.put("ListaExecucaoItemSerieVo_Gera",listaExecucaoItemSerie_Gera);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaCargaPlanejada_Possui() throws JSONException{
		if (getListaCargaPlanejada_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (CargaPlanejada item:this.getListaCargaPlanejada_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaExecucaoItemSerie_Gera() throws JSONException{
		if (getListaExecucaoItemSerie_Gera()==null) return null;
		JSONArray lista = new JSONArray();
		for (ExecucaoItemSerie item:this.getListaExecucaoItemSerie_GeraOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdItemSerie=" + getIdItemSerie() 
		+  " Repeticoes=" + getRepeticoes() 
		+  " Serie=" + getSerie() 
		+  " Parametros=" + getParametros() 
		+  " OrdemExecucao=" + getOrdemExecucao() 
		+  " IdExercicioEd=" + getIdExercicioEd() 
		+  " IdSerieTreinoPa=" + getIdSerieTreinoPa() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idItemSerie;
 	}
 	public String getNomeClasse() {
 		return "ItemSerie";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ItemSerieDisplay display = null;
	private ItemSerieDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ItemSerieDisplay(this);
		}
		return display;
	}
	@Override
	public View getItemListaView() {
		return getObjetoDisplay().getItemListaView();
	}
	@Override
	public String getItemListaTexto() {
		return getObjetoDisplay().getItemListaTexto();
	}
	// ----- FINAL DISPLAY -----------------
 
    // ----- INICIO AGREGADO -----------------
	private ItemSerieAgregado agregado = null;
	private ItemSerieAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ItemSerieAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ItemSerieDerivada derivada = null;
	private ItemSerieDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ItemSerieDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	public boolean getConcluidoNoDia()
	{
		return getDerivada().getConcluidoNoDia(); 
	} 
	public void setConcluidoNoDia(boolean value)
	{
		getDerivada().setConcluidoNoDia(value); 
	} 
	
	
	// ----- FINAL DERIVADA -----------------
	
	
	
 
  
  
  	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
		public Exercicio getExercicio_ExecucaoDe() {
			return getAgregado().getExercicio_ExecucaoDe();
		}
		public void setExercicio_ExecucaoDe(Exercicio item) {
			getAgregado().setExercicio_ExecucaoDe(item);
		}
		
		public void addListaExercicio_ExecucaoDe(Exercicio item) {
			getAgregado().addListaExercicio_ExecucaoDe(item);
		}
		public Exercicio getCorrenteExercicio_ExecucaoDe() {
			return getAgregado().getCorrenteExercicio_ExecucaoDe();
		}
		
		
		public SerieTreino getSerieTreino_PertencenteA() {
			return getAgregado().getSerieTreino_PertencenteA();
		}
		public void setSerieTreino_PertencenteA(SerieTreino item) {
			getAgregado().setSerieTreino_PertencenteA(item);
		}
		
		public void addListaSerieTreino_PertencenteA(SerieTreino item) {
			getAgregado().addListaSerieTreino_PertencenteA(item);
		}
		public SerieTreino getCorrenteSerieTreino_PertencenteA() {
			return getAgregado().getCorrenteSerieTreino_PertencenteA();
		}
		
		
		public Usuario getUsuario_Sincroniza() {
			return getAgregado().getUsuario_Sincroniza();
		}
		public void setUsuario_Sincroniza(Usuario item) {
			getAgregado().setUsuario_Sincroniza(item);
		}
		
		public void addListaUsuario_Sincroniza(Usuario item) {
			getAgregado().addListaUsuario_Sincroniza(item);
		}
		public Usuario getCorrenteUsuario_Sincroniza() {
			return getAgregado().getCorrenteUsuario_Sincroniza();
		}
		
		
	
	// SemChaveEstrangeira
	
		public CargaPlanejada getCorrenteCargaPlanejada_Possui() {
			return getAgregado().getCorrenteCargaPlanejada_Possui();
		}
		public void addListaCargaPlanejada_Possui(CargaPlanejada item) {
			getAgregado().addListaCargaPlanejada_Possui(item);
		}
		public List<CargaPlanejada> getListaCargaPlanejada_Possui() {
			return getAgregado().getListaCargaPlanejada_Possui();
		}
		public List<CargaPlanejada> getListaCargaPlanejada_PossuiOriginal() {
			return getAgregado().getListaCargaPlanejada_PossuiOriginal();
		}
		public List<CargaPlanejada> getListaCargaPlanejada_Possui(int qtde) {
			return getAgregado().getListaCargaPlanejada_Possui(qtde);
		}
		public void setListaCargaPlanejada_Possui(List<CargaPlanejada> lista) {
			getAgregado().setListaCargaPlanejada_Possui(lista);
		}
		public void setListaCargaPlanejada_PossuiByDao(List<CargaPlanejada> lista) {
			getAgregado().setListaCargaPlanejada_PossuiByDao(lista);
		}
		public void criaVaziaListaCargaPlanejada_Possui() {
			getAgregado().criaVaziaListaCargaPlanejada_Possui();
		}
		
		public boolean existeListaCargaPlanejada_Possui() {
			return getAgregado().existeListaCargaPlanejada_Possui();
		}
 		
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_Gera() {
			return getAgregado().getCorrenteExecucaoItemSerie_Gera();
		}
		public void addListaExecucaoItemSerie_Gera(ExecucaoItemSerie item) {
			getAgregado().addListaExecucaoItemSerie_Gera(item);
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Gera() {
			return getAgregado().getListaExecucaoItemSerie_Gera();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_GeraOriginal() {
			return getAgregado().getListaExecucaoItemSerie_GeraOriginal();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Gera(int qtde) {
			return getAgregado().getListaExecucaoItemSerie_Gera(qtde);
		}
		public void setListaExecucaoItemSerie_Gera(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_Gera(lista);
		}
		public void setListaExecucaoItemSerie_GeraByDao(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_GeraByDao(lista);
		}
		public void criaVaziaListaExecucaoItemSerie_Gera() {
			getAgregado().criaVaziaListaExecucaoItemSerie_Gera();
		}
		
		public boolean existeListaExecucaoItemSerie_Gera() {
			return getAgregado().existeListaExecucaoItemSerie_Gera();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ItemSerieVo(JSONObject json) throws JSONException{
		idItemSerie =  ConversorJson.getInteger(json, "IdItemSerie");
		repeticoes =  ConversorJson.getInteger(json, "Repeticoes");
		serie =  ConversorJson.getInteger(json, "Serie");
		parametros =  ConversorJson.getString(json, "Parametros");
		ordemExecucao =  ConversorJson.getInteger(json, "OrdemExecucao");
		idExercicioEd =  ConversorJson.getInteger(json, "IdExercicioEd");
		idSerieTreinoPa =  ConversorJson.getInteger(json, "IdSerieTreinoPa");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
  	}
  	public String toString() {
  		return "" + idItemSerie;
  	}
	private long idItemSerie;
	public long getIdItemSerie() {
		return idItemSerie;
	}
	public void setIdItemSerie(long _valor) {
		idItemSerie = _valor;
	}


	private long repeticoes;
	public long getRepeticoes() {
		return repeticoes;
	}
	public void setRepeticoes(long _valor) {
		repeticoes = _valor;
	}


	private long serie;
	public long getSerie() {
		return serie;
	}
	public void setSerie(long _valor) {
		serie = _valor;
	}


	private String parametros;
	public String getParametros() {
		return parametros;
	}
	public void setParametros(String _valor) {
		parametros = _valor;
	}


	private long ordemExecucao;
	public long getOrdemExecucao() {
		return ordemExecucao;
	}
	public void setOrdemExecucao(long _valor) {
		ordemExecucao = _valor;
	}

	
	private long idExercicioEd;
	public long getIdExercicioEd() {
		// relacionamento
		if (idExercicioEd==0 && this.getExercicio_ExecucaoDe()!=null) 
			return getExercicio_ExecucaoDe().getId();
		else
			return idExercicioEd;
	}
	public void setIdExercicioEd(long _valor) {
		idExercicioEd = _valor;
	}

	
	private long idSerieTreinoPa;
	public long getIdSerieTreinoPa() {
		// relacionamento
		if (idSerieTreinoPa==0 && this.getSerieTreino_PertencenteA()!=null) 
			return getSerieTreino_PertencenteA().getId();
		else
			return idSerieTreinoPa;
	}
	public void setIdSerieTreinoPa(long _valor) {
		idSerieTreinoPa = _valor;
	}

	
	private long idUsuarioS;
	public long getIdUsuarioS() {
		// relacionamento
		if (idUsuarioS==0 && this.getUsuario_Sincroniza()!=null) 
			return getUsuario_Sincroniza().getId();
		else
			return idUsuarioS;
	}
	public void setIdUsuarioS(long _valor) {
		idUsuarioS = _valor;
	}





	private String operacaoSinc = null;
	@Override
	public String getOperacaoSinc() {
		return operacaoSinc;
	}
	@Override
	public void setOperacaoSinc(String valor) {
		operacaoSinc = valor;
	}
	@Override
	public JSONObject JSonSinc() throws JSONException {
		JSONObject json = JSonSimples();
		json.put("OperacaoSinc", operacaoSinc);
		return json;
	}
	
	
	private boolean salvaPreliminar = false;
	@Override
	public void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	@Override
	public boolean getSalvaPreliminar() {
		return salvaPreliminar;
	}
	
	// ComChaveEstrangeira
  	
	public long getIdExercicioEdLazyLoader() {
		return idExercicioEd;
	} 
		
	public long getIdSerieTreinoPaLazyLoader() {
		return idSerieTreinoPa;
	} 
		
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
	
	
	private boolean somenteMemoria = true;
	@Override
	public boolean getSomenteMemoria() {
		return somenteMemoria;
	}
	@Override
	public void setSomenteMemoria(boolean dado) {
		somenteMemoria = dado;
	} 
}