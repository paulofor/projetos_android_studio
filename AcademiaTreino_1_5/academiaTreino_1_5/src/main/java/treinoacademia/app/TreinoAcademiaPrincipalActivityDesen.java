package treinoacademia.app;

import java.util.ArrayList;
import java.util.List;

import treinoacademia.app.base.PrincipalActivityBase;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.tela.listaedicao.SerieTreinoListaEdicao;
import treinoacademia.tela.listauso.DiaTreinoListaUso;
import treinoacademia.tela.listauso.ExercicioListaUsoHistorico;
import treinoacademia.tela.quadro.ExercicioQuadroLista;
import treinoacademia.tela.quadro.SerieTreinoQuadroLista;
import treinoacademia.view.custom.DiaTreinoViewCustom;
import br.com.digicom.activity.ItemNavigator;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.IFragment;

public class TreinoAcademiaPrincipalActivityDesen extends PrincipalActivityBase 
	implements MenuFragment.OnButtonClick{

	
	@Override
	public void onButtonClickMenu(String acao) {
		exibeTela(acao);

	}

	public void exibeTela(String acao) {
		
		if (acao.compareTo(MenuFragment.ITEM_MENU_DIA_TREINO)==0) {
			
		}
		if (acao.compareTo(MenuFragment.ITEM_MENU_ADM_SERIE)==0) {
			
		}
		if (acao.compareTo(MenuFragment.ITEM_MENU_ADM_EXERCICIO)==0) {
			
		}
		if (acao.compareTo(MenuFragment.ITEM_MENU_HISTORICO_DIA_TREINO)==0) {
		}
		if (acao.compareTo(MenuFragment.ITEM_MENU_HISTORICO_EXERCICIO)==0) {
		}
	
		if (acao.compareTo(MenuFragment.ITEM_MENU_SINCRONIZACAO)==0) {
			Sincronizador sinc = new Sincronizador();
			sinc.setContexto(getApplication());
			sinc.start();
		}
	}
	
	@Override
	public void onButtonClickLista(String acao, DCIObjetoDominio obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicializaServicos() {
		// Não pode ser aqui pode gerar conflito.
		//DiaTreinoServico diaTreinoSrv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
		//diaTreinoSrv.LimpezaBase(this.getContexto());
	}

	@Override
	protected List<ItemNavigator> getListaItem() {
		List<ItemNavigator> listaItens = new ArrayList<ItemNavigator>();
		listaItens.add(new ItemNavigator("Treino"));
		listaItens.add(new ItemNavigator("Series"));
		listaItens.add(new ItemNavigator("Exercicios"));
		listaItens.add(new ItemNavigator("Histórico por data"));
		listaItens.add(new ItemNavigator("Histórico por exercicio"));
		return listaItens;
	}

	@Override
	protected IFragment getFragment(int posicao) {
		IFragment saida = null;
		if (posicao==0) {
			saida = new DiaTreinoViewCustom();
			DiaTreinoServico diaTreinoSrv = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
			DiaTreino diaNovo = diaTreinoSrv.inicializaItemTela(this.getContexto());
			((DiaTreinoViewCustom)saida).setDiaTreino(diaNovo);
		}
		if (posicao==1) {
			saida = new SerieTreinoListaEdicao();
		}
		if (posicao==2) {
			saida = new ExercicioQuadroLista();
		}
		if (posicao==3) {
			saida = new DiaTreinoListaUso();
		}
		if (posicao==4) {
			saida = new ExercicioListaUsoHistorico();
		}
		return saida;
	}
}
