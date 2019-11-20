package repcom.tela.listaedicao;

import java.util.List;

import repcom.modelo.Cliente;
import repcom.modelo.Venda;
import repcom.servico.VendaServico;
import repcom.tela.listaedicao.base.VendaListaEdicaoBase;
import repcom.tela.trata.impl.VendaQuadroTrata;
import android.content.Context;
import br.com.digicom.quadro.IFragmentEdicao;

public class ClienteListaVenda extends VendaListaEdicaoBase {

	
	
	@Override
	public String getTituloTela() {
		return "Vendas";
	}

	

	@Override
	protected IFragmentEdicao criaQuadroTrata() {
		VendaQuadroTrata quadro = new VendaQuadroTrata();
		return quadro;
	}

	

	

}
