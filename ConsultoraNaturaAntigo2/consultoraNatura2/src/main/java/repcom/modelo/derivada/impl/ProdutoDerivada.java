package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ProdutoDerivada implements ProdutoDerivadaI{ 
  
  	private Produto principal;
  
  	/*
  	public ProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public ProdutoDerivada(Produto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ProdutoDerivada";
	}
}