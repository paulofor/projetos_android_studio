package  repcom.view.adapter;

import java.util.List;

import repcom.app.R;
import repcom.modelo.ContatoCliente;
import repcom.view.adapter.base.ContatoClienteListAdapterBase;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class ContatoClienteListAdapter extends ContatoClienteListAdapterBase {
	
	public ContatoClienteListAdapter(List<ContatoCliente> lista, Context context) {
		super(lista, context);
	}

	@Override
	protected void montaItemLista(int posicao, ContatoCliente item, View saida) {
		String dataStr = item.getDataContatoDDMMAAAA();
		TextView txtLinha1ContatoCliente = (TextView) saida.findViewById(R.id.txtLinha1ContatoCliente);
		txtLinha1ContatoCliente.setText(dataStr);
	}
	
	
}
