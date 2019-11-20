package repcom.modelo.display.impl;

import repcom.modelo.Usuario;
import android.view.View;
import br.com.digicom.modelo.DisplayI;
import br.com.digicom.modelo.ObjetoDisplay;

public class UsuarioDisplay extends ObjetoDisplay<Usuario> implements DisplayI<Usuario> { 
  
  	public UsuarioDisplay(Usuario item) {
		super(item);
	} 
  	/*
  	private Usuario principal;
  	
  	public UsuarioDisplay(Usuario item) {
  		principal = item;
  	}
  	*/
  	
  }