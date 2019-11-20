package  repcom.view.adapter;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroLista;
import repcom.modelo.ParcelaVenda;
import repcom.view.adapter.base.ParcelaVendaListAdapterBase;

public class ParcelaVendaListAdapter extends ParcelaVendaListAdapterBase {
	
	public ParcelaVendaListAdapter(List<ParcelaVenda> lista, Context context) {
		super(lista, context);
	}
	
}
