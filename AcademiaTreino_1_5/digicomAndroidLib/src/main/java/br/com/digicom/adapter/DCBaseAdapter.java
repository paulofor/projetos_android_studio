package br.com.digicom.adapter;


import java.sql.Timestamp;

import android.view.View;
import android.widget.BaseAdapter;
import br.com.digicom.activity.BaseFragmentActivity;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.quadro.GerenciadorItensTela;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IQuadroLista;

public abstract class DCBaseAdapter extends BaseAdapter {

	private GerenciadorItensTela gerItemTela = null;
	
	private View tela = null; // Deixar assim por um tempo, depois colocar como private (limite 04-09-2015)
	private IQuadroLista fragLista = null;
	
	public final void setRaiz(IQuadroLista quadro) {
		fragLista = quadro;
	}
	public final void setTela(View tela) {
		this.tela = tela;
		gerItemTela = new GerenciadorItensTela(tela);
	}
	
	protected IQuadroLista getQuadroLista() {
		return fragLista;
	}
	
	public void setTextoTextView(int recurso, String nomeRecurso, String texto) {
		gerItemTela.setTexto(recurso, nomeRecurso, texto);
	}
	public void setTextoTextView(int recurso, String nomeRecurso, long texto) {
		gerItemTela.setTexto(recurso, nomeRecurso, texto);
	}
	public void setTempo(int recurso, String nomeRecurso, long texto) {
		gerItemTela.setTempo(recurso, nomeRecurso, texto);
	}
	public void setTempo(int recurso, String nomeRecurso, Timestamp texto) {
		gerItemTela.setTempo(recurso, nomeRecurso, texto);
	}
	public void setData(int recurso, String nomeRecurso, Timestamp texto) {
		gerItemTela.setData(recurso, nomeRecurso, texto);
	}
	public void setMoeda(int recurso, String nomeRecurso, float texto) {
		gerItemTela.setMoeda(recurso, nomeRecurso, texto);
	}
	
}
