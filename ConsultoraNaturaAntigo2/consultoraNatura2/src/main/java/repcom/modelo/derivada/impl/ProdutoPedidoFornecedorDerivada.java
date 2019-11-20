package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ProdutoPedidoFornecedorDerivada implements ProdutoPedidoFornecedorDerivadaI{ 
  
  	private ProdutoPedidoFornecedor principal;
  
  	/*
  	public ProdutoPedidoFornecedorDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ProdutoPedidoFornecedorDerivada() {
		super();
  	}
  	*/
  	
  	public ProdutoPedidoFornecedorDerivada(ProdutoPedidoFornecedor item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ProdutoPedidoFornecedorDerivada";
	}
}