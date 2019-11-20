package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class ProdutoVendaDerivada implements ProdutoVendaDerivadaI{ 
  
  	private ProdutoVenda principal;
  
  	/*
  	public ProdutoVendaDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public ProdutoVendaDerivada() {
		super();
  	}
  	*/
  	
  	public ProdutoVendaDerivada(ProdutoVenda item) {
  		principal = item;
  	}
  	
  	@Override
	public String getTituloTela() {
		return "Implementar getTituloTela() em ProdutoVendaDerivada";
	}
}