package  treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.modelo.DispositivoUsuario;
import treinoacademia.view.adapter.listauso.base.DispositivoUsuarioListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class DispositivoUsuarioListUsoAdapter extends DispositivoUsuarioListUsoAdapterBase {
	
	public DispositivoUsuarioListUsoAdapter(List<DispositivoUsuario> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
