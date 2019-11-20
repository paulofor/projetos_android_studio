package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class EstoqueDerivada implements EstoqueDerivadaI{ 
  
  	private Estoque principal;
  
  	/*
  	public EstoqueDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public EstoqueDerivada() {
		super();
  	}
  	*/
  	
  	public EstoqueDerivada(Estoque item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em EstoqueDerivada";
	}
}