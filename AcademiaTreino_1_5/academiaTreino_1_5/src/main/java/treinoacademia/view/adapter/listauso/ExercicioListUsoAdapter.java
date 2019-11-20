package  treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.Exercicio;
import treinoacademia.view.adapter.listauso.base.ExercicioListUsoAdapterBase;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;


public class ExercicioListUsoAdapter extends ExercicioListUsoAdapterBase {
	
	public ExercicioListUsoAdapter(List<Exercicio> lista, DigicomContexto context) {
		super(lista, context);
	}

	@Override
	protected void montaItemLista(int posicao, Exercicio item, View saida) {
		TextView nomeTxt = (TextView) saida.findViewById(R.id.txtPrincipal);
		nomeTxt.setText("" + item.getTitulo());
		
		TextView subtituloTxt = (TextView) saida.findViewById(R.id.txtSubtitulo);
		subtituloTxt.setText("" + item.getSubtitulo());
	}
	
	
}
