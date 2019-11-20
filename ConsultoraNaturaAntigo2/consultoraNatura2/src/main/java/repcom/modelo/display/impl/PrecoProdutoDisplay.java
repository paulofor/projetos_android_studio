package repcom.modelo.display.impl;

import repcom.modelo.PrecoProduto;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class PrecoProdutoDisplay extends ObjetoDisplay<PrecoProduto> implements DisplayI<PrecoProduto> { 
  
  	public PrecoProdutoDisplay(PrecoProduto item) {
		super(item);
	} 
  	/*
  	private PrecoProduto principal;
  	
  	public PrecoProdutoDisplay(PrecoProduto item) {
  		principal = item;
  	}
  	*/
  	
  }