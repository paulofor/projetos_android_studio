package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class VendaDerivada implements VendaDerivadaI{ 
  
  	private Venda principal;
  
  	/*
  	public VendaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public VendaDerivada() {
		super();
  	}
  	*/
  	
  	public VendaDerivada(Venda item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em VendaDerivada";
	}
}