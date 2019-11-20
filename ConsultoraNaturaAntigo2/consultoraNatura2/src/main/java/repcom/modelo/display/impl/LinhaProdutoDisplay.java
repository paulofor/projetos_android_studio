package repcom.modelo.display.impl;

import repcom.modelo.LinhaProduto;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class LinhaProdutoDisplay extends ObjetoDisplay<LinhaProduto> implements DisplayI<LinhaProduto> { 
  
  	public LinhaProdutoDisplay(LinhaProduto item) {
		super(item);
	} 
  	/*
  	private LinhaProduto principal;
  	
  	public LinhaProdutoDisplay(LinhaProduto item) {
  		principal = item;
  	}
  	*/
  	
  }