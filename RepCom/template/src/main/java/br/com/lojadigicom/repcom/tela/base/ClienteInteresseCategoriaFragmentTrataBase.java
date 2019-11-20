
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.ClienteInteresseCategoria;
import android.view.View;

public abstract class ClienteInteresseCategoriaFragmentTrataBase extends Fragment{

	private ClienteInteresseCategoria item = null;
	
	protected ClienteInteresseCategoria getItem() {
		return item;
	}
	public void setItem(ClienteInteresseCategoria valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}