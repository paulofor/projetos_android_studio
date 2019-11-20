package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.Marca;
import coletapreco.view.adapter.listauso.base.MarcaListUsoAdapterBase;


public class MarcaListUsoAdapter extends MarcaListUsoAdapterBase {
	
	public MarcaListUsoAdapter(List<Marca> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
