package  treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.modelo.SerieTreino;
import treinoacademia.view.adapter.listauso.base.SerieTreinoListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class SerieTreinoListUsoAdapter extends SerieTreinoListUsoAdapterBase {
	
	public SerieTreinoListUsoAdapter(List<SerieTreino> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
