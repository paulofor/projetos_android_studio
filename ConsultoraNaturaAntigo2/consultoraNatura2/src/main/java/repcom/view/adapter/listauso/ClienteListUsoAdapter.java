package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.app.R;
import repcom.modelo.Cliente;
import repcom.servico.ClienteServico;
import repcom.servico.FabricaServico;
import repcom.view.adapter.listauso.base.ClienteListUsoAdapterBase;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;


public class ClienteListUsoAdapter extends ClienteListUsoAdapterBase {
	
	private ClienteServico servico = null;
	
	public ClienteListUsoAdapter(List<Cliente> lista, DigicomContexto context) {
		super(lista, context);
		servico = FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
	}

	@Override
	protected void montaItemLista(int posicao, Cliente item, View saida) {
		TextView nomeCliente = (TextView) saida.findViewById(R.id.nomeCliente);
		item.setContexto(this.getContextDigicom());
		servico.getFiltro().setItem(item);
		servico.PreencheDerivadaAgendaTel(getContextDigicom());
		nomeCliente.setText(item.getNome());
	}
	
	
}
