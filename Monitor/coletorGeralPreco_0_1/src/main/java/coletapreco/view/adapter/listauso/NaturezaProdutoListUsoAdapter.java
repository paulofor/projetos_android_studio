package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.NaturezaProduto;
import coletapreco.view.adapter.listauso.base.NaturezaProdutoListUsoAdapterBase;


public class NaturezaProdutoListUsoAdapter extends NaturezaProdutoListUsoAdapterBase {
	
	public NaturezaProdutoListUsoAdapter(List<NaturezaProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
