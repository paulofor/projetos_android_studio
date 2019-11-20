package br.com.digicom.quadro;

import android.app.Activity;
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
	public void setBundle(BundleFragment bundle);
	public BundleFragment getBundle();
	public void extraiBundle();
	
	public void setContainerFragment(IFragment container);
	public IFragment getContainerFragment();
	
	// O Fragment é associado a um activity pelo Android mas isso não acontece logo apos o commit.
	// Por esse motivo estou criando essa forma alterantiva de relacionar com activity.
	public Activity getActivityAlternativo();
	public void setActivityAlternativo(Activity activity);
	
	public void inicializaParaTeste();
	
	// Coloca o título na tela
	public void setTituloTela();
}
