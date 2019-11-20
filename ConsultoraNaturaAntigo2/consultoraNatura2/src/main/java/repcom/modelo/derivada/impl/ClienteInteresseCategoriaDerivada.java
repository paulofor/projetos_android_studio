package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ClienteInteresseCategoriaDerivada implements ClienteInteresseCategoriaDerivadaI{ 
  
  	private ClienteInteresseCategoria principal;
  
  	/*
  	public ClienteInteresseCategoriaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ClienteInteresseCategoriaDerivada() {
		super();
  	}
  	*/
  	
  	public ClienteInteresseCategoriaDerivada(ClienteInteresseCategoria item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ClienteInteresseCategoriaDerivada";
	}
}