
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.PagamentoFornecedor;
import android.view.View;

public abstract class PagamentoFornecedorFragmentTrataBase extends Fragment{

	private PagamentoFornecedor item = null;
	
	protected PagamentoFornecedor getItem() {
		return item;
	}
	public void setItem(PagamentoFornecedor valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}