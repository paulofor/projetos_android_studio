
package  br.com.lojadigicom.repcom.data.contract;

import android.net.Uri;

public abstract class ProdutoOperacaoBase {
	
	
 	public final Uri buildListaPorIdCategoria(ProdutoFiltro filtro) {
		String uriStr = ProdutoContract.getContentUri().toString() + "/ListaPorIdCategoria/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildPesquisaTrechoNome(ProdutoFiltro filtro) {
		String uriStr = ProdutoContract.getContentUri().toString() + "/PesquisaTrechoNome/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}