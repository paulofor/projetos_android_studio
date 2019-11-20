package  treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.view.adapter.listauso.base.CargaPlanejadaListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class CargaPlanejadaListUsoAdapter extends CargaPlanejadaListUsoAdapterBase {
	
	public CargaPlanejadaListUsoAdapter(List<CargaPlanejada> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
