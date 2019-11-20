package br.com.digicom.activity;

import android.app.Activity;



public abstract class BaseFragmentActivity extends Activity {

	private DigicomContexto ctx = null;
	protected abstract void inicializaServicos();
	
	protected DigicomContexto getContexto() {
		if (ctx==null) {
			ctx = new DigicomContexto(this,null);
		}
		return ctx;
	}
	
	
}
