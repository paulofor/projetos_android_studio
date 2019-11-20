package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.ModeloProdutoProduto;
import coletapreco.modelo.derivada.ModeloProdutoProdutoDerivadaI;

public class ModeloProdutoProdutoDerivada implements ModeloProdutoProdutoDerivadaI{ 
  
  	private ModeloProdutoProduto principal;
  
  	/*
  	public ModeloProdutoProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ModeloProdutoProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public ModeloProdutoProdutoDerivada(ModeloProdutoProduto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ModeloProdutoProdutoDerivada";
	}
}