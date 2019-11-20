package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.CategoriaLoja;
import coletapreco.modelo.derivada.CategoriaLojaDerivadaI;

public class CategoriaLojaDerivada implements CategoriaLojaDerivadaI{ 
  
  	private CategoriaLoja principal;
  
  	/*
  	public CategoriaLojaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public CategoriaLojaDerivada() {
		super();
  	}
  	*/
  	
  	public CategoriaLojaDerivada(CategoriaLoja item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em CategoriaLojaDerivada";
	}
}