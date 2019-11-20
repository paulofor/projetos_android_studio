package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.OportunidadeDia;
import coletapreco.view.adapter.listaedicao.base.OportunidadeDiaListEdicaoAdapterBase;

public class OportunidadeDiaListEdicaoAdapter extends OportunidadeDiaListEdicaoAdapterBase {
	
	
	public OportunidadeDiaListEdicaoAdapter(List<OportunidadeDia> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
