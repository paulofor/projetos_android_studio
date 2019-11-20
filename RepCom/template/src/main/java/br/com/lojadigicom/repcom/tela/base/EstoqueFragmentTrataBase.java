
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.Estoque;
import android.view.View;

public abstract class EstoqueFragmentTrataBase extends Fragment{

	private Estoque item = null;
	
	protected Estoque getItem() {
		return item;
	}
	public void setItem(Estoque valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}