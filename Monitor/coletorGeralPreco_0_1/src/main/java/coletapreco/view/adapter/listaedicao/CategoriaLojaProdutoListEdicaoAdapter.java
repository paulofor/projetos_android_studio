package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.CategoriaLojaProduto;
import coletapreco.view.adapter.listaedicao.base.CategoriaLojaProdutoListEdicaoAdapterBase;

public class CategoriaLojaProdutoListEdicaoAdapter extends CategoriaLojaProdutoListEdicaoAdapterBase {
	
	
	public CategoriaLojaProdutoListEdicaoAdapter(List<CategoriaLojaProduto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
