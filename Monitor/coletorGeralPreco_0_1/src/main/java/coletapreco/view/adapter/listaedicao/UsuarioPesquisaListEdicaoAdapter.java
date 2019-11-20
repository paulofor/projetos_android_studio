package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.app.R;
import coletapreco.modelo.UsuarioPesquisa;
import coletapreco.view.adapter.listaedicao.base.UsuarioPesquisaListEdicaoAdapterBase;

public class UsuarioPesquisaListEdicaoAdapter extends UsuarioPesquisaListEdicaoAdapterBase {
	
	
	public UsuarioPesquisaListEdicaoAdapter(List<UsuarioPesquisa> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}

	@Override
	protected void montaItemLista(int position, UsuarioPesquisa item, View saida) {
		TextView txtNomeObjetoPesquisa = (TextView) saida.findViewById(R.id.txtNomeObjetoPesquisa);
		txtNomeObjetoPesquisa.setText(item.getCorrenteNaturezaProduto_Pesquisa().getNomeNaturezaProduto());
	}
	
	
	
}
