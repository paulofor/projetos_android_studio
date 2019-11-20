
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import android.net.Uri;

public abstract class NaturezaProdutoOperacaoBase {
	
	
 	public final Uri buildListaAtivo(NaturezaProdutoFiltro filtro) {
		String uriStr = NaturezaProdutoContract.getContentUri().toString() + "/ListaAtivo/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}