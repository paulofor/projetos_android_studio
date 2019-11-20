package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.Usuario;
import coletapreco.view.adapter.listaedicao.base.UsuarioListEdicaoAdapterBase;

public class UsuarioListEdicaoAdapter extends UsuarioListEdicaoAdapterBase {
	
	
	public UsuarioListEdicaoAdapter(List<Usuario> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
