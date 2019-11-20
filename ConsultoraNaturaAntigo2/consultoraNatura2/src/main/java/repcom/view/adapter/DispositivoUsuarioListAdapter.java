package  repcom.view.adapter;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroLista;
import repcom.modelo.DispositivoUsuario;
import repcom.view.adapter.base.DispositivoUsuarioListAdapterBase;

public class DispositivoUsuarioListAdapter extends DispositivoUsuarioListAdapterBase {
	
	public DispositivoUsuarioListAdapter(List<DispositivoUsuario> lista, Context context) {
		super(lista, context);
	}
	
}
