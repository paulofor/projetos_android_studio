
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import android.view.View;

public abstract class NaturezaProdutoFragmentTrataBase extends Fragment{

	private NaturezaProduto item = null;
	
	protected NaturezaProduto getItem() {
		return item;
	}
	public void setItem(NaturezaProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}