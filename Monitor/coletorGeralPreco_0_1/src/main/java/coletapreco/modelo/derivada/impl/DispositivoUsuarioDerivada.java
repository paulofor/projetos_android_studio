package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.DispositivoUsuario;
import coletapreco.modelo.derivada.DispositivoUsuarioDerivadaI;

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