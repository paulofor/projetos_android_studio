
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.AppProduto;
import android.view.View;

public abstract class AppProdutoFragmentTrataBase extends Fragment{

	private AppProduto item = null;
	
	protected AppProduto getItem() {
		return item;
	}
	public void setItem(AppProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}