package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.app.R;
import repcom.modelo.CategoriaProduto;
import repcom.view.adapter.listauso.base.CategoriaProdutoListUsoAdapterBase;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;


public class CategoriaProdutoListUsoAdapter extends CategoriaProdutoListUsoAdapterBase {
	
	public CategoriaProdutoListUsoAdapter(List<CategoriaProduto> lista, DigicomContexto context) {
		super(lista, context);
	}

	@Override
	protected void montaItemLista(int posicao, CategoriaProduto item, View saida) {
		TextView txtNomeCategoria = (TextView) saida.findViewById(R.id.txtNomeCategoria);
		txtNomeCategoria.setText(item.getNome());
	}
	
	
}
