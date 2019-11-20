
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import android.net.Uri;

public abstract class ProdutoClienteOperacaoBase {
	
	
 	public final Uri buildListaNaoEscolhido(ProdutoClienteFiltro filtro) {
		String uriStr = ProdutoClienteContract.getContentUri().toString() + "/ListaNaoEscolhido/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildObtemProximoNaoEscolhido(ProdutoClienteFiltro filtro) {
		String uriStr = ProdutoClienteContract.getContentUri().toString() + "/ObtemProximoNaoEscolhido/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}