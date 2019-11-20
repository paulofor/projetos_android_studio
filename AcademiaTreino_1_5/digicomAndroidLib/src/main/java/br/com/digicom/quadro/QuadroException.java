package br.com.digicom.quadro;

public class QuadroException extends RuntimeException {

	public QuadroException(IFragment quadro, String mensagem) {
		super(mensagem + " - chamador:" + quadro.getClass().getName());
	}
	
	
	
}
