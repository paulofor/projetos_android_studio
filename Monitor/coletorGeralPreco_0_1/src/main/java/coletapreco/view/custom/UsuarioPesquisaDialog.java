package coletapreco.view.custom;

import br.com.digicom.quadro.DialogListFragment;
import coletapreco.modelo.NaturezaProduto;
import coletapreco.modelo.UsuarioPesquisa;

public class UsuarioPesquisaDialog extends DialogListFragment {

	@Override
	protected boolean comparaRelacionamentoComItem(Object item, Object relacionamento) {
		UsuarioPesquisa rel = (UsuarioPesquisa) relacionamento;
		NaturezaProduto obj = (NaturezaProduto) item;
		return (rel.getIdNaturezaProdutoP()==obj.getId());
	}

}
