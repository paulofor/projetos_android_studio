package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.PalavraProduto;
import coletapreco.view.adapter.listaedicao.base.PalavraProdutoListEdicaoAdapterBase;

public class PalavraProdutoListEdicaoAdapter extends PalavraProdutoListEdicaoAdapterBase {
	
	
	public PalavraProdutoListEdicaoAdapter(List<PalavraProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
