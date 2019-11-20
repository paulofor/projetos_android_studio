package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.LojaNatureza;
import coletapreco.modelo.derivada.LojaNaturezaDerivadaI;

public class LojaNaturezaDerivada implements LojaNaturezaDerivadaI{ 
  
  	private LojaNatureza principal;
  
  	/*
  	public LojaNaturezaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public LojaNaturezaDerivada() {
		super();
  	}
  	*/
  	
  	public LojaNaturezaDerivada(LojaNatureza item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em LojaNaturezaDerivada";
	}
}