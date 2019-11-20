
package  br.com.lojadigicom.repcom.data.contract;

import android.net.Uri;

public abstract class VendaOperacaoBase {
	
	
 	public final Uri buildAtualizaTotal(VendaFiltro filtro) {
		String uriStr = VendaContract.getContentUri().toString() + "/AtualizaTotal/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildCriaParcelamento(VendaFiltro filtro) {
		String uriStr = VendaContract.getContentUri().toString() + "/CriaParcelamento/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}