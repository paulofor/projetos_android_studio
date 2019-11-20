package  repcom.view.adapter.listauso;

import java.util.List;

import repcom.modelo.Estoque;
import repcom.view.adapter.listauso.base.EstoqueListUsoAdapterBase;
import br.com.digicom.activity.DigicomContexto;


public class EstoqueListUsoAdapter extends EstoqueListUsoAdapterBase {
	
	public EstoqueListUsoAdapter(List<Estoque> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
