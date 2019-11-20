package treinoacademia.app;

// Geracao Inicial

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseMenuFragment;



public class MenuFragment extends BaseMenuFragment {

	//btnTreinar
	
	public final static String ITEM_MENU_DIA_TREINO = "DiaTreino";
	public final static String ITEM_MENU_SINCRONIZACAO = "Sincronizacao";
	public final static String ITEM_MENU_ADM_SERIE = "AdmSerie";
	public final static String ITEM_MENU_ADM_EXERCICIO = "AdmExercicio";
	public static final String ITEM_MENU_HISTORICO_DIA_TREINO = "HistoricoDiaTreino";
	public static final String ITEM_MENU_HISTORICO_EXERCICIO = "HistoricoExercicio";
	
	
	protected void setEscutadores() {
		this.setOnClick(R.id.btnTreinar,ITEM_MENU_DIA_TREINO);
		this.setOnClick(R.id.btnSincronizacao,ITEM_MENU_SINCRONIZACAO);
		this.setOnClick(R.id.btnAdmSerieTreino, ITEM_MENU_ADM_SERIE);
		this.setOnClick(R.id.btnAdmExercicio, ITEM_MENU_ADM_EXERCICIO);
		this.setOnClick(R.id.btnHistoricoDiaTreino, ITEM_MENU_HISTORICO_DIA_TREINO);
		this.setOnClick(R.id.btnHistoricoExercicio, ITEM_MENU_HISTORICO_EXERCICIO);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View tela = inflater.inflate(R.layout.main_menu, container, false);
		return tela;
	}

	protected void setBarraTopo() {
		//final ActionBar topBar = (ActionBar) this.getActivity().findViewById(R.id.topbarMenu);
		//topBar.setTitle("GerProj - Inicial");
	}

	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTituloTela() {
		return "Treino 365";
	}

	@Override
	public View getTela() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAlteraQuadro() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResourceObj getRecurso() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
