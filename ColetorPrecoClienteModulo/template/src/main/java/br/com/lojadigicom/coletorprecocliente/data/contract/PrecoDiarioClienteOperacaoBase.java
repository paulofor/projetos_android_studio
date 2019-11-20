
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import android.net.Uri;

public abstract class PrecoDiarioClienteOperacaoBase {
	
	
 	public final Uri buildQuantidadePorProduto(PrecoDiarioClienteFiltro filtro) {
		String uriStr = PrecoDiarioClienteContract.getContentUri().toString() + "/QuantidadePorProduto/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}