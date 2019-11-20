package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class PedidoFornecedorDerivada implements PedidoFornecedorDerivadaI{ 
  
  	private PedidoFornecedor principal;
  
  	/*
  	public PedidoFornecedorDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public PedidoFornecedorDerivada() {
		super();
  	}
  	*/
  	
  	public PedidoFornecedorDerivada(PedidoFornecedor item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em PedidoFornecedorDerivada";
	}
}