package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.ModeloProdutoProduto;
import coletapreco.view.adapter.listauso.base.ModeloProdutoProdutoListUsoAdapterBase;


public class ModeloProdutoProdutoListUsoAdapter extends ModeloProdutoProdutoListUsoAdapterBase {
	
	public ModeloProdutoProdutoListUsoAdapter(List<ModeloProdutoProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
