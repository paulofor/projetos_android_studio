package  coletapreco.view.adapter;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroLista;
import coletapreco.modelo.Palavra;
import coletapreco.view.adapter.base.PalavraListAdapterBase;

public class PalavraListAdapter extends PalavraListAdapterBase {
	
	public PalavraListAdapter(List<Palavra> lista, Context context) {
		super(lista, context);
	}
	
}
