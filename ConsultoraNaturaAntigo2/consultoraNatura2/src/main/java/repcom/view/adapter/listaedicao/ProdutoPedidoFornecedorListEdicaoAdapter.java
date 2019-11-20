package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.ProdutoPedidoFornecedor;
import repcom.view.adapter.listaedicao.base.ProdutoPedidoFornecedorListEdicaoAdapterBase;

public class ProdutoPedidoFornecedorListEdicaoAdapter extends ProdutoPedidoFornecedorListEdicaoAdapterBase {
	
	
	public ProdutoPedidoFornecedorListEdicaoAdapter(List<ProdutoPedidoFornecedor> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
