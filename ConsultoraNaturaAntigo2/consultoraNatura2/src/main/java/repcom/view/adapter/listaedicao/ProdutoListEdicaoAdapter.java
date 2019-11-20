package  repcom.view.adapter.listaedicao;

import java.util.List;

import repcom.app.R;
import repcom.modelo.Produto;
import repcom.view.adapter.listaedicao.base.ProdutoListEdicaoAdapterBase;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class ProdutoListEdicaoAdapter extends ProdutoListEdicaoAdapterBase {
	
	
	public ProdutoListEdicaoAdapter(List<Produto> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}

	@Override
	protected void montaItemLista(int position, Produto item, View saida) {
		TextView txtNomeProduto = (TextView) saida.findViewById(R.id.txtNomeProduto);
		txtNomeProduto.setText(item.getNome());
	}
	
	
	
	
}
