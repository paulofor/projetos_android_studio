package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.DispositivoUsuario;
import coletapreco.view.adapter.listaedicao.base.DispositivoUsuarioListEdicaoAdapterBase;

public class DispositivoUsuarioListEdicaoAdapter extends DispositivoUsuarioListEdicaoAdapterBase {
	
	
	public DispositivoUsuarioListEdicaoAdapter(List<DispositivoUsuario> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
