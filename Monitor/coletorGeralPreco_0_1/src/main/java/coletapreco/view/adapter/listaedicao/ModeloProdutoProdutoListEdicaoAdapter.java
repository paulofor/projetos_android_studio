package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.ModeloProdutoProduto;
import coletapreco.view.adapter.listaedicao.base.ModeloProdutoProdutoListEdicaoAdapterBase;

public class ModeloProdutoProdutoListEdicaoAdapter extends ModeloProdutoProdutoListEdicaoAdapterBase {
	
	
	public ModeloProdutoProdutoListEdicaoAdapter(List<ModeloProdutoProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
