package br.com.digicom.quadro;

import android.view.View;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.multimidia.AudioRecurso;

public interface IFragment {

	public void audioRawConcluido(AudioRecurso audioRecurso);
	public void setChamador(IFragment chamador);
	public String getTituloTela();
	public View getTela();
	public void onAlteraQuadro();
	public ResourceObj getRecurso();
}
