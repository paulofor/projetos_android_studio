
package br.com.lojadigicom.coletorprecocliente.framework.tela;

import br.com.lojadigicom.coletorprecocliente.framework.faturamento.ConstantesBilling;
import android.app.Application;

public abstract class DCAplicacao extends Application {

	private ConstantesBilling billing = null;

	public DCAplicacao() {
		super();
		billing = criaBilling();
	}

	public ConstantesBilling getBilling() {
		return billing;
	}	

   	public abstract String getLinkFacebook();
   
   	public abstract Class activityInicio();
   	
   	protected abstract ConstantesBilling criaBilling();
}
