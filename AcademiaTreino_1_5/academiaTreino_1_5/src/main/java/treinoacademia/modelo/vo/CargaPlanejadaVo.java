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
import treinoacademia.modelo.agregado.CargaPlanejadaAgregado;
import treinoacademia.modelo.derivada.impl.CargaPlanejadaDerivada;
import treinoacademia.modelo.display.impl.CargaPlanejadaDisplay;
import br.com.digicom.modelo.DCIObjetoDominio;


public class CargaPlanejadaVo implements CargaPlanejada , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idCargaPlanejada;
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
  	
  
  	public CargaPlanejadaVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdCargaPlanejada\" : \"" + idCargaPlanejada + "\" "
		+ ", \"ValorCarga\" : \"" + valorCarga + "\" "
		+ ", \"DataInicio\" : \"" + dataInicio + "\" "
		+ ", \"DataFinal\" : \"" + dataFinal + "\" "
		+ ", \"Ativa\" : \"" + ativa + "\" "
		+ ", \"QuantidadeRepeticao\" : \"" + quantidadeRepeticao + "\" "
		+ ", \"OrdemRepeticao\" : \"" + ordemRepeticao + "\" "
		+ ", \"IdItemSerieRa\" : \"" + idItemSerieRa + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdCargaPlanejada",idCargaPlanejada);
			json.put("ValorCarga",valorCarga);
			json.put("DataInicio",dataInicio);
			json.put("DataFinal",dataFinal);
			json.put("Ativa",ativa);
			json.put("QuantidadeRepeticao",quantidadeRepeticao);
			json.put("OrdemRepeticao",ordemRepeticao);
			json.put("IdItemSerieRa",idItemSerieRa);
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
	
		//} catch (JSONException e) {
		//	DCLog.e("JSONTag", this, e);
		//}
		return json;
	}


	// SemChaveEstrangeira
	
  	// -----  Final JSon -----------
 
 
 	public String debug() {
		return 
		 " IdCargaPlanejada=" + getIdCargaPlanejada() 
		+  " ValorCarga=" + getValorCarga() 
		+  " DataInicio=" + getDataInicio() 
		+  " DataFinal=" + getDataFinal() 
		+  " Ativa=" + getAtiva() 
		+  " QuantidadeRepeticao=" + getQuantidadeRepeticao() 
		+  " OrdemRepeticao=" + getOrdemRepeticao() 
		+  " IdItemSerieRa=" + getIdItemSerieRa() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idCargaPlanejada;
 	}
 	public String getNomeClasse() {
 		return "CargaPlanejada";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private CargaPlanejadaDisplay display = null;
	private CargaPlanejadaDisplay getObjetoDisplay() {
		if (display==null) {
			display = new CargaPlanejadaDisplay(this);
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
	private CargaPlanejadaAgregado agregado = null;
	private CargaPlanejadaAgregado getAgregado() {
		if (agregado==null) {
			agregado = new CargaPlanejadaAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private CargaPlanejadaDerivada derivada = null;
	private CargaPlanejadaDerivada getDerivada() {
		if (derivada==null) {
			derivada = new CargaPlanejadaDerivada(this);
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
  	
		public ItemSerie getItemSerie_ReferenteA() {
			return getAgregado().getItemSerie_ReferenteA();
		}
		public void setItemSerie_ReferenteA(ItemSerie item) {
			getAgregado().setItemSerie_ReferenteA(item);
		}
		
		public void addListaItemSerie_ReferenteA(ItemSerie item) {
			getAgregado().addListaItemSerie_ReferenteA(item);
		}
		public ItemSerie getCorrenteItemSerie_ReferenteA() {
			return getAgregado().getCorrenteItemSerie_ReferenteA();
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
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public CargaPlanejadaVo(JSONObject json) throws JSONException{
		idCargaPlanejada =  ConversorJson.getInteger(json, "IdCargaPlanejada");
		valorCarga =  ConversorJson.getFloat(json, "ValorCarga");
		dataInicio =  ConversorJson.getTimestampData(json, "DataInicio");
		dataFinal =  ConversorJson.getTimestampData(json, "DataFinal");
		ativa =  ConversorJson.getLogic(json, "Ativa");
		quantidadeRepeticao =  ConversorJson.getInteger(json, "QuantidadeRepeticao");
		ordemRepeticao =  ConversorJson.getInteger(json, "OrdemRepeticao");
		idItemSerieRa =  ConversorJson.getInteger(json, "IdItemSerieRa");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
  	}
  	public String toString() {
  		return "" + valorCarga;
  	}
	private long idCargaPlanejada;
	public long getIdCargaPlanejada() {
		return idCargaPlanejada;
	}
	public void setIdCargaPlanejada(long _valor) {
		idCargaPlanejada = _valor;
	}


	private float valorCarga;
	public float getValorCarga() {
		return valorCarga;
	}
	public void setValorCarga(float _valor) {
		valorCarga = _valor;
	}
	
	public String getValorCargaTela() {
		return String.format("%.2f",valorCarga).replace(".", ",");
	}

	private Timestamp dataInicio;
	public Timestamp getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Timestamp _valor) {
		dataInicio = _valor;
	}


	public String getDataInicioDDMMAAAA() {
		if (dataInicio==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataInicio);
    }
    public void setDataInicioDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataInicio = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private Timestamp dataFinal;
	public Timestamp getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Timestamp _valor) {
		dataFinal = _valor;
	}


	public String getDataFinalDDMMAAAA() {
		if (dataFinal==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataFinal);
    }
    public void setDataFinalDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataFinal = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }




	private boolean ativa;
	public boolean getAtiva() {
		return ativa;
	}
	public void setAtiva(boolean _valor) {
		ativa = _valor;
	}


	private long quantidadeRepeticao;
	public long getQuantidadeRepeticao() {
		return quantidadeRepeticao;
	}
	public void setQuantidadeRepeticao(long _valor) {
		quantidadeRepeticao = _valor;
	}


	private long ordemRepeticao;
	public long getOrdemRepeticao() {
		return ordemRepeticao;
	}
	public void setOrdemRepeticao(long _valor) {
		ordemRepeticao = _valor;
	}

	
	private long idItemSerieRa;
	public long getIdItemSerieRa() {
		// relacionamento
		if (idItemSerieRa==0 && this.getItemSerie_ReferenteA()!=null) 
			return getItemSerie_ReferenteA().getId();
		else
			return idItemSerieRa;
	}
	public void setIdItemSerieRa(long _valor) {
		idItemSerieRa = _valor;
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
  	
	public long getIdItemSerieRaLazyLoader() {
		return idItemSerieRa;
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