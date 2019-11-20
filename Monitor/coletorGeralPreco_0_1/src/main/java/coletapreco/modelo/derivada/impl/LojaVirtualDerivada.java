package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.LojaVirtual;
import coletapreco.modelo.derivada.LojaVirtualDerivadaI;

public class LojaVirtualDerivada implements LojaVirtualDerivadaI{ 
  
  	private LojaVirtual principal;
  
  	/*
  	public LojaVirtualDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public LojaVirtualDerivada() {
		super();
  	}
  	*/
  	
  	public LojaVirtualDerivada(LojaVirtual item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em LojaVirtualDerivada";
	}
}