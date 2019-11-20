
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.LinhaProduto;
import android.view.View;

public abstract class LinhaProdutoFragmentTrataBase extends Fragment{

	private LinhaProduto item = null;
	
	protected LinhaProduto getItem() {
		return item;
	}
	public void setItem(LinhaProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}