package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.Cliente;
import repcom.view.adapter.listaedicao.base.ClienteListEdicaoAdapterBase;

public class ClienteListEdicaoAdapter extends ClienteListEdicaoAdapterBase {
	
	
	public ClienteListEdicaoAdapter(List<Cliente> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
