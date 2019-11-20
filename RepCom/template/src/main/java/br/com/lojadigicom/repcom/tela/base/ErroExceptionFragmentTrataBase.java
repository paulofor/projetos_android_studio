
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.ErroException;
import android.view.View;

public abstract class ErroExceptionFragmentTrataBase extends Fragment{

	private ErroException item = null;
	
	protected ErroException getItem() {
		return item;
	}
	public void setItem(ErroException valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}