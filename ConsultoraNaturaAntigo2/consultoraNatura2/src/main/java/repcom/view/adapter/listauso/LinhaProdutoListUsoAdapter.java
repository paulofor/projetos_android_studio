package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.LinhaProduto;
import repcom.view.adapter.listauso.base.LinhaProdutoListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class LinhaProdutoListUsoAdapter extends LinhaProdutoListUsoAdapterBase {
	
	public LinhaProdutoListUsoAdapter(List<LinhaProduto> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
