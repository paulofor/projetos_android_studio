package repcom.modelo.derivada.impl;

import  repcom.modelo.derivada.*;
import  repcom.modelo.*;
import org.json.JSONObject;
import org.json.JSONException;

public class LinhaProdutoDerivada implements LinhaProdutoDerivadaI{ 
  
  	private LinhaProduto principal;
  
  	/*
  	public LinhaProdutoDerivada(JSONObject json) throws JSONException{
		super(json);
  	} 
  	public LinhaProdutoDerivada() {
		super();
  	}
  	*/
  	
  	public LinhaProdutoDerivada(LinhaProduto item) {
  		principal = item;
  	}

	@Override
	public void setQtdeProdutos(long valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getQtdeProdutos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setQtdeCategorias(long valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getQtdeCategorias() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTituloTela() {
		// TODO Auto-generated method stub
		return null;
	}
}