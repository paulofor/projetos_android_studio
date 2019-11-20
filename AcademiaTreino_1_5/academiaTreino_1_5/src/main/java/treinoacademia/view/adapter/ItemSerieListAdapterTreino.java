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
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ItemLista;
import br.com.digicom.modelo.DCIObjetoDominio;

public class ItemSerieListAdapterTreino extends ItemSerieListAdapter {
	
	public ItemSerieListAdapterTreino(List<ItemSerie> lista, Context context) {
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
		
		Button btnTreino = (Button)saida.findViewById(R.id.btnTreino);
		btnTreino.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DCIObjetoDominio obj = ItemLista.getItem((Button)v);
				chamaTelaTrataItem(obj);
			}
		});
		
		ImageView imgConcluido = (ImageView) saida.findViewById(R.id.imgConcluido);
		if (item.getConcluidoNoDia()) {
			btnTreino.setVisibility(View.GONE);
			imgConcluido.setVisibility(View.VISIBLE);
		} else {
			btnTreino.setVisibility(View.VISIBLE);
			imgConcluido.setVisibility(View.GONE);
		}
		
		saida.setBackgroundColor(getColor(R.color.palette3));
	}
	
	
	private void chamaTelaTrataItem(DCIObjetoDominio obj) {
		ItemSerieView quadro = new ItemSerieView((ItemSerie)obj);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}
}
