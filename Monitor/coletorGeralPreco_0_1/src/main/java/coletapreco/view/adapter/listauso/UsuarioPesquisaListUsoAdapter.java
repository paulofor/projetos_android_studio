package  coletapreco.view.adapter.listauso;

import java.util.List;
import br.com.digicom.activity.DigicomContexto;
import coletapreco.modelo.UsuarioPesquisa;
import coletapreco.view.adapter.listauso.base.UsuarioPesquisaListUsoAdapterBase;


public class UsuarioPesquisaListUsoAdapter extends UsuarioPesquisaListUsoAdapterBase {
	
	public UsuarioPesquisaListUsoAdapter(List<UsuarioPesquisa> lista, DigicomContexto context) {
		super(lista, context);
	}
	
}
