
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.ContatoCliente;
import android.view.View;

public abstract class ContatoClienteFragmentTrataBase extends Fragment{

	private ContatoCliente item = null;
	
	protected ContatoCliente getItem() {
		return item;
	}
	public void setItem(ContatoCliente valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}