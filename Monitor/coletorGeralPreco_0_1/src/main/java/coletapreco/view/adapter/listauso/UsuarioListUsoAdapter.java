package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.Usuario;
import coletapreco.view.adapter.listauso.base.UsuarioListUsoAdapterBase;


public class UsuarioListUsoAdapter extends UsuarioListUsoAdapterBase {
	
	public UsuarioListUsoAdapter(List<Usuario> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
