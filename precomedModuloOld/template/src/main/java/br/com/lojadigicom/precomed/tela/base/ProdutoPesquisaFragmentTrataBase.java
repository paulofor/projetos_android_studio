
package  br.com.lojadigicom.precomed.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.precomed.modelo.ProdutoPesquisa;
import android.view.View;

public abstract class ProdutoPesquisaFragmentTrataBase extends Fragment{

	private ProdutoPesquisa item = null;
	
	protected ProdutoPesquisa getItem() {
		return item;
	}
	public void setItem(ProdutoPesquisa valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}