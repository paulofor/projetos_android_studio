package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.Produto;
import coletapreco.view.adapter.listauso.base.ProdutoListUsoAdapterBase;


public class ProdutoListUsoAdapter extends ProdutoListUsoAdapterBase {
	
	public ProdutoListUsoAdapter(List<Produto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
