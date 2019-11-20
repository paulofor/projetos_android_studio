package  treinoacademia.view.adapter;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.view.ItemSerieView;
import treinoacademia.view.adapter.base.ItemSerieListAdapterBase;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.digicom.layout.ItemLista;
import br.com.digicom.modelo.DCIObjetoDominio;

public class ItemSerieListAdapter extends ItemSerieListAdapterBase {
	
	public ItemSerieListAdapter(List<ItemSerie> lista, Context context) {
		super(lista, context);
	}
	
	
	@Override
	protected void montaItemLista(int posicao, ItemSerie item, View saida) {
		
		TextView txtPrincipal = (TextView) saida.findViewById(R.id.txtPrincipal);
		txtPrincipal.setText("" + item.getExercicio_ExecucaoDe().getTitulo());
		
		TextView txtSubtitulo = (TextView) saida.findViewById(R.id.txtSubtitulo);
		txtSubtitulo.setText("" + item.getExercicio_ExecucaoDe().getSubtitulo());
		
		TextView txtApoio = (TextView) saida.findViewById(R.id.txtApoio);
		txtApoio.setText("Regulagem: " + item.getParametros());
		
		saida.setBackgroundColor(getColor(R.color.palette3));
	}
	/*
	
	private void chamaTelaTrataItem(DCIObjetoDominio obj) {
		ItemSerieView quadro = new ItemSerieView((ItemSerie)obj);
		this.alteraQuadro(quadro);
	}
	*/
}
