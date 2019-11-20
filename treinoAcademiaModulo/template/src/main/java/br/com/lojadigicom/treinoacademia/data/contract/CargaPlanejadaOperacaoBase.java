
package  br.com.lojadigicom.treinoacademia.data.contract;

import android.net.Uri;

public abstract class CargaPlanejadaOperacaoBase {
	
	
 	public final Uri buildListaCargaPorExercicio(CargaPlanejadaFiltro filtro) {
		String uriStr = CargaPlanejadaContract.getContentUri().toString() + "/ListaCargaPorExercicio/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildListaCargaAtivaPorExercicio(CargaPlanejadaFiltro filtro) {
		String uriStr = CargaPlanejadaContract.getContentUri().toString() + "/ListaCargaAtivaPorExercicio/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}