package  repcom.view.adapter.listaedicao;

import java.util.List;

import repcom.app.R;
import repcom.modelo.ClienteInteresseCategoria;
import repcom.view.adapter.listaedicao.base.ClienteInteresseCategoriaListEdicaoAdapterBase;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class ClienteInteresseCategoriaListEdicaoAdapter extends ClienteInteresseCategoriaListEdicaoAdapterBase {
	
	
	public ClienteInteresseCategoriaListEdicaoAdapter(List<ClienteInteresseCategoria> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}

	@Override
	protected void montaItemLista(int posicao, ClienteInteresseCategoria item, View saida) {
		TextView linha1 = (TextView) saida.findViewById(R.id.txtLinha1ClienteInteresseCategoria);
		linha1.setText(item.getCategoriaProduto_Associada().getNome());
	}
	
	
	
}
