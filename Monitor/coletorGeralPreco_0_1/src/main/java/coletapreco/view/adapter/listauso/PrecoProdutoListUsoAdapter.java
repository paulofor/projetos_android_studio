package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.PrecoProduto;
import coletapreco.view.adapter.listauso.base.PrecoProdutoListUsoAdapterBase;


public class PrecoProdutoListUsoAdapter extends PrecoProdutoListUsoAdapterBase {
	
	public PrecoProdutoListUsoAdapter(List<PrecoProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
