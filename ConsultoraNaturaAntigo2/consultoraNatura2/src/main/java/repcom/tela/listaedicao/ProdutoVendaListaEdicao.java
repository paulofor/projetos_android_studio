package repcom.tela.listaedicao;

import java.util.List;

import repcom.tela.listaedicao.base.ProdutoVendaListaEdicaoBase;
import repcom.tela.trata.impl.ProdutoVendaQuadroTrata;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.DialogListFragment;
import br.com.digicom.quadro.IFragmentEdicao;

public class ProdutoVendaListaEdicao extends ProdutoVendaListaEdicaoBase {

	@Override
	public String getTituloTela() {
		return "Produto Venda";
	}

	@Override
	public void onDialogNegativeClick(List<DCIObjetoDominio> listaEscolhidos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IFragmentEdicao criaQuadroTrata() {
		return new ProdutoVendaQuadroTrata();
	}

	
	
}
