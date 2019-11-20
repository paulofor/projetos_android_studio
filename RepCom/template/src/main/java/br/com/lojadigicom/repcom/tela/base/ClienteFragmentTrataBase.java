
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.Cliente;
import android.view.View;

public abstract class ClienteFragmentTrataBase extends Fragment{

	private Cliente item = null;
	
	protected Cliente getItem() {
		return item;
	}
	public void setItem(Cliente valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}