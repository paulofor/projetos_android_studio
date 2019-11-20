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
import treinoacademia.modelo.agregado.SerieTreinoAgregado;
import treinoacademia.modelo.derivada.impl.SerieTreinoDerivada;
import treinoacademia.modelo.display.impl.SerieTreinoDisplay;
import br.com.digicom.modelo.DCIObjetoDominio;


public class SerieTreinoVo implements SerieTreino , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idSerieTreino;
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
  	
  
  	public SerieTreinoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdSerieTreino\" : \"" + idSerieTreino + "\" "
		+ ", \"QtdeExecucao\" : \"" + qtdeExecucao + "\" "
		+ ", \"Ativa\" : \"" + ativa + "\" "
		+ ", \"DataCriacao\" : \"" + dataCriacao + "\" "
		+ ", \"DataUltimaExecucao\" : \"" + dataUltimaExecucao + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdSerieTreino",idSerieTreino);
			json.put("QtdeExecucao",qtdeExecucao);
			json.put("Ativa",ativa);
			json.put("DataCriacao",dataCriacao);
			json.put("DataUltimaExecucao",dataUltimaExecucao);
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
		JSONArray listaItemSerie_Possui = JSonListaItemSerie_Possui();
		if (listaItemSerie_Possui!=null) {
			json.put("ListaItemSerieVo_Possui",listaItemSerie_Possui);
		} 
		JSONArray listaDiaTreino_SerieDia = JSonListaDiaTreino_SerieDia();
		if (listaDiaTreino_SerieDia!=null) {
			json.put("ListaDiaTreinoVo_SerieDia",listaDiaTreino_SerieDia);
		} 
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
	private JSONArray JSonListaItemSerie_Possui() throws JSONException{
		if (getListaItemSerie_Possui()==null) return null;
		JSONArray lista = new JSONArray();
		for (ItemSerie item:this.getListaItemSerie_PossuiOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
	private JSONArray JSonListaDiaTreino_SerieDia() throws JSONException{
		if (getListaDiaTreino_SerieDia()==null) return null;
		JSONArray lista = new JSONArray();
		for (DiaTreino item:this.getListaDiaTreino_SerieDiaOriginal()) {
			lista.put(((ObjetoSinc)item).JSonSinc());
			//lista.put(item.JSon());
		}
		return lista;
	}
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdSerieTreino=" + getIdSerieTreino() 
		+  " QtdeExecucao=" + getQtdeExecucao() 
		+  " Ativa=" + getAtiva() 
		+  " DataCriacao=" + getDataCriacao() 
		+  " DataUltimaExecucao=" + getDataUltimaExecucao() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idSerieTreino;
 	}
 	public String getNomeClasse() {
 		return "SerieTreino";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private SerieTreinoDisplay display = null;
	private SerieTreinoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new SerieTreinoDisplay(this);
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
	private SerieTreinoAgregado agregado = null;
	private SerieTreinoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new SerieTreinoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private SerieTreinoDerivada derivada = null;
	private SerieTreinoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new SerieTreinoDerivada(this);
		}
		return derivada;
	}
	public String getTituloTela()
	{
		return getDerivada().getTituloTela(); 
	} 
	
	public Timestamp getDataPrimeiraExecucao()
	{
		return getDerivada().getDataPrimeiraExecucao(); 
	} 
	public void setDataPrimeiraExecucao(Timestamp value)
	{
		getDerivada().setDataPrimeiraExecucao(value); 
	} 
	
	public Timestamp getTempoMedio()
	{
		return getDerivada().getTempoMedio(); 
	} 
	public void setTempoMedio(Timestamp value)
	{
		getDerivada().setTempoMedio(value); 
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
		
		
	
	// SemChaveEstrangeira
	
		public ItemSerie getCorrenteItemSerie_Possui() {
			return getAgregado().getCorrenteItemSerie_Possui();
		}
		public void addListaItemSerie_Possui(ItemSerie item) {
			getAgregado().addListaItemSerie_Possui(item);
		}
		public List<ItemSerie> getListaItemSerie_Possui() {
			return getAgregado().getListaItemSerie_Possui();
		}
		public List<ItemSerie> getListaItemSerie_PossuiOriginal() {
			return getAgregado().getListaItemSerie_PossuiOriginal();
		}
		public List<ItemSerie> getListaItemSerie_Possui(int qtde) {
			return getAgregado().getListaItemSerie_Possui(qtde);
		}
		public void setListaItemSerie_Possui(List<ItemSerie> lista) {
			getAgregado().setListaItemSerie_Possui(lista);
		}
		public void setListaItemSerie_PossuiByDao(List<ItemSerie> lista) {
			getAgregado().setListaItemSerie_PossuiByDao(lista);
		}
		public void criaVaziaListaItemSerie_Possui() {
			getAgregado().criaVaziaListaItemSerie_Possui();
		}
		
		public boolean existeListaItemSerie_Possui() {
			return getAgregado().existeListaItemSerie_Possui();
		}
 		
		public DiaTreino getCorrenteDiaTreino_SerieDia() {
			return getAgregado().getCorrenteDiaTreino_SerieDia();
		}
		public void addListaDiaTreino_SerieDia(DiaTreino item) {
			getAgregado().addListaDiaTreino_SerieDia(item);
		}
		public List<DiaTreino> getListaDiaTreino_SerieDia() {
			return getAgregado().getListaDiaTreino_SerieDia();
		}
		public List<DiaTreino> getListaDiaTreino_SerieDiaOriginal() {
			return getAgregado().getListaDiaTreino_SerieDiaOriginal();
		}
		public List<DiaTreino> getListaDiaTreino_SerieDia(int qtde) {
			return getAgregado().getListaDiaTreino_SerieDia(qtde);
		}
		public void setListaDiaTreino_SerieDia(List<DiaTreino> lista) {
			getAgregado().setListaDiaTreino_SerieDia(lista);
		}
		public void setListaDiaTreino_SerieDiaByDao(List<DiaTreino> lista) {
			getAgregado().setListaDiaTreino_SerieDiaByDao(lista);
		}
		public void criaVaziaListaDiaTreino_SerieDia() {
			getAgregado().criaVaziaListaDiaTreino_SerieDia();
		}
		
		public boolean existeListaDiaTreino_SerieDia() {
			return getAgregado().existeListaDiaTreino_SerieDia();
		}
 		
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public SerieTreinoVo(JSONObject json) throws JSONException{
		idSerieTreino =  ConversorJson.getInteger(json, "IdSerieTreino");
		qtdeExecucao =  ConversorJson.getInteger(json, "QtdeExecucao");
		ativa =  ConversorJson.getLogic(json, "Ativa");
		dataCriacao =  ConversorJson.getTimestampData(json, "DataCriacao");
		dataUltimaExecucao =  ConversorJson.getTimestampData(json, "DataUltimaExecucao");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
  	}
  	public String toString() {
  		return "" + idSerieTreino;
  	}
	private long idSerieTreino;
	public long getIdSerieTreino() {
		return idSerieTreino;
	}
	public void setIdSerieTreino(long _valor) {
		idSerieTreino = _valor;
	}


	private long qtdeExecucao;
	public long getQtdeExecucao() {
		return qtdeExecucao;
	}
	public void setQtdeExecucao(long _valor) {
		qtdeExecucao = _valor;
	}


	private boolean ativa;
	public boolean getAtiva() {
		return ativa;
	}
	public void setAtiva(boolean _valor) {
		ativa = _valor;
	}


	private Timestamp dataCriacao;
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Timestamp _valor) {
		dataCriacao = _valor;
	}


	public String getDataCriacaoDDMMAAAA() {
		if (dataCriacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataCriacao);
    }
    public void setDataCriacaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataCriacao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private Timestamp dataUltimaExecucao;
	public Timestamp getDataUltimaExecucao() {
		return dataUltimaExecucao;
	}
	public void setDataUltimaExecucao(Timestamp _valor) {
		dataUltimaExecucao = _valor;
	}


	public String getDataUltimaExecucaoDDMMAAAA() {
		if (dataUltimaExecucao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataUltimaExecucao);
    }
    public void setDataUltimaExecucaoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataUltimaExecucao = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
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