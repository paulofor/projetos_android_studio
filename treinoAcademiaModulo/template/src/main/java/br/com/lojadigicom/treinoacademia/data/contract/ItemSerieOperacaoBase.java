
package  br.com.lojadigicom.treinoacademia.data.contract;

import android.net.Uri;

public abstract class ItemSerieOperacaoBase {
	
	
 	public final Uri buildListaPorDiaComExecucao(ItemSerieFiltro filtro) {
		String uriStr = ItemSerieContract.getContentUri().toString() + "/ListaPorDiaComExecucao/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildFinalizaItemSerie(ItemSerieFiltro filtro) {
		String uriStr = ItemSerieContract.getContentUri().toString() + "/FinalizaItemSerie/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildCarregaCompleto(ItemSerieFiltro filtro) {
		String uriStr = ItemSerieContract.getContentUri().toString() + "/CarregaCompleto/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildCarregaUltimasExecucoes(ItemSerieFiltro filtro) {
		String uriStr = ItemSerieContract.getContentUri().toString() + "/CarregaUltimasExecucoes/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildAtualizaCarga(ItemSerieFiltro filtro) {
		String uriStr = ItemSerieContract.getContentUri().toString() + "/AtualizaCarga/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}