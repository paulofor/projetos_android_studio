package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.ProdutoVenda;
import repcom.view.adapter.listauso.base.ProdutoVendaListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class ProdutoVendaListUsoAdapter extends ProdutoVendaListUsoAdapterBase {
	
	public ProdutoVendaListUsoAdapter(List<ProdutoVenda> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
