
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.CategoriaProdutoProduto;
import android.view.View;

public abstract class CategoriaProdutoProdutoFragmentTrataBase extends Fragment{

	private CategoriaProdutoProduto item = null;
	
	protected CategoriaProdutoProduto getItem() {
		return item;
	}
	public void setItem(CategoriaProdutoProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}