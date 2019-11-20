package coletapreco.view.custom;

import br.com.digicom.adapter.TabAdapter;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.quadro.QuadroTabs;
import coletapreco.view.adapter.tabs.OportunidadeTabsAdapter;

public class OportunidadeTabs extends QuadroTabs {

	@Override
	public String getTituloTela() {
		return "Oportunidades de Produto";
	}

	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void inicioJogoTela() {
		// TODO Auto-generated method stub

	}

	@Override
	protected TabAdapter getTabAdapter() {
		return new OportunidadeTabsAdapter(this, this.getFragmentManager());
	}

	@Override
	public boolean validaDadosQuadro() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onAlteraQuadro() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResourceObj getRecurso() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
