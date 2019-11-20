package coletapreco.modelo.derivada.impl;

import  coletapreco.modelo.derivada.*;
import  coletapreco.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class PalavraDerivada implements PalavraDerivadaI{ 
  
  	private Palavra principal;
  
  	/*
  	public PalavraDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public PalavraDerivada() {
		super();
  	}
  	*/
  	
  	public PalavraDerivada(Palavra item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em PalavraDerivada";
	}
}