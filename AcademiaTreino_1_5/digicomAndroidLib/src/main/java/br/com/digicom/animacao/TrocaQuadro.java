package br.com.digicom.animacao;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.ViewGroup;
import br.com.digicom.R;
import br.com.digicom.activity.BaseFragmentActivity;
import br.com.digicom.log.DCLog;
import br.com.digicom.quadro.IFragment;

public class TrocaQuadro {
	
	public static String TAG_FRAGMENTO = "Principal"; 
	
	private static TrocaQuadro instancia = null;
	private static BaseFragmentActivity activityPrincipal = null;
	
	public static TrocaQuadro getInstancia() {
		if (instancia==null) {
			instancia = new TrocaQuadro();
		}
		return instancia;
	}
	public void setBaseActivity(BaseFragmentActivity principal) {
		this.activityPrincipal = principal;
	}

	/*
	public void alteraQuadroPrincipal(IFragment novoQuadro, BaseFragmentActivity tela) {
		FragmentTransaction transaction = tela.getFragmentManager().beginTransaction();
		transaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_right_enter,
                R.animator.fragment_slide_right_exit);
     	transaction.replace(R.id.fragment_principal,(Fragment) novoQuadro,TAG_FRAGMENTO);
     	transaction.addToBackStack(null);
     	transaction.commit();
		DCLog.d(DCLog.ANIMACAO, this, "alteraQuadroPrincipal");
		tela.getActionBar().setTitle(novoQuadro.getTituloTela());

	}
	*/
	
	public void alteraQuadroPrincipal(IFragment novoQuadro) {
		if (novoQuadro.getBundle()==null) {
			throw new RuntimeException("Bundle nulo para " + novoQuadro.getClass().getSimpleName());
		}
		FragmentTransaction transaction = activityPrincipal.getFragmentManager().beginTransaction();
		transaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_right_enter,
                R.animator.fragment_slide_right_exit);
		transaction.replace(R.id.fragment_principal,(Fragment) novoQuadro,TAG_FRAGMENTO);
     	transaction.addToBackStack(null);
     	transaction.commit();
     	novoQuadro.setActivityAlternativo(activityPrincipal);
		DCLog.d(DCLog.ANIMACAO, this, "alteraQuadroPrincipal");
		novoQuadro.extraiBundle();
		activityPrincipal.getActionBar().removeAllTabs();
		activityPrincipal.getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		activityPrincipal.getActionBar().setTitle(novoQuadro.getTituloTela());

	}
	public void criaDialog(IFragment quadro, IFragment origem) {
		if (quadro.getBundle()==null) {
			throw new RuntimeException("Bundle nulo para " + quadro.getClass().getSimpleName());
		}
		FragmentManager fragmentManager = ((Fragment)origem).getFragmentManager();
		DialogFragment tela = (DialogFragment) quadro;
		quadro.setChamador(origem);
		tela.show(fragmentManager, origem.getTituloTela());
	}
	
	public void setElementoTela(IFragment quadro, int recurso, IFragment origem) {
		if (quadro.getBundle()==null) {
			throw new RuntimeException("Bundle nulo para " + quadro.getClass().getSimpleName());
		}
		FragmentManager fragmentManager = ((Fragment)origem).getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment fragment = (Fragment) quadro;
		ViewGroup view = (ViewGroup) origem.getTela().findViewById(recurso);
		fragmentTransaction.add(recurso, fragment);
		fragmentTransaction.commit();
		quadro.setContainerFragment(origem);
	}
	public void retornaQuadro(IFragment atual) {
		FragmentManager fragmentManager =  ((Fragment)atual).getFragmentManager();
		fragmentManager.popBackStack();
	}
	// Alteracao de um campo de item de lista para detalhe no quadro seguinte.
	
	// Usando Activitys
	public void enviaIntent(Intent novoIntent, Activity activity) {
		activity.startActivity(novoIntent);
	}
	
	public IFragment getFragmentoAtual() {
		FragmentManager mng = activityPrincipal.getFragmentManager();
		Fragment frag = mng.findFragmentById(R.id.fragment_principal);
		return (IFragment) frag;
	}
	
}
