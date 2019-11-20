
package  br.com.lojadigicom.repcom.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.ParcelaVenda;
import android.view.View;

public abstract class ParcelaVendaFragmentTrataBase extends Fragment{

	private ParcelaVenda item = null;
	
	protected ParcelaVenda getItem() {
		return item;
	}
	public void setItem(ParcelaVenda valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}