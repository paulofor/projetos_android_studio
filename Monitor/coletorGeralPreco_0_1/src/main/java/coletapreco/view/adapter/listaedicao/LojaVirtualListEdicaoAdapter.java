package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.LojaVirtual;
import coletapreco.view.adapter.listaedicao.base.LojaVirtualListEdicaoAdapterBase;

public class LojaVirtualListEdicaoAdapter extends LojaVirtualListEdicaoAdapterBase {
	
	
	public LojaVirtualListEdicaoAdapter(List<LojaVirtual> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
