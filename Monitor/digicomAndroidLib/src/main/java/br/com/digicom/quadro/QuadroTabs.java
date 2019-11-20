package br.com.digicom.quadro;


import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import br.com.digicom.R;
import br.com.digicom.adapter.TabAdapter;
import br.com.digicom.layout.ResourceObj;


public abstract class QuadroTabs extends BaseFragment {

	private ViewPager mViewPager = null;
	private TabAdapter adapter = null;
	private List<IFragment> listaFragment = null;
	
	public abstract boolean validaDadosQuadro();
	
	public void limpezaTabs(Fragment fonte) {
		final ActionBar actionBar = fonte.getActivity().getActionBar();
		actionBar.removeAllTabs();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	}
	
	private void criaTabs() {
		final ActionBar actionBar = this.getActivity().getActionBar();

		if (actionBar.getTabCount() == adapter.getCount()) return;
	    // Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				Activity activity = getActivity();
				ActionBar bar = activity.getActionBar();
				bar.setSelectedNavigationItem(position);
			}
		});
	    listaFragment = new ArrayList<IFragment>();
	    for (int i = 0; i < adapter.getCount(); i++) {
	    	Tab tab = actionBar.newTab();
	    	NavegadorTab navegador = new NavegadorTab();
	    	navegador.setViewPager(mViewPager);
	    	IFragment fragmento = (IFragment) adapter.getItem(i);
	    	listaFragment.add(fragmento);
	    	fragmento.extraiBundle();
	    	navegador.setFragment((Fragment) fragmento);
    		tab.setText(adapter.getPageTitle(i));
	    	tab.setTabListener(navegador);
	    	actionBar.addTab(tab);
	    }
	    inicializaAdapter(); // Precisa disso para a segunda passagem ?
	    //actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.palette3)));
	}
	
	@Override
	public void onResume() {
		super.onResume();
		criaTabs();
	}
	
	private void inicializaAdapter() {
		adapter = getTabAdapter();
		adapter.setDigicomContexto(getContext());
	    mViewPager = (ViewPager) this.getTela().findViewById(getIdPager());
	    mViewPager.setAdapter(adapter);
	}
	
	@Override
	protected void inicializaItensArquitetura() {
		//Alterar depois, acho que esta passando duas vezes
		inicializaAdapter();
	}
	
	//protected abstract int getIdPager();
	protected final int getIdPager() {
		return R.id.pager;
	}
	
	protected abstract TabAdapter getTabAdapter();

	@Override
	protected final ResourceObj getLayoutTela() {
		return new ResourceObj(R.layout.quadro_tabs,"R.layout.quadro_tabs");
	}

	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicioJogoTela() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extraiBundle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void onAlteraQuadro() {
		for (IFragment frag : this.listaFragment) {
			frag.onAlteraQuadro();
		}
	}

	@Override
	public String getTituloTela() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
