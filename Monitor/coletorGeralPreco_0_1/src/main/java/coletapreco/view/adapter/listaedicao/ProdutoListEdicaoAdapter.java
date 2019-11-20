package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.Produto;
import coletapreco.view.adapter.listaedicao.base.ProdutoListEdicaoAdapterBase;

public class ProdutoListEdicaoAdapter extends ProdutoListEdicaoAdapterBase {
	
	
	public ProdutoListEdicaoAdapter(List<Produto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
