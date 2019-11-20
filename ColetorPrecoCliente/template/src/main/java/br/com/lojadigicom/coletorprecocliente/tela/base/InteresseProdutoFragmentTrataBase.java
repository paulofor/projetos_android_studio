
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import android.view.View;

public abstract class InteresseProdutoFragmentTrataBase extends Fragment{

	private InteresseProduto item = null;
	
	protected InteresseProduto getItem() {
		return item;
	}
	public void setItem(InteresseProduto valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}