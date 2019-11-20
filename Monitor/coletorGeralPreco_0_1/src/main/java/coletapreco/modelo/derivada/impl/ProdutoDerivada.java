package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.Produto;
import coletapreco.modelo.derivada.ProdutoDerivadaI;

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