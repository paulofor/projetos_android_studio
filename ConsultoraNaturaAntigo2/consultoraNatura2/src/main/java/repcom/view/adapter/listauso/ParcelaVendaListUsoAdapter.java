package  repcom.view.adapter.listauso;

import java.util.List;
import br.com.digicom.activity.DigicomContexto;
import repcom.modelo.ParcelaVenda;
import repcom.view.adapter.listauso.base.ParcelaVendaListUsoAdapterBase;


public class ParcelaVendaListUsoAdapter extends ParcelaVendaListUsoAdapterBase {
	
	public ParcelaVendaListUsoAdapter(List<ParcelaVenda> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
