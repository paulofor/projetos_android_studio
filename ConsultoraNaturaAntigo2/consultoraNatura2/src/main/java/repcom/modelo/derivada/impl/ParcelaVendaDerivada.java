package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ParcelaVendaDerivada implements ParcelaVendaDerivadaI{ 
  
  	private ParcelaVenda principal;
  
  	/*
  	public ParcelaVendaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ParcelaVendaDerivada() {
		super();
  	}
  	*/
  	
  	public ParcelaVendaDerivada(ParcelaVenda item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ParcelaVendaDerivada";
	}
}