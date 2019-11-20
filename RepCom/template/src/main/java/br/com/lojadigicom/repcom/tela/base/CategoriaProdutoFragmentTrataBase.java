
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.CategoriaProduto;
import android.view.View;

public abstract class CategoriaProdutoFragmentTrataBase extends Fragment{

	private CategoriaProduto item = null;
	
	protected CategoriaProduto getItem() {
		return item;
	}
	public void setItem(CategoriaProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}