package  treinoacademia.view.adapter.listaedicao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import treinoacademia.app.R;
import treinoacademia.modelo.SerieTreino;
import treinoacademia.view.adapter.listaedicao.base.SerieTreinoListEdicaoAdapterBase;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import br.com.digicom.quadro.IQuadroListaEdicao;

public class SerieTreinoListEdicaoAdapter extends SerieTreinoListEdicaoAdapterBase {
	
	
	@Override
	protected void montaItemLista(int posicao, SerieTreino item, View saida) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        Timestamp dataStr1 = item.getDataPrimeiraExecucao();
        String periodoStr = null;
        String dataUlt = item.getDataUltimaExecucaoDDMMAAAA();
        if (dataStr1==null) {
        	periodoStr = "Ainda nao teve execucao";
        	retiraEspacoTexto(saida,R.id.txtQtdeExecucao,"R.id.txtQtdeExecucao");
        } else {
        	String dataStr = formato.format(item.getDataPrimeiraExecucao());
        	periodoStr = "De " + dataStr + " ate " + dataUlt ;
        	setText(saida,"Executado " + item.getQtdeExecucao() + " vezes" ,R.id.txtQtdeExecucao,"R.id.txtQtdeExecucao");
        	
        }
		
		SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm:ss");
		setText(saida,periodoStr,R.id.txtDataPeriodo,"R.id.txtDataPeriodo");
		setText(saida,item.getListaItemSerie_Possui().size() + " exercicios",R.id.txtQtdeExercicio,"R.id.txtQtdeExercicio");
	}

	private void setText(View tela, String texto, int recurso, String nomeRecurso) {
		TextView txt =  (TextView) tela.findViewById(recurso);
		txt.setText(texto);
	}
	private void retiraEspacoTexto(View tela, int recurso, String nomeRecurso) {
		TextView txt =  (TextView) tela.findViewById(recurso);
		txt.setVisibility(TextView.GONE);
	}
	
	
	
	public SerieTreinoListEdicaoAdapter(List<SerieTreino> lista, IQuadroListaEdicao origem, Context context) {
		super(lista, origem,  context);
	}
	
	
}
