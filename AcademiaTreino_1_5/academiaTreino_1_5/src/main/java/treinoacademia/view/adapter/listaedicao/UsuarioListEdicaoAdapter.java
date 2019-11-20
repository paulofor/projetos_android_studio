package  treinoacademia.view.adapter.listaedicao;

import java.util.List;

import treinoacademia.modelo.Usuario;
import treinoacademia.view.adapter.listaedicao.base.UsuarioListEdicaoAdapterBase;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class UsuarioListEdicaoAdapter extends UsuarioListEdicaoAdapterBase {
	
	
	public UsuarioListEdicaoAdapter(List<Usuario> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
