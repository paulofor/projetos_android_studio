package  treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.modelo.Usuario;
import treinoacademia.view.adapter.listauso.base.UsuarioListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class UsuarioListUsoAdapter extends UsuarioListUsoAdapterBase {
	
	public UsuarioListUsoAdapter(List<Usuario> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
