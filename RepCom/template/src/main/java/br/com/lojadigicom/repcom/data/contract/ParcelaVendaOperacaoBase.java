
package  br.com.lojadigicom.repcom.data.contract;

import android.net.Uri;

public abstract class ParcelaVendaOperacaoBase {
	
	
 	public final Uri buildCalculaParcelasVenda(ParcelaVendaFiltro filtro) {
		String uriStr = ParcelaVendaContract.getContentUri().toString() + "/CalculaParcelasVenda/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}