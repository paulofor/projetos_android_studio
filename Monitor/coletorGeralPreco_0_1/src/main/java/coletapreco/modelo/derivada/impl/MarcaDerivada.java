package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.Marca;
import coletapreco.modelo.derivada.MarcaDerivadaI;

public class MarcaDerivada implements MarcaDerivadaI{ 
  
  	private Marca principal;
  
  	/*
  	public MarcaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public MarcaDerivada() {
		super();
  	}
  	*/
  	
  	public MarcaDerivada(Marca item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em MarcaDerivada";
	}
}