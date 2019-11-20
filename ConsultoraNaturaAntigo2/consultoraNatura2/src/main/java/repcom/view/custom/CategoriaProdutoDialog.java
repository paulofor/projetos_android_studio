package repcom.view.custom;

import java.util.List;

import repcom.modelo.CategoriaProduto;
import repcom.modelo.ClienteInteresseCategoria;
import br.com.digicom.quadro.DialogListFragment;

public class CategoriaProdutoDialog extends DialogListFragment{

	
	

	@Override
	protected boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		CategoriaProduto categoria = (CategoriaProduto) item;
		ClienteInteresseCategoria interesse = (ClienteInteresseCategoria) relacionamento;
		return interesse.getIdCategoriaProdutoA()==categoria.getIdCategoriaProduto();
	}
	
	
	

}
