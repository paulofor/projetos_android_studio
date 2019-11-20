
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.ProdutoPedidoFornecedor;
import android.view.View;

public abstract class ProdutoPedidoFornecedorFragmentTrataBase extends Fragment{

	private ProdutoPedidoFornecedor item = null;
	
	protected ProdutoPedidoFornecedor getItem() {
		return item;
	}
	public void setItem(ProdutoPedidoFornecedor valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}