package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.PrecoProduto;
import coletapreco.view.adapter.listaedicao.base.PrecoProdutoListEdicaoAdapterBase;

public class PrecoProdutoListEdicaoAdapter extends PrecoProdutoListEdicaoAdapterBase {
	
	
	public PrecoProdutoListEdicaoAdapter(List<PrecoProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
