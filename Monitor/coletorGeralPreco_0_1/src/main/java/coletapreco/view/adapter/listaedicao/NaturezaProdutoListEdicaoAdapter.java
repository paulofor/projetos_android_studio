package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.NaturezaProduto;
import coletapreco.view.adapter.listaedicao.base.NaturezaProdutoListEdicaoAdapterBase;

public class NaturezaProdutoListEdicaoAdapter extends NaturezaProdutoListEdicaoAdapterBase {
	
	
	public NaturezaProdutoListEdicaoAdapter(List<NaturezaProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
