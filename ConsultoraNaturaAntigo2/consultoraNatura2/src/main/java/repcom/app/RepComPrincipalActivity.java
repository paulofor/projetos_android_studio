
package  repcom.app;

import java.util.ArrayList;
import java.util.List;

import repcom.app.base.PrincipalActivityBase;
import repcom.modelo.MesAno;
import repcom.servico.FabricaServico;
import repcom.servico.MesAnoServico;
import repcom.tela.listauso.CategoriaProdutoListaUso;
import repcom.tela.listauso.ClienteListaUso;
import repcom.view.MesAnoView;
import br.com.digicom.activity.ItemNavigator;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.IFragment;

public class RepComPrincipalActivity extends PrincipalActivityBase {

	@Override
	public void onButtonClickMenu(String acao) {
		exibeTela(acao);

	}

	public void exibeTela(String acao) {
		
		/*
		
		if (acao.compareTo(MenuFragment.ITEM_MENU_ADM_SERIE)==0) {
			IFragment newFragment = new SerieTreinoQuadroLista();
			alteraQuadro(newFragment);
		}
		
		*/
		
	}
	
	@Override
	public void onButtonClickLista(String acao, DCIObjetoDominio obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<ItemNavigator> getListaItem() {
		List<ItemNavigator> lista = new ArrayList<ItemNavigator>();
		lista.add(new ItemNavigator("Clientes"));
		lista.add(new ItemNavigator("Produtos"));
		lista.add(new ItemNavigator("Resumo Mês"));
		return lista;
	}

	@Override
	protected IFragment getFragment(int posicao) {
		if (posicao==0) {
			return new ClienteListaUso();
		}
		if (posicao==1) {
			return new CategoriaProdutoListaUso();
		}
		if (posicao==2) {
			MesAnoServico servico = FabricaServico.getInstancia().getMesAnoServico(FabricaServico.TIPO_SQLITE);
			MesAno itemTela = servico.inicializaItemTela(getContexto());
			return new MesAnoView(itemTela);
		}
		return null;
		
	}
	
	
	
}