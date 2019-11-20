package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class PagamentoFornecedorDerivada implements PagamentoFornecedorDerivadaI{ 
  
  	private PagamentoFornecedor principal;
  
  	/*
  	public PagamentoFornecedorDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public PagamentoFornecedorDerivada() {
		super();
  	}
  	*/
  	
  	public PagamentoFornecedorDerivada(PagamentoFornecedor item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em PagamentoFornecedorDerivada";
	}
}