package br.com.digicom.quadro;

import java.util.List;

import br.com.digicom.modelo.DCIObjetoDominio;

public interface DialogListListener {

	public void onDialogPositiveClick(List<DCIObjetoDominio> listaInclusao,List<DCIObjetoDominio> listaExclusao);
	public void onDialogNegativeClick(List<DCIObjetoDominio> listaEscolhidos);
}
