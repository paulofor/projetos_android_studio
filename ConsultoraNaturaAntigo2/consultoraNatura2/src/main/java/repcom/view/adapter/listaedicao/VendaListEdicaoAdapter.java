package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.Venda;
import repcom.view.adapter.listaedicao.base.VendaListEdicaoAdapterBase;

public class VendaListEdicaoAdapter extends VendaListEdicaoAdapterBase {
	
	
	public VendaListEdicaoAdapter(List<Venda> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
