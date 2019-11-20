package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.LinhaProduto;
import repcom.view.adapter.listaedicao.base.LinhaProdutoListEdicaoAdapterBase;

public class LinhaProdutoListEdicaoAdapter extends LinhaProdutoListEdicaoAdapterBase {
	
	
	public LinhaProdutoListEdicaoAdapter(List<LinhaProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
