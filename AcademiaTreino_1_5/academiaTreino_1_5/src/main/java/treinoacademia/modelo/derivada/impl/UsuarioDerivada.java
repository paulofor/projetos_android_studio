package treinoacademia.modelo.derivada.impl;

import  treinoacademia.modelo.derivada.*;
import  treinoacademia.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class UsuarioDerivada implements UsuarioDerivadaI{ 
  
  	private Usuario principal;
  
  	/*
  	public UsuarioDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public UsuarioDerivada() {
		super();
  	}
  	*/
  	
  	public UsuarioDerivada(Usuario item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em UsuarioDerivada";
	}
}