package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class CategoriaProdutoProdutoDerivada implements CategoriaProdutoProdutoDerivadaI{ 
  
  	private CategoriaProdutoProduto principal;
  
  	/*
  	public CategoriaProdutoProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public CategoriaProdutoProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public CategoriaProdutoProdutoDerivada(CategoriaProdutoProduto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em CategoriaProdutoProdutoDerivada";
	}
}