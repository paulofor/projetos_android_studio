package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.LojaNatureza;
import coletapreco.view.adapter.listaedicao.base.LojaNaturezaListEdicaoAdapterBase;

public class LojaNaturezaListEdicaoAdapter extends LojaNaturezaListEdicaoAdapterBase {
	
	
	public LojaNaturezaListEdicaoAdapter(List<LojaNatureza> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
