package repcom.view.adapter.tab;

import repcom.tela.listaedicao.ClienteInteresseCategoriaListaEdicao;
import repcom.tela.listaedicao.ClienteListaVenda;
import repcom.tela.listaedicao.ContatoClienteListaEdicao;
import repcom.view.custom.ClienteTabs;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import br.com.digicom.adapter.TabAdapter;
import br.com.digicom.quadro.QuadroTabs;

public class ClienteTabAdapter extends TabAdapter {

	public ClienteTabAdapter(QuadroTabs quadro, FragmentManager fm) {
		super(quadro, fm);
	}

	@Override
	protected void inicializaItensTab() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getTituloTab(int posicao) {
		switch (posicao) {
		case 0 : 
			return "Contatos";
		case 1 :
			return "Interesses";
		case 2 :
			return "Vendas";
		}
		return null;
	}

	@Override
	public Fragment getItem(int posicao) {
		ClienteTabs quadroTab = (ClienteTabs) this.getQuadroTab();
		switch (posicao) {
		case 0:
			ContatoClienteListaEdicao ctrl0 = new ContatoClienteListaEdicao();
			ctrl0.setCliente(quadroTab.getCliente());
			return ctrl0;
		
		case 1:
			ClienteInteresseCategoriaListaEdicao ctrl1 = new ClienteInteresseCategoriaListaEdicao();
			ctrl1.setCliente(quadroTab.getCliente());
			return ctrl1;
		case 2:
			ClienteListaVenda ctrl2 = new ClienteListaVenda();
			ctrl2.setCliente(quadroTab.getCliente());
			return ctrl2;
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
