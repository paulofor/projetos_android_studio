package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.ProdutoVenda;
import repcom.view.adapter.listaedicao.base.ProdutoVendaListEdicaoAdapterBase;

public class ProdutoVendaListEdicaoAdapter extends ProdutoVendaListEdicaoAdapterBase {
	
	
	public ProdutoVendaListEdicaoAdapter(List<ProdutoVenda> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
