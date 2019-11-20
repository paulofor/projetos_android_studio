package br.com.digicom.quadro;

import java.util.HashMap;

public class BundleFragment {
	
	private HashMap<String,Object> map = null;
	
	private HashMap<String,Object> getMap() {
		if (map==null) {
			map = new HashMap<String,Object>();
		}
		return map;
	}
	public void setObjeto(String chave, Object valor) {
		getMap().put(chave, valor);
	}
	public Object getObjeto(String chave) {
		Object saida = getMap().get(chave);
		if (saida==null) {
			throw new RuntimeException("Chave " + chave + " não encontrada");
		}
		return saida;
	}
	public Object getObjetoPermiteNull(String chave) {
		Object saida = getMap().get(chave);
		return saida;
	}
}
