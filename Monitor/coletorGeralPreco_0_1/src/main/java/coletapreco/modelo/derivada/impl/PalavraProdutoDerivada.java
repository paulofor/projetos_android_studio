package coletapreco.modelo.derivada.impl;

import  coletapreco.modelo.derivada.*;
import  coletapreco.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class PalavraProdutoDerivada implements PalavraProdutoDerivadaI{ 
  
  	private PalavraProduto principal;
  
  	/*
  	public PalavraProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public PalavraProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public PalavraProdutoDerivada(PalavraProduto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em PalavraProdutoDerivada";
	}
}