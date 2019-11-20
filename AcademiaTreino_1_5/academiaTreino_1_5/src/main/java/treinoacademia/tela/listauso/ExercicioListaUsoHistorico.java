package treinoacademia.tela.listauso;

import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.app.R;
import treinoacademia.modelo.Exercicio;
import treinoacademia.servico.ExercicioServico;
import treinoacademia.tela.listauso.base.ExercicioListaUsoBase;
import treinoacademia.tela.quadro.FabricaFragment;
import android.content.Context;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.BundleFragment;
import br.com.digicom.quadro.IFragment;

public class ExercicioListaUsoHistorico extends ExercicioListaUsoBase {

	
	
	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_EXERCICIO, obj);
		//IFragment quadro = FabricaFragment.getInstancia().getDiaTreinoListaUsoHistoricoExercicio(bundle);
		IFragment quadro = FabricaFragment.getInstancia().getExercicioGraficoFragment(bundle);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}

	@Override
	protected List<Exercicio> getListaCorrente(Context contexto, ExercicioServico servico) {
		return servico.ListaAtivosNoMomento(this.getDCContext());
	}

	@Override
	public String getTituloTela() {
		return "Historico de Exercicios Ativos";
	}

	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.lista_uso_padrao_card,"R.layout.lista_uso_padrao_card");
		return recurso;
	}

}
