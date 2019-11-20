package  repcom.view.adapter.listaedicao;

import java.util.List;

import repcom.app.R;
import repcom.modelo.ContatoCliente;
import repcom.view.adapter.listaedicao.base.ContatoClienteListEdicaoAdapterBase;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class ContatoClienteListEdicaoAdapter extends ContatoClienteListEdicaoAdapterBase {
	
	
	public ContatoClienteListEdicaoAdapter(List<ContatoCliente> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}

	@Override
	protected void montaItemLista(int posicao, ContatoCliente item, View saida) {
		TextView txtLinha1ContatoCliente = (TextView) saida.findViewById(R.id.txtLinha1ContatoCliente);
		txtLinha1ContatoCliente.setText(item.getDataContatoDDMMAAAA());
	}
	
	
}
