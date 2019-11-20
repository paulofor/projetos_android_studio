package  treinoacademia.view.adapter;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.view.adapter.base.SerieTreinoListAdapterBase;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class SerieTreinoListAdapter extends SerieTreinoListAdapterBase {
	
	public SerieTreinoListAdapter(List<SerieTreino> lista, Context context) {
		super(lista, context);
	}

	/*
	@Override
	protected void montaItemLista(int posicao, SerieTreino item, View saida) {
		TextView nomeTxt = (TextView) saida.findViewById(R.id.NomeSerieTreino);
		nomeTxt.setText("" + item.getDataInicialDDMMAAAA());
		TextView SituacaoSerie = (TextView) saida.findViewById(R.id.SituacaoSerie);
		SituacaoSerie.setText((item.getAtiva()?"ativa":"inativa"));
	}
	*/
	
	
}
