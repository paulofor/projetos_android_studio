package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.PedidoFornecedor;
import repcom.view.adapter.listaedicao.base.PedidoFornecedorListEdicaoAdapterBase;

public class PedidoFornecedorListEdicaoAdapter extends PedidoFornecedorListEdicaoAdapterBase {
	
	
	public PedidoFornecedorListEdicaoAdapter(List<PedidoFornecedor> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
