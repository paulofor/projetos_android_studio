package repcom.modelo.display.impl;

import repcom.modelo.ContatoCliente;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class ContatoClienteDisplay extends ObjetoDisplay<ContatoCliente> implements DisplayI<ContatoCliente> { 
  
  	public ContatoClienteDisplay(ContatoCliente item) {
		super(item);
	} 
  	/*
  	private ContatoCliente principal;
  	
  	public ContatoClienteDisplay(ContatoCliente item) {
  		principal = item;
  	}
  	*/
  	
  }