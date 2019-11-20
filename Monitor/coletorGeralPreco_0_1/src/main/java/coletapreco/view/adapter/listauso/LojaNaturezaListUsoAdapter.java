package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.LojaNatureza;
import coletapreco.view.adapter.listauso.base.LojaNaturezaListUsoAdapterBase;


public class LojaNaturezaListUsoAdapter extends LojaNaturezaListUsoAdapterBase {
	
	public LojaNaturezaListUsoAdapter(List<LojaNatureza> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
