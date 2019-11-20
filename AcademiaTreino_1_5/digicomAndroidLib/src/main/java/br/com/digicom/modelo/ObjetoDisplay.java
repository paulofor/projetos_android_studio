package br.com.digicom.modelo;

import android.view.View;

public abstract class ObjetoDisplay<E> implements DisplayI<E>{
	
	private E principal;
  	
  	public ObjetoDisplay(E item) {
  		principal = item;
  	}
  	protected E getPrincipal() {
  		return principal;
  	}
	
	public View getItemListaView() {
		throw new UnsupportedOperationException();
	}
	public String getItemListaTexto() {
		throw new UnsupportedOperationException();
	}
}
