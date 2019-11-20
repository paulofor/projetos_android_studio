
package  coletapreco.app;

import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.IFragment;
import coletapreco.app.base.PrincipalActivityBase;
import coletapreco.modelo.Usuario;
import coletapreco.servico.FabricaServico;
import coletapreco.servico.UsuarioServico;
import coletapreco.tela.listaedicao.UsuarioPesquisaListaEdicao;
import coletapreco.view.custom.OportunidadeTabs;

public class ColetaPrecoPrincipalActivity extends PrincipalActivityBase {

	@Override
	public void onButtonClickMenu(String acao) {
		exibeTela(acao);

	}
	
	

	public void exibeTela(String acao) {
		
		/*
		if (acao.compareTo(MenuFragment.ITEM_MENU_DIA_TREINO)==0) {
			IFragment newFragment = new DiaTreinoViewCustom();
			alteraQuadro(newFragment);
		}
		if (acao.compareTo(MenuFragment.ITEM_MENU_ADM_SERIE)==0) {
			IFragment newFragment = new SerieTreinoQuadroLista();
			alteraQuadro(newFragment);
		}
		if (acao.compareTo(MenuFragment.ITEM_MENU_ADM_EXERCICIO)==0) {
			IFragment newFragment = new ExercicioQuadroLista();
			alteraQuadro(newFragment);
		}
		*/
		
	
		if (acao.compareTo(MenuFragment.ITEM_MENU_OPORTUNIDADE_DIA)==0) {
			IFragment newFragment = new OportunidadeTabs();
			alteraQuadro(newFragment);
		}
		if (acao.compareTo(MenuFragment.ITEM_MENU_NATUREZA_USUARIO)==0) {
			//UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
			//Usuario usuario = usuarioSrv.getCorrente();
			UsuarioPesquisaListaEdicao frag = new UsuarioPesquisaListaEdicao();
			alteraQuadro(frag);
		}
	}
	
	@Override
	public void onButtonClickLista(String acao, DCIObjetoDominio obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub
		
	}
	
}