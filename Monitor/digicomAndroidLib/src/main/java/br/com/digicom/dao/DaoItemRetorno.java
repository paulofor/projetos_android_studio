package br.com.digicom.dao;

import br.com.digicom.modelo.DCIObjetoDominio;



public class DaoItemRetorno {

	
	private boolean insere;
	private DCIObjetoDominio objeto;
	
	public boolean getInsere() {
		return insere;
	}
	public void setInsere(boolean item) {
		insere = item;
	}
	
	public DCIObjetoDominio getObjeto() {
		return objeto;
	}
	public void setObjeto(DCIObjetoDominio item) {
		objeto = item;
	}
}
