package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.ModeloProduto;
import coletapreco.view.adapter.listaedicao.base.ModeloProdutoListEdicaoAdapterBase;

public class ModeloProdutoListEdicaoAdapter extends ModeloProdutoListEdicaoAdapterBase {
	
	
	public ModeloProdutoListEdicaoAdapter(List<ModeloProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
