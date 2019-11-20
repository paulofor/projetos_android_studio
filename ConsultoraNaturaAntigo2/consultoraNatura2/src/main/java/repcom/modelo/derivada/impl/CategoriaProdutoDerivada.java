package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class CategoriaProdutoDerivada implements CategoriaProdutoDerivadaI{ 
  
  	private CategoriaProduto principal;
  
  	/*
  	public CategoriaProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public CategoriaProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public CategoriaProdutoDerivada(CategoriaProduto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em CategoriaProdutoDerivada";
	}
}