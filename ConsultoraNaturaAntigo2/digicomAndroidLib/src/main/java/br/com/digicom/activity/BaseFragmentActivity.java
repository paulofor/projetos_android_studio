package br.com.digicom.activity;

import android.support.v4.app.FragmentActivity;



public abstract class BaseFragmentActivity extends FragmentActivity {

	private DigicomContexto ctx = null;
	protected abstract void inicializaServicos();
	
	protected DigicomContexto getContexto() {
		if (ctx==null) {
			ctx = new DigicomContexto(this,null);
		}
		return ctx;
	}
	
	
}
