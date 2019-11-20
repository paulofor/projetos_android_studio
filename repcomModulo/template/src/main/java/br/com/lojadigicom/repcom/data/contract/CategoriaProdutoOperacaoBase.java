
package  br.com.lojadigicom.repcom.data.contract;

import android.net.Uri;

public abstract class CategoriaProdutoOperacaoBase {
	
	
 	public final Uri buildListaNivel0(CategoriaProdutoFiltro filtro) {
		String uriStr = CategoriaProdutoContract.getContentUri().toString() + "/ListaNivel0/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}