package  treinoacademia.view.adapter.listaedicao;

import java.util.List;

import treinoacademia.modelo.DispositivoUsuario;
import treinoacademia.view.adapter.listaedicao.base.DispositivoUsuarioListEdicaoAdapterBase;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class DispositivoUsuarioListEdicaoAdapter extends DispositivoUsuarioListEdicaoAdapterBase {
	
	
	public DispositivoUsuarioListEdicaoAdapter(List<DispositivoUsuario> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
