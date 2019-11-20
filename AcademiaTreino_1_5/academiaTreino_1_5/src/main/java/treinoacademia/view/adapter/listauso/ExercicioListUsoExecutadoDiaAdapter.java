package treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.modelo.Exercicio;
import treinoacademia.view.adapter.listauso.base.ExercicioListUsoAdapterBase;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.layout.ResourceObj;

public class ExercicioListUsoExecutadoDiaAdapter extends ExercicioListUsoAdapterBase {

	public ExercicioListUsoExecutadoDiaAdapter(List<Exercicio> lista, DigicomContexto context) {
		super(lista, context);
	}

	@Override
	protected ResourceObj getLayoutItem() {
		return new ResourceObj(R.layout.item_exercicio_execucao_novo,"R.layout.item_exercicio_execucao_novo");
	}

	@Override
	protected void montaItemLista(int posicao, Exercicio item, View saida) {
		if (item==null) return;
		TextView txtNomeExercicio = (TextView)  saida.findViewById(R.id.txtNomeExercicio);
		txtNomeExercicio.setText(item.getTitulo());
		List<ExecucaoItemSerie> lista = item.getListaExecucaoItemSerie_Executado();
		
		colocandoDadosNaTela(lista.get(0),lista.get(1),lista.get(2));
	}

	
	
	private void colocandoDadosNaTela(ExecucaoItemSerie exec1, ExecucaoItemSerie exec2, ExecucaoItemSerie exec3) {
		this.setTextoTextView(R.id.txtExercicioRepeticao1, "R.id.txtExercicioRepeticao1", exec1.getQuantidadeRepeticao());
		this.setTextoTextView(R.id.txtExercicioCarga1, "R.id.txtExercicioCarga1", "  x  "+ exec1.getCargaUtilizadaTela());
		this.setTextoTextView(R.id.txtExercicioHorario1, "R.id.txtExercicioHorario1", " : " +exec1.getDataHoraInicioHHMMSS());
		
		this.setTextoTextView(R.id.txtExercicioRepeticao2, "R.id.txtExercicioRepeticao2", exec2.getQuantidadeRepeticao());
		this.setTextoTextView(R.id.txtExercicioCarga2, "R.id.txtExercicioCarga2", "  x  "+ exec2.getCargaUtilizadaTela());
		this.setTextoTextView(R.id.txtExercicioHorario2, "R.id.txtExercicioHorario2", " : " + exec2.getDataHoraInicioHHMMSS());
		
		this.setTextoTextView(R.id.txtExercicioRepeticao3, "R.id.txtExercicioRepeticao3", exec3.getQuantidadeRepeticao());
		this.setTextoTextView(R.id.txtExercicioCarga3, "R.id.txtExercicioCarga3", "  x  "+ exec3.getCargaUtilizadaTela());
		this.setTextoTextView(R.id.txtExercicioHorario3, "R.id.txtExercicioHorario3", " : " + exec3.getDataHoraInicioHHMMSS());
	}
}
