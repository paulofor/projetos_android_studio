package repcom.modelo.vo;

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
import repcom.modelo.*;
import repcom.modelo.agregado.MesAnoAgregado;
import repcom.modelo.derivada.impl.MesAnoDerivada;
import repcom.modelo.display.impl.MesAnoDisplay;

public class MesAnoVo implements MesAno , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idMesAno;
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
  	
  
  	public MesAnoVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdMesAno\" : \"" + idMesAno + "\" "
		+ ", \"Mes\" : \"" + mes + "\" "
		+ ", \"Ano\" : \"" + ano + "\" "
		+ ", \"DescricaoTela\" : \"" + descricaoTela + "\" "
		+ ", \"DataReferencia\" : \"" + dataReferencia + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdMesAno",idMesAno);
			json.put("Mes",mes);
			json.put("Ano",ano);
			json.put("DescricaoTela",descricaoTela);
			json.put("DataReferencia",dataReferencia);
		
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
		 " IdMesAno=" + getIdMesAno() 
		+  " Mes=" + getMes() 
		+  " Ano=" + getAno() 
		+  " DescricaoTela=" + getDescricaoTela() 
		+  " DataReferencia=" + getDataReferencia() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idMesAno;
 	}
 	public String getNomeClasse() {
 		return "MesAno";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private MesAnoDisplay display = null;
	private MesAnoDisplay getObjetoDisplay() {
		if (display==null) {
			display = new MesAnoDisplay(this);
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
	private MesAnoAgregado agregado = null;
	private MesAnoAgregado getAgregado() {
		if (agregado==null) {
			agregado = new MesAnoAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private MesAnoDerivada derivada = null;
	private MesAnoDerivada getDerivada() {
		if (derivada==null) {
			derivada = new MesAnoDerivada(this);
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
  	
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public MesAnoVo(JSONObject json) throws JSONException{
		idMesAno =  ConversorJson.getInteger(json, "IdMesAno");
		mes =  ConversorJson.getInteger(json, "Mes");
		ano =  ConversorJson.getInteger(json, "Ano");
		descricaoTela =  ConversorJson.getString(json, "DescricaoTela");
		dataReferencia =  ConversorJson.getTimestampData(json, "DataReferencia");
  	}
  	public String toString() {
  		return "" + mes;
  	}
	private long idMesAno;
	public long getIdMesAno() {
		return idMesAno;
	}
	public void setIdMesAno(long _valor) {
		idMesAno = _valor;
	}


	private long mes;
	public long getMes() {
		return mes;
	}
	public void setMes(long _valor) {
		mes = _valor;
	}


	private long ano;
	public long getAno() {
		return ano;
	}
	public void setAno(long _valor) {
		ano = _valor;
	}


	private String descricaoTela;
	public String getDescricaoTela() {
		return descricaoTela;
	}
	public void setDescricaoTela(String _valor) {
		descricaoTela = _valor;
	}


	private Timestamp dataReferencia;
	public Timestamp getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(Timestamp _valor) {
		dataReferencia = _valor;
	}


	public String getDataReferenciaDDMMAAAA() {
		if (dataReferencia==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataReferencia);
    }
    public void setDataReferenciaDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataReferencia = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
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
  	
}