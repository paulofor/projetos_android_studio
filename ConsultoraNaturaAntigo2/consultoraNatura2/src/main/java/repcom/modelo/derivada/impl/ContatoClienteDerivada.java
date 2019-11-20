package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ContatoClienteDerivada implements ContatoClienteDerivadaI{ 
  
  	private ContatoCliente principal;
  
  	/*
  	public ContatoClienteDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ContatoClienteDerivada() {
		super();
  	}
  	*/
  	
  	public ContatoClienteDerivada(ContatoCliente item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ContatoClienteDerivada";
	}
}