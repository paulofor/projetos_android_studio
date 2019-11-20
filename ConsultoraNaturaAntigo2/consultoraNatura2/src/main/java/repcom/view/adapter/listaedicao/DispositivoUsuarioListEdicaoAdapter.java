package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.DispositivoUsuario;
import repcom.view.adapter.listaedicao.base.DispositivoUsuarioListEdicaoAdapterBase;

public class DispositivoUsuarioListEdicaoAdapter extends DispositivoUsuarioListEdicaoAdapterBase {
	
	
	public DispositivoUsuarioListEdicaoAdapter(List<DispositivoUsuario> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
