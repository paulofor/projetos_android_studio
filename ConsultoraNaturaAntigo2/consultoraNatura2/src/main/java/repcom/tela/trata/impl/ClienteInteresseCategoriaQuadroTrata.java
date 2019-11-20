package repcom.tela.trata.impl;

import br.com.digicom.quadro.ResultadoValidacao;
import repcom.modelo.Cliente;
import repcom.tela.trata.base.ClienteInteresseCategoriaQuadroTrataBase;

public class ClienteInteresseCategoriaQuadroTrata extends ClienteInteresseCategoriaQuadroTrataBase {

	@Override
	protected ResultadoValidacao validaCamposTela() {
		ResultadoValidacao novo = new ResultadoValidacao();
		novo.setProsseguir(true);
		return novo;
	}

	

}
