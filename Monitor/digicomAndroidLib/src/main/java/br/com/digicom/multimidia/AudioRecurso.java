package br.com.digicom.multimidia;

import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.IFragment;

public class AudioRecurso {

	
	private int recurso;
	private DCIObjetoDominio objeto;
	private int tipoAudio;

	
	public AudioRecurso(DCIObjetoDominio objeto, int recurso) {
		this.objeto = objeto;
		this.recurso = recurso;
		this.tipoAudio = 0;
	}
	public AudioRecurso(DCIObjetoDominio objeto, int recurso, int tipoAudio) {
		this.objeto = objeto;
		this.recurso = recurso;
		this.tipoAudio = tipoAudio;
	}
	
	public int getTipoAudio() {
		return tipoAudio;
	}
	public int getRecurso() {
		return recurso;
	}
	public DCIObjetoDominio getObjeto() {
		return objeto;
	}
	public boolean testaTipo(int tipoTeste) {
		return (tipoTeste==this.tipoAudio);
	}
	
}
