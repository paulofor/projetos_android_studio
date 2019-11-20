package coletapreco.view.adapter.tabs;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import br.com.digicom.adapter.TabAdapter;
import br.com.digicom.quadro.QuadroTabs;
import coletapreco.modelo.NaturezaProduto;
import coletapreco.modelo.Usuario;
import coletapreco.modelo.UsuarioPesquisa;
import coletapreco.servico.FabricaServico;
import coletapreco.servico.UsuarioPesquisaServico;
import coletapreco.servico.UsuarioServico;
import coletapreco.tela.listauso.OportunidadeDiaListaUso;



public class OportunidadeTabsAdapter extends TabAdapter {

	public OportunidadeTabsAdapter(QuadroTabs quadro, FragmentManager fm) {
		super(quadro, fm);
	}



	private List<UsuarioPesquisa> listaTabs = null;
	
	
	
	
	public void inicializaItensTab() {
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		UsuarioPesquisaServico srv = FabricaServico.getInstancia().getUsuarioPesquisaServico(FabricaServico.TIPO_SQLITE);
		listaTabs = srv.getAllTela(contexto.getContext());
	}

	@Override
	public Fragment getItem(int arg0) {
		OportunidadeDiaListaUso quadro = new OportunidadeDiaListaUso();
		quadro.setNaturezaProduto(listaTabs.get(arg0).getNaturezaProduto_Pesquisa());
		return quadro;
	}

	@Override
	public int getCount() {
		return listaTabs.size();
	}

	

	@Override
	protected String getTituloTab(int posicao) {
		NaturezaProduto item = listaTabs.get(posicao).getNaturezaProduto_Pesquisa();
		return item.getNomeNaturezaProduto();
	}
}
