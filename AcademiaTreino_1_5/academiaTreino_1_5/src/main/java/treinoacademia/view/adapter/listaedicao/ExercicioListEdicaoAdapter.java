package  treinoacademia.view.adapter.listaedicao;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.Exercicio;
import treinoacademia.view.adapter.listaedicao.base.ExercicioListEdicaoAdapterBase;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class ExercicioListEdicaoAdapter extends ExercicioListEdicaoAdapterBase {
	
	
	public ExercicioListEdicaoAdapter(List<Exercicio> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	@Override
	protected void montaItemLista(int posicao, Exercicio item, View saida) {
		TextView nomeTxt = (TextView) saida.findViewById(R.id.txtPrincipal);
		nomeTxt.setText("" + item.getTitulo());
		
		TextView subtituloTxt = (TextView) saida.findViewById(R.id.txtSubtitulo);
		subtituloTxt.setText("" + item.getSubtitulo());
	}
	
}
