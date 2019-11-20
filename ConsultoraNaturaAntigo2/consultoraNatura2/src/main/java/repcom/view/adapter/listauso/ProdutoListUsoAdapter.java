package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.app.R;
import repcom.modelo.Produto;
import repcom.view.adapter.listauso.base.ProdutoListUsoAdapterBase;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;


public class ProdutoListUsoAdapter extends ProdutoListUsoAdapterBase {
	
	public ProdutoListUsoAdapter(List<Produto> lista, DigicomContexto context) {
		super(lista, context);
	}

	@Override
	protected void montaItemLista(int posicao, Produto item, View saida) {
		TextView txtNomeProduto = (TextView) saida.findViewById(R.id.txtNomeProduto);
		txtNomeProduto.setText(item.getNome());
	}
	
}
