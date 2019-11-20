package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class MesAnoDerivada implements MesAnoDerivadaI{ 
  
  	private MesAno principal;
  
  	/*
  	public MesAnoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public MesAnoDerivada() {
		super();
  	}
  	*/
  	
  	public MesAnoDerivada(MesAno item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em MesAnoDerivada";
	}
}