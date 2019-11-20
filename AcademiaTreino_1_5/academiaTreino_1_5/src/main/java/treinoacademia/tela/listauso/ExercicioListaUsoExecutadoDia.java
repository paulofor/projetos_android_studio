package treinoacademia.tela.listauso;

import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.modelo.Exercicio;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.servico.ExercicioServico;
import treinoacademia.servico.FabricaServico;
import treinoacademia.tela.listauso.base.ExercicioListaUsoBase;
import treinoacademia.view.adapter.listauso.ExercicioListUsoExecutadoDiaAdapter;
import treinoacademia.view.adapter.listauso.base.ExercicioListUsoAdapterBase;
import android.content.Context;
import br.com.digicom.activity.DigicomContexto;

public class ExercicioListaUsoExecutadoDia extends ExercicioListaUsoBase {
	
	private DiaTreino dia = null;
	

	
	public void setDiaTreino(DiaTreino valor) {
		dia = valor;
	}
	@Override
	public String getTituloTela() {
		return dia.getTituloTela();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	protected List<Exercicio> getListaCorrente(Context contexto, ExercicioServico servico) {
		return dia.getListaExercicio();
	}

	@Override
	protected ExercicioListUsoAdapterBase getAdapter(List<Exercicio> lista, DigicomContexto dContexto) {
		return new ExercicioListUsoExecutadoDiaAdapter(lista,dContexto);
	}
	@Override
	public void extraiBundle() {
		dia = (DiaTreino) this.getBundle().getObjeto(Constantes.CHAVE_DIA_TREINO);
	}
	

	
	
}
