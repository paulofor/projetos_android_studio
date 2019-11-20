package  coletapreco.view.adapter;

import java.util.List;

import android.content.Context;
import coletapreco.modelo.Produto;
import coletapreco.view.adapter.base.ProdutoListAdapterBase;

public class ProdutoListAdapter extends ProdutoListAdapterBase {
	
	public ProdutoListAdapter(List<Produto> lista, Context context) {
		super(lista, context);
	}
	
}
