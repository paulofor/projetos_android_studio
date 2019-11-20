
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.PedidoFornecedor;
import android.view.View;

public abstract class PedidoFornecedorFragmentTrataBase extends Fragment{

	private PedidoFornecedor item = null;
	
	protected PedidoFornecedor getItem() {
		return item;
	}
	public void setItem(PedidoFornecedor valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}