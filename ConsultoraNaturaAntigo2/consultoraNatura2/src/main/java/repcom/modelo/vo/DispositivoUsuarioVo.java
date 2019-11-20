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
import repcom.modelo.agregado.DispositivoUsuarioAgregado;
import repcom.modelo.derivada.impl.DispositivoUsuarioDerivada;
import repcom.modelo.display.impl.DispositivoUsuarioDisplay;

public class DispositivoUsuarioVo implements DispositivoUsuario , ObjetoSinc{ 
  
  
  	public long getIdObj()
    {
       return idDispositivoUsuario;
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
  	
  
  	public DispositivoUsuarioVo() {
  	}
   	// -----  Inicio JSON -----
  	private String JsonAtributosOld() {
		return 
		" \"IdDispositivoUsuario\" : \"" + idDispositivoUsuario + "\" "
		+ ", \"NumeroCelular\" : \"" + numeroCelular + "\" "
		+ ", \"CodigoDispositivo\" : \"" + codigoDispositivo + "\" "
		+ ", \"TipoAcesso\" : \"" + tipoAcesso + "\" "
		+ ", \"MelhorPath\" : \"" + melhorPath + "\" "
		+ ", \"IdUsuarioRa\" : \"" + idUsuarioRa + "\" "
		
	;
	}
	private JSONObject JSonAtributos() throws JSONException{
		JSONObject json = new JSONObject();
		//try {
			json.put("IdDispositivoUsuario",idDispositivoUsuario);
			json.put("NumeroCelular",numeroCelular);
			json.put("CodigoDispositivo",codigoDispositivo);
			json.put("TipoAcesso",tipoAcesso);
			json.put("MelhorPath",melhorPath);
			json.put("IdUsuarioRa",idUsuarioRa);
		
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
		 " IdDispositivoUsuario=" + getIdDispositivoUsuario() 
		+  " NumeroCelular=" + getNumeroCelular() 
		+  " CodigoDispositivo=" + getCodigoDispositivo() 
		+  " TipoAcesso=" + getTipoAcesso() 
		+  " MelhorPath=" + getMelhorPath() 
		+  " IdUsuarioRa=" + getIdUsuarioRa() 
		
	;
	}
 
 
 	// ---------  Metodos DCIObjeto ----------------
 	
 	public long getId() {
 		return idDispositivoUsuario;
 	}
 	public String getNomeClasse() {
 		return "DispositivoUsuario";
 	}
 	// ---------------------------------------------
 
 
    // ----- INICIO DISPLAY -----------------
	private DispositivoUsuarioDisplay display = null;
	private DispositivoUsuarioDisplay getObjetoDisplay() {
		if (display==null) {
			display = new DispositivoUsuarioDisplay(this);
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
	private DispositivoUsuarioAgregado agregado = null;
	private DispositivoUsuarioAgregado getAgregado() {
		if (agregado==null) {
			agregado = new DispositivoUsuarioAgregado(this);
		}
		return agregado;
	}
	// ----- FINAL AGREGADO -----------------
	
	// ----- INICIO DERIVADA -----------------
	private DispositivoUsuarioDerivada derivada = null;
	private DispositivoUsuarioDerivada getDerivada() {
		if (derivada==null) {
			derivada = new DispositivoUsuarioDerivada(this);
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
  	
		public Usuario getUsuario_ReferenteA() {
			return getAgregado().getUsuario_ReferenteA();
		}
		public void setUsuario_ReferenteA(Usuario item) {
			getAgregado().setUsuario_ReferenteA(item);
		}
		
		public void addListaUsuario_ReferenteA(Usuario item) {
			getAgregado().addListaUsuario_ReferenteA(item);
		}
		public Usuario getCorrenteUsuario_ReferenteA() {
			return getAgregado().getCorrenteUsuario_ReferenteA();
		}
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
  	
  	
  	// ------ FINAL AGREGADOS -------------
  
  
  	public DispositivoUsuarioVo(JSONObject json) throws JSONException{
		idDispositivoUsuario =  ConversorJson.getInteger(json, "IdDispositivoUsuario");
		numeroCelular =  ConversorJson.getString(json, "NumeroCelular");
		codigoDispositivo =  ConversorJson.getString(json, "CodigoDispositivo");
		tipoAcesso =  ConversorJson.getString(json, "TipoAcesso");
		melhorPath =  ConversorJson.getString(json, "MelhorPath");
		idUsuarioRa =  ConversorJson.getInteger(json, "IdUsuarioRa");
  	}
  	public String toString() {
  		return "" + numeroCelular;
  	}
	private long idDispositivoUsuario;
	public long getIdDispositivoUsuario() {
		return idDispositivoUsuario;
	}
	public void setIdDispositivoUsuario(long _valor) {
		idDispositivoUsuario = _valor;
	}


	private String numeroCelular;
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String _valor) {
		numeroCelular = _valor;
	}


	private String codigoDispositivo;
	public String getCodigoDispositivo() {
		return codigoDispositivo;
	}
	public void setCodigoDispositivo(String _valor) {
		codigoDispositivo = _valor;
	}


	private String tipoAcesso;
	public String getTipoAcesso() {
		return tipoAcesso;
	}
	public void setTipoAcesso(String _valor) {
		tipoAcesso = _valor;
	}


	private String melhorPath;
	public String getMelhorPath() {
		return melhorPath;
	}
	public void setMelhorPath(String _valor) {
		melhorPath = _valor;
	}

	
	private long idUsuarioRa;
	public long getIdUsuarioRa() {
		// relacionamento
		if (idUsuarioRa==0 && this.getUsuario_ReferenteA()!=null) 
			return getUsuario_ReferenteA().getId();
		else
			return idUsuarioRa;
	}
	public void setIdUsuarioRa(long _valor) {
		idUsuarioRa = _valor;
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
  	
	public long getIdUsuarioRaLazyLoader() {
		return idUsuarioRa;
	} 
		
}