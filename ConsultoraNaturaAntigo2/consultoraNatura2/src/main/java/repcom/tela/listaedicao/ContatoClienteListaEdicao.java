package repcom.tela.listaedicao;

import java.util.List;

import repcom.modelo.Cliente;
import repcom.modelo.ContatoCliente;
import repcom.servico.ContatoClienteServico;
import repcom.tela.listaedicao.base.ContatoClienteListaEdicaoBase;
import repcom.tela.trata.impl.ContatoClienteQuadroTrata;
import android.content.Context;
import br.com.digicom.quadro.IFragmentEdicao;
import br.com.digicom.quadro.QuadroException;

public class ContatoClienteListaEdicao extends ContatoClienteListaEdicaoBase {

	

	
	
	@Override
	protected IFragmentEdicao criaQuadroTrata() {
		ContatoClienteQuadroTrata trata = new ContatoClienteQuadroTrata();
		return trata;
	}

	@Override
	public String getTituloTela() {
		return "Contatos";
	}
	
}
