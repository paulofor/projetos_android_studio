
package  br.com.lojadigicom.precomed.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.precomed.modelo.ModeloProdutoProduto;
import android.view.View;

public abstract class ModeloProdutoProdutoFragmentTrataBase extends Fragment{

	private ModeloProdutoProduto item = null;
	
	protected ModeloProdutoProduto getItem() {
		return item;
	}
	public void setItem(ModeloProdutoProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}