
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.ProdutoVenda;
import android.view.View;

public abstract class ProdutoVendaFragmentTrataBase extends Fragment{

	private ProdutoVenda item = null;
	
	protected ProdutoVenda getItem() {
		return item;
	}
	public void setItem(ProdutoVenda valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}