package br.com.digicom.dao;

import br.com.digicom.modelo.DCIObjetoDominio;

public interface DaoSincronismo {

	public void insere(DCIObjetoDominio obj);
	public void dropCreate();
}
