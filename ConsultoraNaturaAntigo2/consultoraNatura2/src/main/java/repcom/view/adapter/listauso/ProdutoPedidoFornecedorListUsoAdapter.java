package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.ProdutoPedidoFornecedor;
import repcom.view.adapter.listauso.base.ProdutoPedidoFornecedorListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class ProdutoPedidoFornecedorListUsoAdapter extends ProdutoPedidoFornecedorListUsoAdapterBase {
	
	public ProdutoPedidoFornecedorListUsoAdapter(List<ProdutoPedidoFornecedor> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
