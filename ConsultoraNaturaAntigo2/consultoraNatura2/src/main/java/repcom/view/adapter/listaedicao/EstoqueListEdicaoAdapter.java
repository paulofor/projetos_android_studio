package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.Estoque;
import repcom.view.adapter.listaedicao.base.EstoqueListEdicaoAdapterBase;

public class EstoqueListEdicaoAdapter extends EstoqueListEdicaoAdapterBase {
	
	
	public EstoqueListEdicaoAdapter(List<Estoque> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
