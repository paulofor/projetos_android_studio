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
import treinoacademia.modelo.agregado.ExercicioAgregado;
import treinoacademia.modelo.derivada.impl.ExercicioDerivada;
import treinoacademia.modelo.display.impl.ExercicioDisplay;
import br.com.digicom.modelo.DCIObjetoDominio;


public class ExercicioVo implements Exercicio , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idExercicio;
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
  	
  
  	public ExercicioVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdExercicio\" : \"" + idExercicio + "\" "
		+ ", \"DescricaoExercicio\" : \"" + descricaoExercicio + "\" "
		+ ", \"Imagem\" : \"" + imagem + "\" "
		+ ", \"Titulo\" : \"" + titulo + "\" "
		+ ", \"Subtitulo\" : \"" + subtitulo + "\" "
		+ ", \"IdGrupoMuscularP\" : \"" + idGrupoMuscularP + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdExercicio",idExercicio);
			json.put("DescricaoExercicio",descricaoExercicio);
			json.put("Imagem",imagem);
			json.put("Titulo",titulo);
			json.put("Subtitulo",subtitulo);
			json.put("IdGrupoMuscularP",idGrupoMuscularP);
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
		JSONArray listaItemSerie_Gera = JSonListaItemSerie_Gera();
		if (listaItemSerie_Gera!=null) {
			json.put("ListaItemSerieVo_Gera",listaItemSerie_Gera);
		} 
		JSONArray listaExecucaoItemSerie_Executado = JSonListaExecucaoItemSerie_Executado();
		if (listaExecucaoItemSerie_Executado!=null) {
			json.put("ListaExecucaoItemSerieVo_Executado",listaExecucaoItemSerie_Executado);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaItemSerie_Gera() throws JSONException{
		if (getListaItemSerie_Gera()==null) return null;
		JSONArray lista = new JSONArray();
		for (ItemSerie item:this.getListaItemSerie_GeraOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaExecucaoItemSerie_Executado() throws JSONException{
		if (getListaExecucaoItemSerie_Executado()==null) return null;
		JSONArray lista = new JSONArray();
		for (ExecucaoItemSerie item:this.getListaExecucaoItemSerie_ExecutadoOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdExercicio=" + getIdExercicio() 
		+  " DescricaoExercicio=" + getDescricaoExercicio() 
		+  " Imagem=" + getImagem() 
		+  " Titulo=" + getTitulo() 
		+  " Subtitulo=" + getSubtitulo() 
		+  " IdGrupoMuscularP=" + getIdGrupoMuscularP() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idExercicio;
 	}
 	public String getNomeClasse() {
 		return "Exercicio";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ExercicioDisplay display = null;
	private ExercicioDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ExercicioDisplay(this);
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
	private ExercicioAgregado agregado = null;
	private ExercicioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ExercicioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ExercicioDerivada derivada = null;
	private ExercicioDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ExercicioDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	
	// ----- FINAL DERIVADA -----------------
	
	
	
 
  
  
  	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
		public GrupoMuscular getGrupoMuscular_Para() {
			return getAgregado().getGrupoMuscular_Para();
		}
		public void setGrupoMuscular_Para(GrupoMuscular item) {
			getAgregado().setGrupoMuscular_Para(item);
		}
		
		public void addListaGrupoMuscular_Para(GrupoMuscular item) {
			getAgregado().addListaGrupoMuscular_Para(item);
		}
		public GrupoMuscular getCorrenteGrupoMuscular_Para() {
			return getAgregado().getCorrenteGrupoMuscular_Para();
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
	
		public ItemSerie getCorrenteItemSerie_Gera() {
			return getAgregado().getCorrenteItemSerie_Gera();
		}
		public void addListaItemSerie_Gera(ItemSerie item) {
			getAgregado().addListaItemSerie_Gera(item);
		}
		public List<ItemSerie> getListaItemSerie_Gera() {
			return getAgregado().getListaItemSerie_Gera();
		}
		public List<ItemSerie> getListaItemSerie_GeraOriginal() {
			return getAgregado().getListaItemSerie_GeraOriginal();
		}
		public List<ItemSerie> getListaItemSerie_Gera(int qtde) {
			return getAgregado().getListaItemSerie_Gera(qtde);
		}
		public void setListaItemSerie_Gera(List<ItemSerie> lista) {
			getAgregado().setListaItemSerie_Gera(lista);
		}
		public void setListaItemSerie_GeraByDao(List<ItemSerie> lista) {
			getAgregado().setListaItemSerie_GeraByDao(lista);
		}
		public void criaVaziaListaItemSerie_Gera() {
			getAgregado().criaVaziaListaItemSerie_Gera();
		}
		
		public boolean existeListaItemSerie_Gera() {
			return getAgregado().existeListaItemSerie_Gera();
		}
 		
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_Executado() {
			return getAgregado().getCorrenteExecucaoItemSerie_Executado();
		}
		public void addListaExecucaoItemSerie_Executado(ExecucaoItemSerie item) {
			getAgregado().addListaExecucaoItemSerie_Executado(item);
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Executado() {
			return getAgregado().getListaExecucaoItemSerie_Executado();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_ExecutadoOriginal() {
			return getAgregado().getListaExecucaoItemSerie_ExecutadoOriginal();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_Executado(int qtde) {
			return getAgregado().getListaExecucaoItemSerie_Executado(qtde);
		}
		public void setListaExecucaoItemSerie_Executado(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_Executado(lista);
		}
		public void setListaExecucaoItemSerie_ExecutadoByDao(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_ExecutadoByDao(lista);
		}
		public void criaVaziaListaExecucaoItemSerie_Executado() {
			getAgregado().criaVaziaListaExecucaoItemSerie_Executado();
		}
		
		public boolean existeListaExecucaoItemSerie_Executado() {
			return getAgregado().existeListaExecucaoItemSerie_Executado();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ExercicioVo(JSONObject json) throws JSONException{
		idExercicio =  ConversorJson.getInteger(json, "IdExercicio");
		descricaoExercicio =  ConversorJson.getString(json, "DescricaoExercicio");
		imagem =  ConversorJson.getString(json, "Imagem");
		titulo =  ConversorJson.getString(json, "Titulo");
		subtitulo =  ConversorJson.getString(json, "Subtitulo");
		idGrupoMuscularP =  ConversorJson.getInteger(json, "IdGrupoMuscularP");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
  	}
  	public String toString() {
  		return "" + titulo;
  	}
	private long idExercicio;
	public long getIdExercicio() {
		return idExercicio;
	}
	public void setIdExercicio(long _valor) {
		idExercicio = _valor;
	}


	private String descricaoExercicio;
	public String getDescricaoExercicio() {
		return descricaoExercicio;
	}
	public void setDescricaoExercicio(String _valor) {
		descricaoExercicio = _valor;
	}


	private String imagem;
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String _valor) {
		imagem = _valor;
	}


	private String titulo;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String _valor) {
		titulo = _valor;
	}


	private String subtitulo;
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String _valor) {
		subtitulo = _valor;
	}

	
	private long idGrupoMuscularP;
	public long getIdGrupoMuscularP() {
		// relacionamento
		if (idGrupoMuscularP==0 && this.getGrupoMuscular_Para()!=null) 
			return getGrupoMuscular_Para().getId();
		else
			return idGrupoMuscularP;
	}
	public void setIdGrupoMuscularP(long _valor) {
		idGrupoMuscularP = _valor;
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
  	
	public long getIdGrupoMuscularPLazyLoader() {
		return idGrupoMuscularP;
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