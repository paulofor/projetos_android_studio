package repcom.modelo.display.impl;

import repcom.modelo.Venda;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class VendaDisplay extends ObjetoDisplay<Venda> implements DisplayI<Venda> { 
  
  	public VendaDisplay(Venda item) {
		super(item);
	} 
  	/*
  	private Venda principal;
  	
  	public VendaDisplay(Venda item) {
  		principal = item;
  	}
  	*/
  	
  }