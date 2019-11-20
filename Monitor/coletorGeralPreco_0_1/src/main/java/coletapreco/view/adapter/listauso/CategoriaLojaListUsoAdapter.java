package  coletapreco.view.adapter.listauso;

import java.util.List;

import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.CategoriaLoja;
import coletapreco.view.adapter.listauso.base.CategoriaLojaListUsoAdapterBase;


public class CategoriaLojaListUsoAdapter extends CategoriaLojaListUsoAdapterBase {
	
	public CategoriaLojaListUsoAdapter(List<CategoriaLoja> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
