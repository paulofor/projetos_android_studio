package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.Usuario;
import coletapreco.modelo.derivada.UsuarioDerivadaI;

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