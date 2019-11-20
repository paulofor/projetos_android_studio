package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.ModeloProduto;
import coletapreco.view.adapter.listauso.base.ModeloProdutoListUsoAdapterBase;


public class ModeloProdutoListUsoAdapter extends ModeloProdutoListUsoAdapterBase {
	
	public ModeloProdutoListUsoAdapter(List<ModeloProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
