package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.PagamentoFornecedor;
import repcom.view.adapter.listaedicao.base.PagamentoFornecedorListEdicaoAdapterBase;

public class PagamentoFornecedorListEdicaoAdapter extends PagamentoFornecedorListEdicaoAdapterBase {
	
	
	public PagamentoFornecedorListEdicaoAdapter(List<PagamentoFornecedor> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
