package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.PagamentoFornecedor;
import repcom.view.adapter.listauso.base.PagamentoFornecedorListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class PagamentoFornecedorListUsoAdapter extends PagamentoFornecedorListUsoAdapterBase {
	
	public PagamentoFornecedorListUsoAdapter(List<PagamentoFornecedor> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
