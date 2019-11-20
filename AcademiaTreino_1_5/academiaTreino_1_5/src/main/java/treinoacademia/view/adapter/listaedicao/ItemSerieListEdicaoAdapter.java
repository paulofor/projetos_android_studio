package  treinoacademia.view.adapter.listaedicao;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.view.adapter.listaedicao.base.ItemSerieListEdicaoAdapterBase;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.log.DCLog;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class ItemSerieListEdicaoAdapter extends ItemSerieListEdicaoAdapterBase {
	
	
	public ItemSerieListEdicaoAdapter(List<ItemSerie> lista,  IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}

	
	@Override
	protected void montaItemLista(int posicao,ItemSerie item, View saida) {
		
		
		if (item.getExercicio_ExecucaoDe()==null) {
			DCLog.d(DCLog.ITEM_NULL, this,"Item Serie " + item.getId() + " sem Exercicio");
		} else {
			TextView txtPrincipal = (TextView) saida.findViewById(R.id.txtPrincipal);
			txtPrincipal.setText("" + item.getExercicio_ExecucaoDe().getTitulo());
		
			TextView txtSubtitulo = (TextView) saida.findViewById(R.id.txtSubtitulo);
			txtSubtitulo.setText("" + item.getExercicio_ExecucaoDe().getSubtitulo());
			if (item.getExercicio_ExecucaoDe().getSubtitulo().length()==0) {
				txtSubtitulo.setVisibility(View.GONE);
			}
		}
		TextView txtApoio = (TextView) saida.findViewById(R.id.txtApoio);
		txtApoio.setText("Regulagem: " + item.getParametros());
		
		saida.setBackgroundColor(getColor(R.color.palette3));
	}
	
	@Override
	protected int getLayout() {
		return R.layout.itemedicao_item_serie;
	}
	
	
}
