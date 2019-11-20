
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;
import android.view.View;

public abstract class UsuarioPesquisaFragmentTrataBase extends Fragment{

	private UsuarioPesquisa item = null;
	
	protected UsuarioPesquisa getItem() {
		return item;
	}
	public void setItem(UsuarioPesquisa valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}