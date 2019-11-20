
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.PrecoDiario;
import android.view.View;

public abstract class PrecoDiarioFragmentTrataBase extends Fragment{

	private PrecoDiario item = null;
	
	protected PrecoDiario getItem() {
		return item;
	}
	public void setItem(PrecoDiario valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}