package  repcom.view.adapter.listaedicao;

import java.util.List;
import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import repcom.modelo.MesAno;
import repcom.view.adapter.listaedicao.base.MesAnoListEdicaoAdapterBase;

public class MesAnoListEdicaoAdapter extends MesAnoListEdicaoAdapterBase {
	
	
	public MesAnoListEdicaoAdapter(List<MesAno> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
