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
import treinoacademia.modelo.agregado.ExecucaoItemSerieAgregado;
import treinoacademia.modelo.derivada.impl.ExecucaoItemSerieDerivada;
import treinoacademia.modelo.display.impl.ExecucaoItemSerieDisplay;
import br.com.digicom.modelo.DCIObjetoDominio;


public class ExecucaoItemSerieVo implements ExecucaoItemSerie , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idExecucaoItemSerie;
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
  	
  
  	public ExecucaoItemSerieVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdExecucaoItemSerie\" : \"" + idExecucaoItemSerie + "\" "
		+ ", \"DataHoraInicio\" : \"" + dataHoraInicio + "\" "
		+ ", \"CargaUtilizada\" : \"" + cargaUtilizada + "\" "
		+ ", \"SucessoRepeticoes\" : \"" + sucessoRepeticoes + "\" "
		+ ", \"NumeroSerie\" : \"" + numeroSerie + "\" "
		+ ", \"DataHoraFinalizacao\" : \"" + dataHoraFinalizacao + "\" "
		+ ", \"QuantidadeRepeticao\" : \"" + quantidadeRepeticao + "\" "
		+ ", \"IdItemSerieRa\" : \"" + idItemSerieRa + "\" "
		+ ", \"IdDiaTreinoE\" : \"" + idDiaTreinoE + "\" "
		+ ", \"IdExercicioRa\" : \"" + idExercicioRa + "\" "
		+ ", \"IdUsuarioS\" : \"" + idUsuarioS + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdExecucaoItemSerie",idExecucaoItemSerie);
			json.put("DataHoraInicio",dataHoraInicio);
			json.put("CargaUtilizada",cargaUtilizada);
			json.put("SucessoRepeticoes",sucessoRepeticoes);
			json.put("NumeroSerie",numeroSerie);
			json.put("DataHoraFinalizacao",dataHoraFinalizacao);
			json.put("QuantidadeRepeticao",quantidadeRepeticao);
			json.put("IdItemSerieRa",idItemSerieRa);
			json.put("IdDiaTreinoE",idDiaTreinoE);
			json.put("IdExercicioRa",idExercicioRa);
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
		 " IdExecucaoItemSerie=" + getIdExecucaoItemSerie() 
		+  " DataHoraInicio=" + getDataHoraInicio() 
		+  " CargaUtilizada=" + getCargaUtilizada() 
		+  " SucessoRepeticoes=" + getSucessoRepeticoes() 
		+  " NumeroSerie=" + getNumeroSerie() 
		+  " DataHoraFinalizacao=" + getDataHoraFinalizacao() 
		+  " QuantidadeRepeticao=" + getQuantidadeRepeticao() 
		+  " IdItemSerieRa=" + getIdItemSerieRa() 
		+  " IdDiaTreinoE=" + getIdDiaTreinoE() 
		+  " IdExercicioRa=" + getIdExercicioRa() 
		+  " IdUsuarioS=" + getIdUsuarioS() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idExecucaoItemSerie;
 	}
 	public String getNomeClasse() {
 		return "ExecucaoItemSerie";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ExecucaoItemSerieDisplay display = null;
	private ExecucaoItemSerieDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ExecucaoItemSerieDisplay(this);
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
	private ExecucaoItemSerieAgregado agregado = null;
	private ExecucaoItemSerieAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ExecucaoItemSerieAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ExecucaoItemSerieDerivada derivada = null;
	private ExecucaoItemSerieDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ExecucaoItemSerieDerivada(this);
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
		
		
		public DiaTreino getDiaTreino_Em() {
			return getAgregado().getDiaTreino_Em();
		}
		public void setDiaTreino_Em(DiaTreino item) {
			getAgregado().setDiaTreino_Em(item);
		}
		
		public void addListaDiaTreino_Em(DiaTreino item) {
			getAgregado().addListaDiaTreino_Em(item);
		}
		public DiaTreino getCorrenteDiaTreino_Em() {
			return getAgregado().getCorrenteDiaTreino_Em();
		}
		
		
		public Exercicio getExercicio_ReferenteA() {
			return getAgregado().getExercicio_ReferenteA();
		}
		public void setExercicio_ReferenteA(Exercicio item) {
			getAgregado().setExercicio_ReferenteA(item);
		}
		
		public void addListaExercicio_ReferenteA(Exercicio item) {
			getAgregado().addListaExercicio_ReferenteA(item);
		}
		public Exercicio getCorrenteExercicio_ReferenteA() {
			return getAgregado().getCorrenteExercicio_ReferenteA();
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
  
  
  	public ExecucaoItemSerieVo(JSONObject json) throws JSONException{
		idExecucaoItemSerie =  ConversorJson.getInteger(json, "IdExecucaoItemSerie");
		dataHoraInicio =  ConversorJson.getTimestampDataHora(json, "DataHoraInicio");
		cargaUtilizada =  ConversorJson.getFloat(json, "CargaUtilizada");
		sucessoRepeticoes =  ConversorJson.getLogic(json, "SucessoRepeticoes");
		numeroSerie =  ConversorJson.getInteger(json, "NumeroSerie");
		dataHoraFinalizacao =  ConversorJson.getTimestampDataHora(json, "DataHoraFinalizacao");
		quantidadeRepeticao =  ConversorJson.getInteger(json, "QuantidadeRepeticao");
		idItemSerieRa =  ConversorJson.getInteger(json, "IdItemSerieRa");
		idDiaTreinoE =  ConversorJson.getInteger(json, "IdDiaTreinoE");
		idExercicioRa =  ConversorJson.getInteger(json, "IdExercicioRa");
		idUsuarioS =  ConversorJson.getInteger(json, "IdUsuarioS");
  	}
  	public String toString() {
  		return "" + cargaUtilizada;
  	}
	private long idExecucaoItemSerie;
	public long getIdExecucaoItemSerie() {
		return idExecucaoItemSerie;
	}
	public void setIdExecucaoItemSerie(long _valor) {
		idExecucaoItemSerie = _valor;
	}


	private Timestamp dataHoraInicio;
	public Timestamp getDataHoraInicio() {
		return dataHoraInicio;
	}
	public void setDataHoraInicio(Timestamp _valor) {
		dataHoraInicio = _valor;
	}


	public String getDataHoraInicioDDMMAAAA() {
		if (dataHoraInicio==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataHoraInicio);
    }




	public String getDataHoraInicioHHMM() {
		if (dataHoraInicio==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataHoraInicio);
    }
    public String getDataHoraInicioHHMMSS() {
		if (dataHoraInicio==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataHoraInicio);
    }
	private float cargaUtilizada;
	public float getCargaUtilizada() {
		return cargaUtilizada;
	}
	public void setCargaUtilizada(float _valor) {
		cargaUtilizada = _valor;
	}
	
	public String getCargaUtilizadaTela() {
		return String.format("%.2f",cargaUtilizada).replace(".", ",");
	}

	private boolean sucessoRepeticoes;
	public boolean getSucessoRepeticoes() {
		return sucessoRepeticoes;
	}
	public void setSucessoRepeticoes(boolean _valor) {
		sucessoRepeticoes = _valor;
	}


	private long numeroSerie;
	public long getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(long _valor) {
		numeroSerie = _valor;
	}


	private Timestamp dataHoraFinalizacao;
	public Timestamp getDataHoraFinalizacao() {
		return dataHoraFinalizacao;
	}
	public void setDataHoraFinalizacao(Timestamp _valor) {
		dataHoraFinalizacao = _valor;
	}


	public String getDataHoraFinalizacaoDDMMAAAA() {
		if (dataHoraFinalizacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataHoraFinalizacao);
    }




	public String getDataHoraFinalizacaoHHMM() {
		if (dataHoraFinalizacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        return formato.format(dataHoraFinalizacao);
    }
    public String getDataHoraFinalizacaoHHMMSS() {
		if (dataHoraFinalizacao==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(dataHoraFinalizacao);
    }
	private long quantidadeRepeticao;
	public long getQuantidadeRepeticao() {
		return quantidadeRepeticao;
	}
	public void setQuantidadeRepeticao(long _valor) {
		quantidadeRepeticao = _valor;
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

	
	private long idDiaTreinoE;
	public long getIdDiaTreinoE() {
		// relacionamento
		if (idDiaTreinoE==0 && this.getDiaTreino_Em()!=null) 
			return getDiaTreino_Em().getId();
		else
			return idDiaTreinoE;
	}
	public void setIdDiaTreinoE(long _valor) {
		idDiaTreinoE = _valor;
	}

	
	private long idExercicioRa;
	public long getIdExercicioRa() {
		// relacionamento
		if (idExercicioRa==0 && this.getExercicio_ReferenteA()!=null) 
			return getExercicio_ReferenteA().getId();
		else
			return idExercicioRa;
	}
	public void setIdExercicioRa(long _valor) {
		idExercicioRa = _valor;
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
		
	public long getIdDiaTreinoELazyLoader() {
		return idDiaTreinoE;
	} 
		
	public long getIdExercicioRaLazyLoader() {
		return idExercicioRa;
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