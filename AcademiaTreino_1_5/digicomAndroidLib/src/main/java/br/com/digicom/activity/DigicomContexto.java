package br.com.digicom.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import br.com.digicom.log.DCLog;
import br.com.digicom.quadro.IFragment;

public class DigicomContexto {

	
	private Context context;
	private IFragment quadro;
	
	public DigicomContexto(Activity contexto, IFragment quadro) {
		if (contexto==null) {
			//throw new RuntimeException("Context esta null.");
			DCLog.dStack("NULO", this, "Context esta nulo.");
			contexto = quadro.getActivityAlternativo();
		}
		this.context = contexto;
		this.quadro = quadro;
	}
	public IFragment getQuadro() {
		return quadro;
	}
	public Context getContext() {
		//return context;
		return context;
	}
	
	public Activity getActivity() {
		Fragment fragment = (Fragment) quadro;
		return fragment.getActivity();
	}
}
