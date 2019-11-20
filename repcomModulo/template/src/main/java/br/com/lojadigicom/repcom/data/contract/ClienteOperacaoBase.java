
package  br.com.lojadigicom.repcom.data.contract;

import android.net.Uri;

public abstract class ClienteOperacaoBase {
	
	
 	public final Uri buildObtemListaAgendaTel(ClienteFiltro filtro) {
		String uriStr = ClienteContract.getContentUri().toString() + "/ObtemListaAgendaTel/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildSincronizaAgendaTel(ClienteFiltro filtro) {
		String uriStr = ClienteContract.getContentUri().toString() + "/SincronizaAgendaTel/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildObtemPorIdAgendaTel(ClienteFiltro filtro) {
		String uriStr = ClienteContract.getContentUri().toString() + "/ObtemPorIdAgendaTel/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildPreencheDerivadaAgendaTel(ClienteFiltro filtro) {
		String uriStr = ClienteContract.getContentUri().toString() + "/PreencheDerivadaAgendaTel/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildDesativaPorId(ClienteFiltro filtro) {
		String uriStr = ClienteContract.getContentUri().toString() + "/DesativaPorId/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildListaAtivos(ClienteFiltro filtro) {
		String uriStr = ClienteContract.getContentUri().toString() + "/ListaAtivos/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
 	public final Uri buildPesquisaTrechoNome(ClienteFiltro filtro) {
		String uriStr = ClienteContract.getContentUri().toString() + "/PesquisaTrechoNome/param?" + filtro.getParam();
		return Uri.parse(uriStr);
    }
	
}