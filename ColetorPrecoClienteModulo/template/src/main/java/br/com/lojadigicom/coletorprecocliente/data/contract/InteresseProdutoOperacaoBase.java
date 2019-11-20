
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import android.net.Uri;

public abstract class InteresseProdutoOperacaoBase {
	
	
 	public final Uri buildListaEspera(InteresseProdutoFiltro filtro) {
		String uriStr = InteresseProdutoContract.getContentUri().toString() + "/ListaEspera/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildListaMonitora(InteresseProdutoFiltro filtro) {
		String uriStr = InteresseProdutoContract.getContentUri().toString() + "/ListaMonitora/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildObtemComProduto(InteresseProdutoFiltro filtro) {
		String uriStr = InteresseProdutoContract.getContentUri().toString() + "/ObtemComProduto/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildQuantidadeMonitorado(InteresseProdutoFiltro filtro) {
		String uriStr = InteresseProdutoContract.getContentUri().toString() + "/QuantidadeMonitorado/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildQuantidadeMudanca(InteresseProdutoFiltro filtro) {
		String uriStr = InteresseProdutoContract.getContentUri().toString() + "/QuantidadeMudanca/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}