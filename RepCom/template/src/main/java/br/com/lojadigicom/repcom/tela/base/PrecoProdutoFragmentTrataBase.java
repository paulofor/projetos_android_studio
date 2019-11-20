
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.PrecoProduto;
import android.view.View;

public abstract class PrecoProdutoFragmentTrataBase extends Fragment{

	private PrecoProduto item = null;
	
	protected PrecoProduto getItem() {
		return item;
	}
	public void setItem(PrecoProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}