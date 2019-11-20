
package  br.com.lojadigicom.treinoacademia.data.contract;

import android.net.Uri;

public abstract class ExecucaoItemSerieOperacaoBase {
	
	
 	public final Uri buildObtemPorDiaItemSerie(ExecucaoItemSerieFiltro filtro) {
		String uriStr = ExecucaoItemSerieContract.getContentUri().toString() + "/ObtemPorDiaItemSerie/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildUltimasExecucoesUsuarioExercicio(ExecucaoItemSerieFiltro filtro) {
		String uriStr = ExecucaoItemSerieContract.getContentUri().toString() + "/UltimasExecucoesUsuarioExercicio/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildUltimasExecucoesItemSerie(ExecucaoItemSerieFiltro filtro) {
		String uriStr = ExecucaoItemSerieContract.getContentUri().toString() + "/UltimasExecucoesItemSerie/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildCarregaCompletoPorDia(ExecucaoItemSerieFiltro filtro) {
		String uriStr = ExecucaoItemSerieContract.getContentUri().toString() + "/CarregaCompletoPorDia/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildPrimeiraPorDia(ExecucaoItemSerieFiltro filtro) {
		String uriStr = ExecucaoItemSerieContract.getContentUri().toString() + "/PrimeiraPorDia/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildUltimaPorDia(ExecucaoItemSerieFiltro filtro) {
		String uriStr = ExecucaoItemSerieContract.getContentUri().toString() + "/UltimaPorDia/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildPrimeiraPorSerie(ExecucaoItemSerieFiltro filtro) {
		String uriStr = ExecucaoItemSerieContract.getContentUri().toString() + "/PrimeiraPorSerie/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}