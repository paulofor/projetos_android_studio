package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.CategoriaProdutoProduto;
import repcom.view.adapter.listauso.base.CategoriaProdutoProdutoListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class CategoriaProdutoProdutoListUsoAdapter extends CategoriaProdutoProdutoListUsoAdapterBase {
	
	public CategoriaProdutoProdutoListUsoAdapter(List<CategoriaProdutoProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
