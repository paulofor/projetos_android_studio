
package  br.com.lojadigicom.treinoacademia.data.contract;

import android.net.Uri;

public abstract class UsuarioOperacaoBase {
	
	
 	public final Uri buildObtemNoAparelho(UsuarioFiltro filtro) {
		String uriStr = UsuarioContract.getContentUri().toString() + "/ObtemNoAparelho/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}