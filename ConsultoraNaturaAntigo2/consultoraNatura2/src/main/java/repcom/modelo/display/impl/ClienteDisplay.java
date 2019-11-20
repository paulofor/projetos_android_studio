package repcom.modelo.display.impl;

import repcom.modelo.Cliente;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class ClienteDisplay extends ObjetoDisplay<Cliente> implements DisplayI<Cliente> { 
  
  	public ClienteDisplay(Cliente item) {
		super(item);
	} 
  	/*
  	private Cliente principal;
  	
  	public ClienteDisplay(Cliente item) {
  		principal = item;
  	}
  	*/
  	
  }