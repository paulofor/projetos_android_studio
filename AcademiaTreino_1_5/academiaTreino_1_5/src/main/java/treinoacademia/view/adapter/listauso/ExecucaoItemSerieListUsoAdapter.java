package  treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.view.adapter.listauso.base.ExecucaoItemSerieListUsoAdapterBase;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;


public class ExecucaoItemSerieListUsoAdapter extends ExecucaoItemSerieListUsoAdapterBase {
	
	
	// Precisa disso
	public ExecucaoItemSerieListUsoAdapter(List<ExecucaoItemSerie> lista, DigicomContexto context) {
		super(lista, context);
	}

	@Override
	protected void montaItemLista(int posicao,ExecucaoItemSerie item, View saida) {
		TextView txtExercicioExecucao = (TextView) saida.findViewById(R.id.txtExercicioExecucao);
		txtExercicioExecucao.setText(item.getCorrenteExercicio_ReferenteA().getTitulo());
	}
	
}
