package repcom.modelo.display.impl;

import repcom.modelo.Produto;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class ProdutoDisplay extends ObjetoDisplay<Produto> implements DisplayI<Produto> { 
  
  	public ProdutoDisplay(Produto item) {
		super(item);
	} 
  	/*
  	private Produto principal;
  	
  	public ProdutoDisplay(Produto item) {
  		principal = item;
  	}
  	*/
  	
  }