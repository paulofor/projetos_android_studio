package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.ModeloProduto;
import coletapreco.modelo.derivada.ModeloProdutoDerivadaI;

public class ModeloProdutoDerivada implements ModeloProdutoDerivadaI{ 
  
  	private ModeloProduto principal;
  
  	/*
  	public ModeloProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ModeloProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public ModeloProdutoDerivada(ModeloProduto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ModeloProdutoDerivada";
	}
}