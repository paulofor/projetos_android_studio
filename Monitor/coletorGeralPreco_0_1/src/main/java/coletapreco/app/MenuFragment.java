package coletapreco.app;

// Geracao Inicial

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseMenuFragment;


public class MenuFragment extends BaseMenuFragment {

	public final static String ITEM_MENU_OPORTUNIDADE_DIA = "OportunidadeDia";
	public final static String ITEM_MENU_NATUREZA_USUARIO = "NaturezaUsuario";
	
	
	protected void setEscutadores() {
		this.setOnClick(R.id.btnOportunidade,ITEM_MENU_OPORTUNIDADE_DIA);
		this.setOnClick(R.id.btnNaturezaCadastrada,ITEM_MENU_NATUREZA_USUARIO);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View tela = inflater.inflate(R.layout.main_menu, container, false);
		final ActionBar actionBar = this.getActivity().getActionBar();
		actionBar.removeAllTabs();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
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
		// TODO Auto-generated method stub
		return null;
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
