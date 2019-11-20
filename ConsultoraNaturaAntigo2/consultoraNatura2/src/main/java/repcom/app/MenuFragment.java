package repcom.app;

// Geracao Inicial

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseMenuFragment;



public class MenuFragment extends BaseMenuFragment {

	public final static String ITEM_MENU_SINCRONIZACAO = "Sincronizacao";
	public final static String ITEM_MENU_RESUMO_MES = "ResumoMes";
	public final static String ITEM_MENU_CLIENTES = "Clientes";
	public final static String ITEM_MENU_PRODUTOS = "Produtos";
	public final static String ITEM_MENU_VENDAS = "Vendas";
	
	
	protected void setEscutadores() {
		//this.setOnClick(R.id.btnTreinar,ITEM_MENU_DIA_TREINO);
		this.setOnClick(R.id.btnCliente, ITEM_MENU_CLIENTES);
		this.setOnClick(R.id.btnSincronizacao, ITEM_MENU_SINCRONIZACAO);
		this.setOnClick(R.id.btnResumoMes, ITEM_MENU_RESUMO_MES);
		this.setOnClick(R.id.btnProduto, ITEM_MENU_PRODUTOS);
		this.setOnClick(R.id.btnVenda, ITEM_MENU_VENDAS);
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
		return "Consultora Natura";
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
