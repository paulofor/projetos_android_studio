package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.PrecoProduto;
import repcom.view.adapter.listauso.base.PrecoProdutoListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class PrecoProdutoListUsoAdapter extends PrecoProdutoListUsoAdapterBase {
	
	public PrecoProdutoListUsoAdapter(List<PrecoProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
