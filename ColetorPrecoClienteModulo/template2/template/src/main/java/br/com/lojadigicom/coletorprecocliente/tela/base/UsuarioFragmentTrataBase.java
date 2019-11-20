
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.Usuario;
import android.view.View;

public abstract class UsuarioFragmentTrataBase extends Fragment{

	private Usuario item = null;
	
	protected Usuario getItem() {
		return item;
	}
	public void setItem(Usuario valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}