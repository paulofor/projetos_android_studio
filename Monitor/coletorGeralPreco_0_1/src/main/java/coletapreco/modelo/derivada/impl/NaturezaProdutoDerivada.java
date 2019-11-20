package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.NaturezaProduto;
import coletapreco.modelo.derivada.NaturezaProdutoDerivadaI;

public class NaturezaProdutoDerivada implements NaturezaProdutoDerivadaI{ 
  
  	private NaturezaProduto principal;
  
  	/*
  	public NaturezaProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public NaturezaProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public NaturezaProdutoDerivada(NaturezaProduto item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em NaturezaProdutoDerivada";
	}
}