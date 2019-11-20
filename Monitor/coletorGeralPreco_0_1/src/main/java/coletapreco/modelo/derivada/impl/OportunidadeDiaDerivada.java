package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.OportunidadeDia;
import coletapreco.modelo.derivada.OportunidadeDiaDerivadaI;

public class OportunidadeDiaDerivada implements OportunidadeDiaDerivadaI{ 
  
  	private OportunidadeDia principal;
  
  	/*
  	public OportunidadeDiaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public OportunidadeDiaDerivada() {
		super();
  	}
  	*/
  	
  	public OportunidadeDiaDerivada(OportunidadeDia item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em OportunidadeDiaDerivada";
	}
}