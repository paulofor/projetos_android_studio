package treinoacademia.modelo.derivada.impl;

import  treinoacademia.modelo.derivada.*;
import  treinoacademia.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class CargaPlanejadaDerivada implements CargaPlanejadaDerivadaI{ 
  
  	private CargaPlanejada principal;
  
  	/*
  	public CargaPlanejadaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public CargaPlanejadaDerivada() {
		super();
  	}
  	*/
  	
  	public CargaPlanejadaDerivada(CargaPlanejada item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em CargaPlanejadaDerivada";
	}
}