package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.PedidoFornecedor;
import repcom.view.adapter.listauso.base.PedidoFornecedorListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class PedidoFornecedorListUsoAdapter extends PedidoFornecedorListUsoAdapterBase {
	
	public PedidoFornecedorListUsoAdapter(List<PedidoFornecedor> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
