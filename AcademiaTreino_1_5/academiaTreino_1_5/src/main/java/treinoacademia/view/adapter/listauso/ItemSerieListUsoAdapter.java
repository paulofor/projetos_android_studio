package  treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.app.R;
import treinoacademia.modelo.ItemSerie;
import treinoacademia.tela.quadro.FabricaFragment;
import treinoacademia.view.adapter.listauso.base.ItemSerieListUsoAdapterBase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ItemLista;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.BundleFragment;
import br.com.digicom.quadro.IFragment;


public class ItemSerieListUsoAdapter extends ItemSerieListUsoAdapterBase {
	
	public ItemSerieListUsoAdapter(List<ItemSerie> lista, DigicomContexto context) {
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
	
	/*
	private void chamaTelaTrataItem(DCIObjetoDominio obj) {
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_ITEM_SERIE, obj);
		IFragment quadro = FabricaFragment.getInstancia().getItemSerieView(bundle);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}
	*/
	private void chamaTelaTrataItem(DCIObjetoDominio obj) {
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_ITEM_SERIE, obj);
		IFragment quadro = FabricaFragment.getInstancia().getItemSerieView2(bundle);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}

}
