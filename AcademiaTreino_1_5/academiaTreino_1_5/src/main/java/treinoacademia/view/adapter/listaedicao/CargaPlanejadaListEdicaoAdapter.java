package  treinoacademia.view.adapter.listaedicao;

import java.util.List;

import treinoacademia.modelo.CargaPlanejada;
import treinoacademia.view.adapter.listaedicao.base.CargaPlanejadaListEdicaoAdapterBase;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class CargaPlanejadaListEdicaoAdapter extends CargaPlanejadaListEdicaoAdapterBase {
	
	
	public CargaPlanejadaListEdicaoAdapter(List<CargaPlanejada> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
