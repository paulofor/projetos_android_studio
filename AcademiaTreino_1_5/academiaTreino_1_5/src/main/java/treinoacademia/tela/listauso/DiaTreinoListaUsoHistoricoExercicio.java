package treinoacademia.tela.listauso;


import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.app.R;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.Exercicio;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.tela.listauso.base.DiaTreinoListaUsoBase;
import treinoacademia.view.adapter.listauso.DiaTreinoListUsoHistoricoExercicioAdapter;
import treinoacademia.view.adapter.listauso.base.DiaTreinoListUsoAdapterBase;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.layout.ResourceObj;

public class DiaTreinoListaUsoHistoricoExercicio extends DiaTreinoListaUsoBase {

	private Exercicio exercicio = null;
	
	
	
	
	
	@Override
	public String getTituloTela() {
		return exercicio.getTituloTela();
	}

	@Override
	protected final ResourceObj getLayoutTela() {
		return new ResourceObj(R.layout.lista_uso_padrao,"R.layout.lista_uso_padrao");
	}

	@Override
	protected DiaTreinoListUsoAdapterBase getAdapter(List<DiaTreino> lista, DigicomContexto dContexto) {
		return new DiaTreinoListUsoHistoricoExercicioAdapter(lista,dContexto);
	}

	@Override
	protected List<DiaTreino> getListaCorrente(Context contexto, DiaTreinoServico servico) {
		servico.getFiltro().setIdExercicio(exercicio.getId());
		List<DiaTreino> listaExercicio = servico.HistoricoExecucaoPorIdExercicio(this.getContext());
		return listaExercicio;
	}

	@Override
	public void extraiBundle() {
		this.exercicio = (Exercicio) this.getBundle().getObjeto(Constantes.CHAVE_EXERCICIO);
	}
	
	
}
