package treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.view.adapter.listauso.base.ExecucaoItemSerieListUsoAdapterBase;
import android.view.View;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.layout.ResourceObj;

public class ExecucaoItemSerieListUsoHistoricoAdapter extends ExecucaoItemSerieListUsoAdapterBase {

	public ExecucaoItemSerieListUsoHistoricoAdapter(List<ExecucaoItemSerie> lista, DigicomContexto context) {
		super(lista, context);
	}

	@Override
	protected void montaItemLista(int posicao, ExecucaoItemSerie item, View saida) {
		//String carga = item.getCargaUtilizadaTela();
		//String repeticao = "" + item.getQuantidadeRepeticao();
		//long exercicio = item.getIdExercicioRa();
		//TextView txtCargaHistorico = (TextView) saida.findViewById(R.id.txtCargaHistorico);
		//txtCargaHistorico.setText(item.getCargaUtilizadaTela());
		//TextView txtRepeticaoHistorico = (TextView) saida.findViewById(R.id.txtRepeticaoHistorico);
		//txtRepeticaoHistorico.setText("" +item.getQuantidadeRepeticao());
	}

	@Override
	protected ResourceObj getLayoutItem() {
		return new ResourceObj(R.layout.item_execucao_item_serie_historico,"R.layout.item_execucao_item_serie_historic");
	}
	
	
}
