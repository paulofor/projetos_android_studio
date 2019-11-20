package repcom.tela.listaedicao;

import java.util.List;

import repcom.modelo.CategoriaProduto;
import repcom.modelo.Cliente;
import repcom.modelo.ClienteInteresseCategoria;
import repcom.servico.CategoriaProdutoServico;
import repcom.servico.ClienteInteresseCategoriaServico;
import repcom.servico.FabricaServico;
import repcom.tela.listaedicao.base.ClienteInteresseCategoriaListaEdicaoBase;
import repcom.tela.trata.impl.ClienteInteresseCategoriaQuadroTrata;
import repcom.view.custom.CategoriaProdutoDialog;
import android.content.Context;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.DialogListFragment;
import br.com.digicom.quadro.DialogListListener;
import br.com.digicom.quadro.IFragmentEdicao;
import br.com.digicom.quadro.QuadroException;

public class ClienteInteresseCategoriaListaEdicao extends ClienteInteresseCategoriaListaEdicaoBase  {

	private Cliente cliente = null;
	private List<CategoriaProduto> listaCategoria = null;
	
	public void setCliente(Cliente valor) {
		cliente = valor;
	}

	@Override
	protected List<ClienteInteresseCategoria> getListaCorrente(Context contexto, ClienteInteresseCategoriaServico servico) {
		if (cliente==null) {
			throw new QuadroException(this,"Precisa setar o Cliente");
		}
		List<ClienteInteresseCategoria> listaSaida = servico.getPorAssociadaCliente(contexto, cliente.getId());
		return listaSaida;
	}
	
	@Override
	protected IFragmentEdicao criaQuadroTrata() {
		ClienteInteresseCategoriaQuadroTrata trata = new ClienteInteresseCategoriaQuadroTrata();
		return trata;
	}
	
	/*
	public void insereItem() {
		DialogListListener listener = (DialogListListener) this;
		DialogListFragment dialog = new CategoriaProdutoDialog();
		dialog.setListaItens((List)listaCategoria);
		dialog.setDialogListListener(listener);
		dialog.show(getActivity().getSupportFragmentManager(), "ListaCategoriaDialog");
	}
	*/

	@Override
	protected DialogListFragment criaDialog() {
		return new CategoriaProdutoDialog();
	}

	@Override
	protected List getListaItensDialog() {
		CategoriaProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
		List lista = srv.getAllTela(this.getContext().getContext());
		return lista;
	}

	@Override
	public void onDialogPositiveClick(List<DCIObjetoDominio> listaEscolhidos) {
		this.getServico().atualizaRelacionamento(cliente, listaEscolhidos);
		this.atualizaListaTela();
	}

	@Override
	public String getTituloTela() {
		return "Interesses";
	}

	
	
}
