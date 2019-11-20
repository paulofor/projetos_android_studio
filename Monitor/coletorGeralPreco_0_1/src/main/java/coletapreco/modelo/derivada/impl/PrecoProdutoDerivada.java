package coletapreco.modelo.derivada.impl;

import coletapreco.modelo.PrecoProduto;
import coletapreco.modelo.derivada.PrecoProdutoDerivadaI;

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