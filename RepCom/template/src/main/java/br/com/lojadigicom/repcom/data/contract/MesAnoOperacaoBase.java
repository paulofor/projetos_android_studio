
package  br.com.lojadigicom.repcom.data.contract;

import android.net.Uri;

public abstract class MesAnoOperacaoBase {
	
	
 	public final Uri buildObtemMesCorrente(MesAnoFiltro filtro) {
		String uriStr = MesAnoContract.getContentUri().toString() + "/ObtemMesCorrente/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}