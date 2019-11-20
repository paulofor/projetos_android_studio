package  treinoacademia.view.adapter.listaedicao;

import java.util.List;

import treinoacademia.modelo.DiaTreino;
import treinoacademia.view.adapter.listaedicao.base.DiaTreinoListEdicaoAdapterBase;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class DiaTreinoListEdicaoAdapter extends DiaTreinoListEdicaoAdapterBase {
	
	
	public DiaTreinoListEdicaoAdapter(List<DiaTreino> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
