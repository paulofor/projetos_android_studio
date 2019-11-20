
package  br.com.lojadigicom.precomed.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.precomed.modelo.OportunidadeDia;
import android.view.View;

public abstract class OportunidadeDiaFragmentTrataBase extends Fragment{

	private OportunidadeDia item = null;
	
	protected OportunidadeDia getItem() {
		return item;
	}
	public void setItem(OportunidadeDia valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}