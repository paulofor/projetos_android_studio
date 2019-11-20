
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.MesAno;
import android.view.View;

public abstract class MesAnoFragmentTrataBase extends Fragment{

	private MesAno item = null;
	
	protected MesAno getItem() {
		return item;
	}
	public void setItem(MesAno valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}