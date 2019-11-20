package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ClienteDerivada implements ClienteDerivadaI{ 
  
  	private Cliente principal;
  
  	/*
  	public ClienteDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ClienteDerivada() {
		super();
  	}
  	*/
  	
  	public ClienteDerivada(Cliente item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ClienteDerivada";
	}

	@Override
	public void setTelefoneAgenda(String valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTelefoneAgenda() {
		// TODO Auto-generated method stub
		return null;
	}
}