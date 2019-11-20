package br.com.lojadigicom.repcom.framework.fcm;



import android.content.ContentResolver;
import br.com.lojadigicom.repcom.framework.tela.DCAplicacao;


public abstract class DCNotificacaoParam {
	private int idIcone;
	private String titulo;
	private String texto;
	
	private Class mainClasse;
	private DCAplicacao aplicacao = null;
	

	public DCNotificacaoParam(Class classePrincipal, String titulo, DCAplicacao app) {
		this.mainClasse = classePrincipal;
		this.titulo = titulo;
		aplicacao = app;
	}

	protected ContentResolver getContentResolver() {
		return aplicacao.getContentResolver();
	}

    public abstract int getIdIcone();
    public String getTitulo() {
    	return titulo;
    }

    public abstract String getTexto();
    
    public abstract boolean existeAlteracao();
    public Class getMainClass() {
    	return mainClasse;
    }
}
