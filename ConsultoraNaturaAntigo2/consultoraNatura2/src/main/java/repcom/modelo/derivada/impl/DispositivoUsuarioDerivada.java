package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class DispositivoUsuarioDerivada implements DispositivoUsuarioDerivadaI{ 
  
  	private DispositivoUsuario principal;
  
  	/*
  	public DispositivoUsuarioDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public DispositivoUsuarioDerivada() {
		super();
  	}
  	*/
  	
  	public DispositivoUsuarioDerivada(DispositivoUsuario item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em DispositivoUsuarioDerivada";
	}
}