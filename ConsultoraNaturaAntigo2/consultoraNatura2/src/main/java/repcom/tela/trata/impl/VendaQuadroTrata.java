package repcom.tela.trata.impl;

import br.com.digicom.quadro.ResultadoValidacao;
import repcom.app.R;
import repcom.modelo.Cliente;
import repcom.modelo.Venda;
import repcom.tela.listaedicao.ProdutoVendaListaEdicao;
import repcom.tela.trata.base.VendaQuadroTrataBase;


public class VendaQuadroTrata extends VendaQuadroTrataBase {

	private ProdutoVendaListaEdicao interno = null;
	
	
	
	@Override
	protected void carregaElementosTela() {
		interno = new ProdutoVendaListaEdicao();
		if (this.getItem()==null) {
			throw new RuntimeException("Venda está null");
		}
		interno.setVenda((Venda)this.getItem());
		this.setElementoTela(interno, R.id.lytListaProdutoVenda);
	}
	
	@Override
	protected ResultadoValidacao validaCamposTela() {
		ResultadoValidacao novo = new ResultadoValidacao();
		novo.setProsseguir(true);
		return novo;
	}
	
}
