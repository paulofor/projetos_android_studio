package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.LojaVirtual;
import coletapreco.view.adapter.listauso.base.LojaVirtualListUsoAdapterBase;


public class LojaVirtualListUsoAdapter extends LojaVirtualListUsoAdapterBase {
	
	public LojaVirtualListUsoAdapter(List<LojaVirtual> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
