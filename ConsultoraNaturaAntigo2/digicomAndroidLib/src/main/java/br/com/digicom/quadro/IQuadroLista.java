package br.com.digicom.quadro;

import br.com.digicom.modelo.DCIObjetoDominio;

public interface IQuadroLista {

	public void onToqueTela(DCIObjetoDominio obj);
	
	public void alteraQuadro(IFragment quadro);
}
