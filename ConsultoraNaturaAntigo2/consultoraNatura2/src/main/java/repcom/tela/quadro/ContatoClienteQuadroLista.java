
package  repcom.tela.quadro;

import repcom.modelo.Cliente;
import repcom.tela.quadro.base.ContatoClienteQuadroListaBase;
import repcom.tela.trata.impl.ContatoClienteQuadroTrata;
import br.com.digicom.quadro.IFragmentEdicao;

public class ContatoClienteQuadroLista extends  ContatoClienteQuadroListaBase{
	
	Cliente cliente = null;
	
	public void setCliente(Cliente valor) {
		this.cliente = valor;
	}

	


	

}