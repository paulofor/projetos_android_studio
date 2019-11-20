
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import android.view.View;

public abstract class ProdutoClienteFragmentTrataBase extends Fragment{

	private ProdutoCliente item = null;
	
	protected ProdutoCliente getItem() {
		return item;
	}
	public void setItem(ProdutoCliente valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}