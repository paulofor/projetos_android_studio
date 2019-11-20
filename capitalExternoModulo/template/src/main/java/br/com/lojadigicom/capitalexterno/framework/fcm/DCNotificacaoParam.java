package br.com.lojadigicom.capitalexterno.framework.fcm;


import android.content.Context;

public class DCNotificacaoParam {
	private int idIcone;
	private String titulo;
	private String texto;

	public DCNotificacaoParam(int idIcone, String titulo, String texto) {
		this.idIcone = idIcone;
		this.titulo = titulo;
		this.texto = texto;
	}

    public int getIdIcone() {
    	return idIcone;
    }
    public String getTitulo() {
    	return titulo;
    }
    public String getTexto() {
    	return texto;
    }
}
