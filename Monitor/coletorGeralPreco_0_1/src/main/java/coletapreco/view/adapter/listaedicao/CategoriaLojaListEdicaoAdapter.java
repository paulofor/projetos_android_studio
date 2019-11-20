package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.CategoriaLoja;
import coletapreco.view.adapter.listaedicao.base.CategoriaLojaListEdicaoAdapterBase;

public class CategoriaLojaListEdicaoAdapter extends CategoriaLojaListEdicaoAdapterBase {
	
	
	public CategoriaLojaListEdicaoAdapter(List<CategoriaLoja> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
