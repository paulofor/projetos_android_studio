package coletapreco.modelo.derivada.impl;

import  coletapreco.modelo.derivada.*;
import  coletapreco.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class UsuarioNaturezaDerivada implements UsuarioNaturezaDerivadaI{ 
  
  	private UsuarioNatureza principal;
  
  	/*
  	public UsuarioNaturezaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public UsuarioNaturezaDerivada() {
		super();
  	}
  	*/
  	
  	public UsuarioNaturezaDerivada(UsuarioNatureza item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em UsuarioNaturezaDerivada";
	}
}