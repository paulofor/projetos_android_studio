package  treinoacademia.view.adapter.listaedicao;

import java.util.List;

import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.view.adapter.listaedicao.base.ExecucaoItemSerieListEdicaoAdapterBase;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class ExecucaoItemSerieListEdicaoAdapter extends ExecucaoItemSerieListEdicaoAdapterBase {
	
	
	public ExecucaoItemSerieListEdicaoAdapter(List<ExecucaoItemSerie> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
