
package  br.com.lojadigicom.precomed.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.precomed.modelo.Produto;
import android.view.View;

public abstract class ProdutoFragmentTrataBase extends Fragment{

	private Produto item = null;
	
	protected Produto getItem() {
		return item;
	}
	public void setItem(Produto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}