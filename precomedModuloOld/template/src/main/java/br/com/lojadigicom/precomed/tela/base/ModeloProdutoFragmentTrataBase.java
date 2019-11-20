
package  br.com.lojadigicom.precomed.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.precomed.modelo.ModeloProduto;
import android.view.View;

public abstract class ModeloProdutoFragmentTrataBase extends Fragment{

	private ModeloProduto item = null;
	
	protected ModeloProduto getItem() {
		return item;
	}
	public void setItem(ModeloProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}