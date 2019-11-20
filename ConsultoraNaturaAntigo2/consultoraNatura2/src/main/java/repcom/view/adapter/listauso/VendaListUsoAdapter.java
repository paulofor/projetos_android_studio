package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.Venda;
import repcom.view.adapter.listauso.base.VendaListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class VendaListUsoAdapter extends VendaListUsoAdapterBase {
	
	public VendaListUsoAdapter(List<Venda> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}

