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
import treinoacademia.modelo.agregado.DiaTreinoAgregado;
import treinoacademia.modelo.derivada.impl.DiaTreinoDerivada;
import treinoacademia.modelo.display.impl.DiaTreinoDisplay;
import br.com.digicom.modelo.DCIObjetoDominio;


public class DiaTreinoVo implements DiaTreino , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idDiaTreino;
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
  	
  
  	public DiaTreinoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdDiaTreino\" : \"" + idDiaTreino + "\" "
		+ ", \"Data\" : \"" + data + "\" "
		+ ", \"Concluido\" : \"" + concluido + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		+ ", \"IdSerieTreinoSd\" : \"" + idSerieTreinoSd + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdDiaTreino",idDiaTreino);
			json.put("Data",data);
			json.put("Concluido",concluido);
			json.put("IdUsuarioS",idUsuarioS);
			json.put("IdSerieTreinoSd",idSerieTreinoSd);
		
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
		JSONArray listaExecucaoItemSerie_FoiRealizado = JSonListaExecucaoItemSerie_FoiRealizado();
		if (listaExecucaoItemSerie_FoiRealizado!=null) {
			json.put("ListaExecucaoItemSerieVo_FoiRealizado",listaExecucaoItemSerie_FoiRealizado);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaExecucaoItemSerie_FoiRealizado() throws JSONException{
		if (getListaExecucaoItemSerie_FoiRealizado()==null) return null;
		JSONArray lista = new JSONArray();
		for (ExecucaoItemSerie item:this.getListaExecucaoItemSerie_FoiRealizadoOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdDiaTreino=" + getIdDiaTreino() 
		+  " Data=" + getData() 
		+  " Concluido=" + getConcluido() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		+  " IdSerieTreinoSd=" + getIdSerieTreinoSd() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idDiaTreino;
 	}
 	public String getNomeClasse() {
 		return "DiaTreino";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private DiaTreinoDisplay display = null;
	private DiaTreinoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new DiaTreinoDisplay(this);
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
	private DiaTreinoAgregado agregado = null;
	private DiaTreinoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new DiaTreinoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private DiaTreinoDerivada derivada = null;
	private DiaTreinoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new DiaTreinoDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	public Timestamp getHoraInicio()
	{
		return getDerivada().getHoraInicio(); 
	} 
	public void setHoraInicio(Timestamp value)
	{
		getDerivada().setHoraInicio(value); 
	} 
	
	public Timestamp getHoraFim()
	{
		return getDerivada().getHoraFim(); 
	} 
	public void setHoraFim(Timestamp value)
	{
		getDerivada().setHoraFim(value); 
	} 
	
	public String getDiaSemana()
	{
		return getDerivada().getDiaSemana(); 
	} 
	public void setDiaSemana(String value)
	{
		getDerivada().setDiaSemana(value); 
	} 
	
	public Timestamp getTempoConsumido()
	{
		return getDerivada().getTempoConsumido(); 
	} 
	public void setTempoConsumido(Timestamp value)
	{
		getDerivada().setTempoConsumido(value); 
	} 
	
	public List getListaExercicio()
	{
		return getDerivada().getListaExercicio(); 
	} 
	public void setListaExercicio(List value)
	{
		getDerivada().setListaExercicio(value); 
	} 
	
	public long getQuantidadeExecutado()
	{
		return getDerivada().getQuantidadeExecutado(); 
	} 
	public void setQuantidadeExecutado(long value)
	{
		getDerivada().setQuantidadeExecutado(value); 
	} 
	
	public long getQuantidadeTotal()
	{
		return getDerivada().getQuantidadeTotal(); 
	} 
	public void setQuantidadeTotal(long value)
	{
		getDerivada().setQuantidadeTotal(value); 
	} 
	
	
	// ----- FINAL DERIVADA -----------------
	
	
	
 
  
  
  	// ------ AGREGADOS 2-------------------
  	// ComChaveEstrangeira
  	
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
		
		
		public SerieTreino getSerieTreino_SerieDia() {
			return getAgregado().getSerieTreino_SerieDia();
		}
		public void setSerieTreino_SerieDia(SerieTreino item) {
			getAgregado().setSerieTreino_SerieDia(item);
		}
		
		public void addListaSerieTreino_SerieDia(SerieTreino item) {
			getAgregado().addListaSerieTreino_SerieDia(item);
		}
		public SerieTreino getCorrenteSerieTreino_SerieDia() {
			return getAgregado().getCorrenteSerieTreino_SerieDia();
		}
		
		
	
	// SemChaveEstrangeira
	
		public ExecucaoItemSerie getCorrenteExecucaoItemSerie_FoiRealizado() {
			return getAgregado().getCorrenteExecucaoItemSerie_FoiRealizado();
		}
		public void addListaExecucaoItemSerie_FoiRealizado(ExecucaoItemSerie item) {
			getAgregado().addListaExecucaoItemSerie_FoiRealizado(item);
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizado() {
			return getAgregado().getListaExecucaoItemSerie_FoiRealizado();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizadoOriginal() {
			return getAgregado().getListaExecucaoItemSerie_FoiRealizadoOriginal();
		}
		public List<ExecucaoItemSerie> getListaExecucaoItemSerie_FoiRealizado(int qtde) {
			return getAgregado().getListaExecucaoItemSerie_FoiRealizado(qtde);
		}
		public void setListaExecucaoItemSerie_FoiRealizado(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_FoiRealizado(lista);
		}
		public void setListaExecucaoItemSerie_FoiRealizadoByDao(List<ExecucaoItemSerie> lista) {
			getAgregado().setListaExecucaoItemSerie_FoiRealizadoByDao(lista);
		}
		public void criaVaziaListaExecucaoItemSerie_FoiRealizado() {
			getAgregado().criaVaziaListaExecucaoItemSerie_FoiRealizado();
		}
		
		public boolean existeListaExecucaoItemSerie_FoiRealizado() {
			return getAgregado().existeListaExecucaoItemSerie_FoiRealizado();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public DiaTreinoVo(JSONObject json) throws JSONException{
		idDiaTreino =  ConversorJson.getInteger(json, "IdDiaTreino");
		data =  ConversorJson.getTimestampData(json, "Data");
		concluido =  ConversorJson.getLogic(json, "Concluido");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
		idSerieTreinoSd =  ConversorJson.getInteger(json, "IdSerieTreinoSd");
  	}
  	public String toString() {
  		return "" + data;
  	}
	private long idDiaTreino;
	public long getIdDiaTreino() {
		return idDiaTreino;
	}
	public void setIdDiaTreino(long _valor) {
		idDiaTreino = _valor;
	}


	private Timestamp data;
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp _valor) {
		data = _valor;
	}


	public String getDataDDMMAAAA() {
		if (data==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(data);
    }
    public void setDataDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			data = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private boolean concluido;
	public boolean getConcluido() {
		return concluido;
	}
	public void setConcluido(boolean _valor) {
		concluido = _valor;
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

	
	private long idSerieTreinoSd;
	public long getIdSerieTreinoSd() {
		// relacionamento
		if (idSerieTreinoSd==0 && this.getSerieTreino_SerieDia()!=null) 
			return getSerieTreino_SerieDia().getId();
		else
			return idSerieTreinoSd;
	}
	public void setIdSerieTreinoSd(long _valor) {
		idSerieTreinoSd = _valor;
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
  	
	public long getIdUsuarioSLazyLoader() {
		return idUsuarioS;
	} 
		
	public long getIdSerieTreinoSdLazyLoader() {
		return idSerieTreinoSd;
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