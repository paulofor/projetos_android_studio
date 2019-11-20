package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.MesAno;
import repcom.view.adapter.listauso.base.MesAnoListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class MesAnoListUsoAdapter extends MesAnoListUsoAdapterBase {
	
	public MesAnoListUsoAdapter(List<MesAno> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
