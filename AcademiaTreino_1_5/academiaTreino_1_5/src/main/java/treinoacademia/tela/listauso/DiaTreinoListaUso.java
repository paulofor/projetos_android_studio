package treinoacademia.tela.listauso;

import java.util.List;

import treinoacademia.app.Constantes;
import treinoacademia.modelo.DiaTreino;
import treinoacademia.servico.DiaTreinoServico;
import treinoacademia.tela.listauso.base.DiaTreinoListaUsoBase;
import treinoacademia.tela.quadro.FabricaFragment;
import android.content.Context;
import br.com.digicom.animacao.TrocaQuadro;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.quadro.BundleFragment;

public class DiaTreinoListaUso extends DiaTreinoListaUsoBase {

	
	
	@Override
	protected void inicializaServicos() {
		
	}

	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
		//ExercicioListaUsoExecutadoDia quadro = new ExercicioListaUsoExecutadoDia();
		//quadro.setDiaTreino(dia);
		DiaTreino dia = (DiaTreino) obj;
		dia.setContexto(getContext());
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_DIA_TREINO, dia);
		ExercicioListaUsoExecutadoDia quadro = (ExercicioListaUsoExecutadoDia) FabricaFragment.getInstancia().getExercicioListaUsoExecutadoDia(bundle);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}

	@Override
	public String getTituloTela() {
		return "Dias de Treino";
	}

	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}

	// Passar para template depois ? Por causa dos derivadas.
	@Override
	public List<DiaTreino> getListaCorrente(Context contexto,DiaTreinoServico servico) {
		List<DiaTreino> saida = servico.getAllTela(contexto);
		for (DiaTreino item : saida) {
			item.setContexto(getContext());
		}
		return saida;
		
	}

}
