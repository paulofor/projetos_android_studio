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
import repcom.modelo.agregado.ContatoClienteAgregado;
import repcom.modelo.derivada.impl.ContatoClienteDerivada;
import repcom.modelo.display.impl.ContatoClienteDisplay;

public class ContatoClienteVo implements ContatoCliente , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idContatoCliente;
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
  	
  
  	public ContatoClienteVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdContatoCliente\" : \"" + idContatoCliente + "\" "
		+ ", \"DataContato\" : \"" + dataContato + "\" "
		+ ", \"IdClienteRa\" : \"" + idClienteRa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdContatoCliente",idContatoCliente);
			json.put("DataContato",dataContato);
			json.put("IdClienteRa",idClienteRa);
		
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
		 " IdContatoCliente=" + getIdContatoCliente() 
		+  " DataContato=" + getDataContato() 
		+  " IdClienteRa=" + getIdClienteRa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idContatoCliente;
 	}
 	public String getNomeClasse() {
 		return "ContatoCliente";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private ContatoClienteDisplay display = null;
	private ContatoClienteDisplay getObjetoDisplay() {
		if (display==null) {
			display = new ContatoClienteDisplay(this);
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
	private ContatoClienteAgregado agregado = null;
	private ContatoClienteAgregado getAgregado() {
		if (agregado==null) {
			agregado = new ContatoClienteAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private ContatoClienteDerivada derivada = null;
	private ContatoClienteDerivada getDerivada() {
		if (derivada==null) {
			derivada = new ContatoClienteDerivada(this);
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
  	
		public Cliente getCliente_ReferenteA() {
			return getAgregado().getCliente_ReferenteA();
		}
		public void setCliente_ReferenteA(Cliente item) {
			getAgregado().setCliente_ReferenteA(item);
		}
		
		public void addListaCliente_ReferenteA(Cliente item) {
			getAgregado().addListaCliente_ReferenteA(item);
		}
		public Cliente getCorrenteCliente_ReferenteA() {
			return getAgregado().getCorrenteCliente_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public ContatoClienteVo(JSONObject json) throws JSONException{
		idContatoCliente =  ConversorJson.getInteger(json, "IdContatoCliente");
		dataContato =  ConversorJson.getTimestampData(json, "DataContato");
		idClienteRa =  ConversorJson.getInteger(json, "IdClienteRa");
  	}
  	public String toString() {
  		return "" + dataContato;
  	}
	private long idContatoCliente;
	public long getIdContatoCliente() {
		return idContatoCliente;
	}
	public void setIdContatoCliente(long _valor) {
		idContatoCliente = _valor;
	}


	private Timestamp dataContato;
	public Timestamp getDataContato() {
		return dataContato;
	}
	public void setDataContato(Timestamp _valor) {
		dataContato = _valor;
	}


	public String getDataContatoDDMMAAAA() {
		if (dataContato==null) return null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataContato);
    }
    public void setDataContatoDDMMAAAAComBarra(String arg) {
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = formatter.parse(arg);
			dataContato = new Timestamp(date.getTime());
		} catch (ParseException e) {
			DCLog.e(DCLog.MODELO, this, e);
		}
    }



	
	private long idClienteRa;
	public long getIdClienteRa() {
		// relacionamento
		if (idClienteRa==0 && this.getCliente_ReferenteA()!=null) 
			return getCliente_ReferenteA().getId();
		else
			return idClienteRa;
	}
	public void setIdClienteRa(long _valor) {
		idClienteRa = _valor;
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
  	
	public long getIdClienteRaLazyLoader() {
		return idClienteRa;
	} 
		
}