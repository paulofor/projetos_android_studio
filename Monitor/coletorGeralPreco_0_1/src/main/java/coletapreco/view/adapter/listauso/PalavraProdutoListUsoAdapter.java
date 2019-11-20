package  coletapreco.view.adapter.listauso;

import java.util.List;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.PalavraProduto;
import coletapreco.view.adapter.listauso.base.PalavraProdutoListUsoAdapterBase;


public class PalavraProdutoListUsoAdapter extends PalavraProdutoListUsoAdapterBase {
	
	public PalavraProdutoListUsoAdapter(List<PalavraProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
