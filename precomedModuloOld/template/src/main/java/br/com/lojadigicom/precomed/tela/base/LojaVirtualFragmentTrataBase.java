
package  br.com.lojadigicom.precomed.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.precomed.modelo.LojaVirtual;
import android.view.View;

public abstract class LojaVirtualFragmentTrataBase extends Fragment{

	private LojaVirtual item = null;
	
	protected LojaVirtual getItem() {
		return item;
	}
	public void setItem(LojaVirtual valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}