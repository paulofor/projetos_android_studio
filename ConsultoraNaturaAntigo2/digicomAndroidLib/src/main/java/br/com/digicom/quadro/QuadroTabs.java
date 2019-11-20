package br.com.digicom.quadro;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import br.com.digicom.R;
import br.com.digicom.adapter.TabAdapter;


public abstract class QuadroTabs extends BaseFragment {

	private ViewPager mViewPager = null;
	private TabAdapter adapter = null;
	
	public abstract boolean validaDadosQuadro();
	
	public void limpezaTabs(Fragment fonte) {
		final ActionBar actionBar = fonte.getActivity().getActionBar();
		actionBar.removeAllTabs();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	}
	
	private void criaTabs() {
		final ActionBar actionBar = this.getActivity().getActionBar();

		//if (actionBar.getTabCount() > 1) return;
	    // Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				getActivity().getActionBar().setSelectedNavigationItem(position);
			}
		});
	    for (int i = 0; i < adapter.getCount(); i++) {
	    	Tab tab = actionBar.newTab();
	    	NavegadorTab navegador = new NavegadorTab();
	    	navegador.setViewPager(mViewPager);
	    	navegador.setFragment(adapter.getItem(i));
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
	protected final int getLayoutTela() {
		return R.layout.quadro_tabs;
	}

	@Override
	protected void inicializaServicos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inicioJogoTela() {
		// TODO Auto-generated method stub
		
	}
	
	

}
