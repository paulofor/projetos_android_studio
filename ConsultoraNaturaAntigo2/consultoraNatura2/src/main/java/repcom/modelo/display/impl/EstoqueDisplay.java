package repcom.modelo.display.impl;

import repcom.modelo.Estoque;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class EstoqueDisplay extends ObjetoDisplay<Estoque> implements DisplayI<Estoque> { 
  
  	public EstoqueDisplay(Estoque item) {
		super(item);
	} 
  	/*
  	private Estoque principal;
  	
  	public EstoqueDisplay(Estoque item) {
  		principal = item;
  	}
  	*/
  	
  }