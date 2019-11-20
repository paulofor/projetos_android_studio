
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.DispositivoUsuario;
import android.view.View;

public abstract class DispositivoUsuarioFragmentTrataBase extends Fragment{

	private DispositivoUsuario item = null;
	
	protected DispositivoUsuario getItem() {
		return item;
	}
	public void setItem(DispositivoUsuario valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}