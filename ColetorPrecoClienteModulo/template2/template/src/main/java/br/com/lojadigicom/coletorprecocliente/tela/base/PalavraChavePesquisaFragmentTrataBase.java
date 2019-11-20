
package  br.com.lojadigicom.coletorprecocliente.tela.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.PalavraChavePesquisa;
import android.view.View;

public abstract class PalavraChavePesquisaFragmentTrataBase extends Fragment{

	private PalavraChavePesquisa item = null;
	
	protected PalavraChavePesquisa getItem() {
		return item;
	}
	public void setItem(PalavraChavePesquisa valor) {
		item = valor;
	}
	
	protected abstract void inicializaCamposTela(View v);
	
}