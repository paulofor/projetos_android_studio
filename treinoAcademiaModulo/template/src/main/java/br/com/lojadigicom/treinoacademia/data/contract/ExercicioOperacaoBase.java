
package  br.com.lojadigicom.treinoacademia.data.contract;

import android.net.Uri;

public abstract class ExercicioOperacaoBase {
	
	
 	public final Uri buildListaAtivosNoMomento(ExercicioFiltro filtro) {
		String uriStr = ExercicioContract.getContentUri().toString() + "/ListaAtivosNoMomento/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}