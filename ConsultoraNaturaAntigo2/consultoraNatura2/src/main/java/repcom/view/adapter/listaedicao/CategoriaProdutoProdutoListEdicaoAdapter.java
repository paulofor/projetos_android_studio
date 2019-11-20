package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.CategoriaProdutoProduto;
import repcom.view.adapter.listaedicao.base.CategoriaProdutoProdutoListEdicaoAdapterBase;

public class CategoriaProdutoProdutoListEdicaoAdapter extends CategoriaProdutoProdutoListEdicaoAdapterBase {
	
	
	public CategoriaProdutoProdutoListEdicaoAdapter(List<CategoriaProdutoProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
