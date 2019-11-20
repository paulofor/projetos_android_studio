package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.DispositivoUsuario;
import coletapreco.view.adapter.listauso.base.DispositivoUsuarioListUsoAdapterBase;


public class DispositivoUsuarioListUsoAdapter extends DispositivoUsuarioListUsoAdapterBase {
	
	public DispositivoUsuarioListUsoAdapter(List<DispositivoUsuario> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
