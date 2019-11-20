
package  br.com.lojadigicom.treinoacademia.data.contract;

import android.net.Uri;

public abstract class DiaTreinoOperacaoBase {
	
	
 	public final Uri buildEncerraDiaTreino(DiaTreinoFiltro filtro) {
		String uriStr = DiaTreinoContract.getContentUri().toString() + "/EncerraDiaTreino/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildObtemPorData(DiaTreinoFiltro filtro) {
		String uriStr = DiaTreinoContract.getContentUri().toString() + "/ObtemPorData/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildLimpezaBase(DiaTreinoFiltro filtro) {
		String uriStr = DiaTreinoContract.getContentUri().toString() + "/LimpezaBase/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildHistoricoExecucaoPorIdExercicio(DiaTreinoFiltro filtro) {
		String uriStr = DiaTreinoContract.getContentUri().toString() + "/HistoricoExecucaoPorIdExercicio/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildTreinosPosDataPesquisa(DiaTreinoFiltro filtro) {
		String uriStr = DiaTreinoContract.getContentUri().toString() + "/TreinosPosDataPesquisa/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}