package  repcom.view.adapter.listaedicao;

import java.util.List;

import repcom.modelo.CategoriaProduto;
import repcom.view.adapter.listaedicao.base.CategoriaProdutoListEdicaoAdapterBase;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class CategoriaProdutoListEdicaoAdapter extends CategoriaProdutoListEdicaoAdapterBase {
	
	
	public CategoriaProdutoListEdicaoAdapter(List<CategoriaProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
