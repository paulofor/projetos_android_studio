
package  repcom.tela.quadro;

import repcom.modelo.Cliente;
import repcom.tela.quadro.base.ClienteInteresseCategoriaQuadroListaBase;
import repcom.tela.trata.impl.ClienteInteresseCategoriaQuadroTrata;
import br.com.digicom.quadro.IFragmentEdicao;

public class ClienteInteresseCategoriaQuadroLista extends  ClienteInteresseCategoriaQuadroListaBase{

	Cliente cliente = null;
	
	public void setCliente(Cliente valor) {
		this.cliente = valor;
	}

	@Override
	protected IFragmentEdicao criaQuadroTrata() {
		ClienteInteresseCategoriaQuadroTrata trata = new ClienteInteresseCategoriaQuadroTrata();
		return trata;
	}
	

}