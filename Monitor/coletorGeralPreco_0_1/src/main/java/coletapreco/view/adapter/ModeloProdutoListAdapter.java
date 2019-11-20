package  coletapreco.view.adapter;

import java.util.List;

import android.content.Context;
import coletapreco.modelo.ModeloProduto;
import coletapreco.view.adapter.base.ModeloProdutoListAdapterBase;

public class ModeloProdutoListAdapter extends ModeloProdutoListAdapterBase {
	
	public ModeloProdutoListAdapter(List<ModeloProduto> lista, Context context) {
		super(lista, context);
	}
	
}
