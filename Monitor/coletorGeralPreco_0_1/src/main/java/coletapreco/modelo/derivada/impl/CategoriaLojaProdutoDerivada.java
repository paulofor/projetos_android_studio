package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.CategoriaLojaProduto;
import coletapreco.modelo.derivada.CategoriaLojaProdutoDerivadaI;

public class CategoriaLojaProdutoDerivada implements CategoriaLojaProdutoDerivadaI{ 
  
  	private CategoriaLojaProduto principal;
  
  	/*
  	public CategoriaLojaProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public CategoriaLojaProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public CategoriaLojaProdutoDerivada(CategoriaLojaProduto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em CategoriaLojaProdutoDerivada";
	}
}