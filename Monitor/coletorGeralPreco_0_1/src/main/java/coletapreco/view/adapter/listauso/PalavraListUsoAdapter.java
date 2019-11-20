package  coletapreco.view.adapter.listauso;

import java.util.List;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.Palavra;
import coletapreco.view.adapter.listauso.base.PalavraListUsoAdapterBase;


public class PalavraListUsoAdapter extends PalavraListUsoAdapterBase {
	
	public PalavraListUsoAdapter(List<Palavra> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
