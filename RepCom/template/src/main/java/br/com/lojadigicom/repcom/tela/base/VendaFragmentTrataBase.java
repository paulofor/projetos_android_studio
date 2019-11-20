
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.Venda;
import android.view.View;

public abstract class VendaFragmentTrataBase extends Fragment{

	private Venda item = null;
	
	protected Venda getItem() {
		return item;
	}
	public void setItem(Venda valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}