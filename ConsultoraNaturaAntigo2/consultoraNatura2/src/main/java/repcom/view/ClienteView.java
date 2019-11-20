
package  repcom.view;

import repcom.app.R;
import repcom.modelo.Cliente;
import repcom.servico.ClienteInteresseCategoriaServico;
import repcom.servico.ContatoClienteServico;
import repcom.servico.FabricaServico;
import repcom.tela.listaedicao.ClienteInteresseCategoriaListaEdicao;
import repcom.tela.listaedicao.ContatoClienteListaEdicao;
import repcom.view.base.ClienteViewBase;
import android.widget.TextView;

public class ClienteView extends  ClienteViewBase{
	
	TextView txtNomeCliente = null;
	//Layout lytContato = null;
	//Layout lytInteresse = null;
	
	ContatoClienteServico contatoSrv = null;
	ClienteInteresseCategoriaServico interesseCategoriaSrv = null;

	public ClienteView(Cliente item) {
		super(item);
	}

	@Override
	protected void inicializaItensTela() {
		txtNomeCliente = (TextView) getTela().findViewById(R.id.txtNomeCliente);
		//lytContato = (Layout) getTela().findViewById(R.id.lytContato);
		//lytInteresse = (Layout) getTela().findViewById(R.id.lytInteresse);
	}

	
	
	@Override
	protected int getLayoutTela() {
		return R.layout.cliente_view;
	}

	@Override
	protected void inicializaServicos() {
		ContatoClienteServico contatoSrv = FabricaServico.getInstancia().getContatoClienteServico(FabricaServico.TIPO_SQLITE);
		ClienteInteresseCategoriaServico interesseCategoriaSrv = FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
	}

	@Override
	protected void carregaElementosTela() {
		txtNomeCliente.setText(getItem().getNome());
		
		ClienteInteresseCategoriaListaEdicao listaInteresse = new ClienteInteresseCategoriaListaEdicao();
		listaInteresse.setCliente(getItem());
		this.setElementoTela(listaInteresse, R.id.lytInteresse);
		
		ContatoClienteListaEdicao listaContato = new ContatoClienteListaEdicao();
		listaContato.setCliente(getItem());
		this.setElementoTela(listaContato, R.id.lytContato);
	}
	
	
}