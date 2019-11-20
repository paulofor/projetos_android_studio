package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.ParcelaVenda;
import repcom.view.adapter.listaedicao.base.ParcelaVendaListEdicaoAdapterBase;

public class ParcelaVendaListEdicaoAdapter extends ParcelaVendaListEdicaoAdapterBase {
	
	
	public ParcelaVendaListEdicaoAdapter(List<ParcelaVenda> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
