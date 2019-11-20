
package  br.com.lojadigicom.treinoacademia.data.contract;

import android.net.Uri;

public abstract class SerieTreinoOperacaoBase {
	
	
 	public final Uri buildObtemProxima(SerieTreinoFiltro filtro) {
		String uriStr = SerieTreinoContract.getContentUri().toString() + "/ObtemProxima/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildObtemMontadaPorId(SerieTreinoFiltro filtro) {
		String uriStr = SerieTreinoContract.getContentUri().toString() + "/ObtemMontadaPorId/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildCriaSerieCompleta(SerieTreinoFiltro filtro) {
		String uriStr = SerieTreinoContract.getContentUri().toString() + "/CriaSerieCompleta/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildCarregaItemSerie(SerieTreinoFiltro filtro) {
		String uriStr = SerieTreinoContract.getContentUri().toString() + "/CarregaItemSerie/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}