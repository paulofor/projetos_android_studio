package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.app.R;
import repcom.modelo.ContatoCliente;
import repcom.view.adapter.listauso.base.ContatoClienteListUsoAdapterBase;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;


public class ContatoClienteListUsoAdapter extends ContatoClienteListUsoAdapterBase {
	
	public ContatoClienteListUsoAdapter(List<ContatoCliente> lista, DigicomContexto context) {
		super(lista, context);
	}

	@Override
	protected void montaItemLista(int posicao, ContatoCliente item, View saida) {
		TextView txtLinha1ContatoCliente = (TextView) saida.findViewById(R.id.txtLinha1ContatoCliente);
		txtLinha1ContatoCliente.setText(item.getDataContatoDDMMAAAA());
	}
	
	
	
}
