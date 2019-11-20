package treinoacademia.tela.listaedicao;

import treinoacademia.app.Constantes;
import treinoacademia.tela.listaedicao.base.SerieTreinoListaEdicaoBase;
import treinoacademia.tela.quadro.FabricaFragment;
import treinoacademia.tela.trata.impl.SerieTreinoQuadroTrata;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.BundleFragment;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;

public class SerieTreinoListaEdicao extends SerieTreinoListaEdicaoBase {
	
	@Override
	protected IFragmentEdicao criaQuadroTrata(BundleFragment bundle) {
		IFragment fragmento = FabricaFragment.getInstancia().getSerieTreinoQuadroTrata(bundle);
		return (IFragmentEdicao) fragmento;
	}
	
	/*
	@Override
	public void chamaCriaItem() {
		IFragmentEdicao quadro = criaQuadroTrata();
		SerieTreino nova = FabricaVo.criaSerieTreino();
		nova = insereObjetoPrincipal(nova);
		nova = (SerieTreino) ((ServicoLocal)servico).atribuiUsuario(nova);
		nova = servico.inicializaItemTela(nova,getContext());
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_SERIE, nova);
		bundle.setObjeto(Constantes.CHAVE_ALTERACAO, !nova.getSomenteMemoria());
		quadro.setBundle(bundle);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}
	*/
	
	/*
	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
		DiaTreinoListaPorSerie quadro = new DiaTreinoListaPorSerie();
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_SERIE, obj);
		quadro.setBundle(bundle);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}
	*/
	
	



	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_SERIE_TREINO, obj);
		bundle.setObjeto(Constantes.CHAVE_ALTERACAO, true);
		IFragment quadro = (SerieTreinoQuadroTrata) FabricaFragment.getInstancia().getSerieTreinoQuadroTrata(bundle);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}



	@Override
	public String getTituloTela() {
		return "Series";
	}

	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
