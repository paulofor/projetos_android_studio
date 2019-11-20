package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class PrecoProdutoDerivada implements PrecoProdutoDerivadaI{ 
  
  	private PrecoProduto principal;
  
  	/*
  	public PrecoProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public PrecoProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public PrecoProdutoDerivada(PrecoProduto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em PrecoProdutoDerivada";
	}
}