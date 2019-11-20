package  treinoacademia.view.adapter.listauso;

import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.view.adapter.listauso.base.DiaTreinoListUsoAdapterBase;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.quadro.IFragment;


public class DiaTreinoListUsoAdapter extends DiaTreinoListUsoAdapterBase {
	

	private int tamLista = 0;
	
	public DiaTreinoListUsoAdapter(List<DiaTreino> lista, DigicomContexto context) {
		super(lista, context);
		tamLista = lista.size();
	}

	@Override
	protected void montaItemLista(int posicao, DiaTreino item, View saida) {
		//  x = tam - posicao
		IFragment quadro = this.getQuadroLista();
		if ("DiaTreinoListaUso".equals(quadro.getClass().getSimpleName())) {
			montaItemPadrao(posicao,item,saida);
		}
		if ("DiaTreinoListaPorSerie".equals(quadro.getClass().getSimpleName())) {
			montaItemListaPorSerie(posicao,item,saida);
		}
	}
	@Override
	protected ResourceObj getLayoutItem() {
		IFragment quadro = this.getQuadroLista();
		if ("DiaTreinoListaUso".equals(quadro.getClass().getSimpleName())) {
			return new ResourceObj(R.layout.item_dia_treino,"R.layout.item_dia_treino");
		}
		if ("DiaTreinoListaPorSerie".equals(quadro.getClass().getSimpleName())) {
			return new ResourceObj(R.layout.item_dia_treino_por_serie,"R.layout.item_dia_treino_por_serie");
		}
		return null;
	}
	
	
	
	private void montaItemPadrao(int posicao, DiaTreino item, View saida) {
		int dias = tamLista - posicao;
		String dadoTela = String.format("%02d", dias) + " ) " + item.getTituloTela();
		this.setTextoTextView(R.id.txtDataTreino, "R.id.txtDataTreino", dadoTela);
		this.setTempo(R.id.txtTempoDiaTreino, "R.id.txtTempoDiaTreino", item.getTempoConsumido());
	}

	private void montaItemListaPorSerie(int posicao, DiaTreino item, View saida) {
		this.setData(R.id.txtDataTreinoPorSerie, "R.id.txtDataTreinoPorSerie", item.getData());
		this.setTempo(R.id.txtTempoDia, "R.id.txtTempoDia", item.getTempoConsumido());
	}
	
}
