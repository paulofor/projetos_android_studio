
package  br.com.lojadigicom.precomed.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.precomed.modelo.Marca;
import android.view.View;

public abstract class MarcaFragmentTrataBase extends Fragment{

	private Marca item = null;
	
	protected Marca getItem() {
		return item;
	}
	public void setItem(Marca valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}