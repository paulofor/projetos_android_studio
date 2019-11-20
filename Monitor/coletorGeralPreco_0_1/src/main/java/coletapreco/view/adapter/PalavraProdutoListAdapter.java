package  coletapreco.view.adapter;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroLista;
import coletapreco.modelo.PalavraProduto;
import coletapreco.view.adapter.base.PalavraProdutoListAdapterBase;

public class PalavraProdutoListAdapter extends PalavraProdutoListAdapterBase {
	
	public PalavraProdutoListAdapter(List<PalavraProduto> lista, Context context) {
		super(lista, context);
	}
	
}
