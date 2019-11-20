package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.CategoriaLojaProduto;
import coletapreco.view.adapter.listauso.base.CategoriaLojaProdutoListUsoAdapterBase;


public class CategoriaLojaProdutoListUsoAdapter extends CategoriaLojaProdutoListUsoAdapterBase {
	
	public CategoriaLojaProdutoListUsoAdapter(List<CategoriaLojaProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
