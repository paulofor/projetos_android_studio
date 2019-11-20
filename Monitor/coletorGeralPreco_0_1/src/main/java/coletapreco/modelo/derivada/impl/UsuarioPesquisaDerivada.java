package coletapreco.modelo.derivada.impl;

import  coletapreco.modelo.derivada.*;
import  coletapreco.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class UsuarioPesquisaDerivada implements UsuarioPesquisaDerivadaI{ 
  
  	private UsuarioPesquisa principal;
  
  	/*
  	public UsuarioPesquisaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public UsuarioPesquisaDerivada() {
		super();
  	}
  	*/
  	
  	public UsuarioPesquisaDerivada(UsuarioPesquisa item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em UsuarioPesquisaDerivada";
	}
}