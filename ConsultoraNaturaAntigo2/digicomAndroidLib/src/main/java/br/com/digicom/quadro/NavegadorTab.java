package br.com.digicom.quadro;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

public class NavegadorTab implements TabListener {

	private ViewPager mViewPager =  null;
	private Fragment quadro = null;
	
	public void setViewPager(ViewPager view) {
		this.mViewPager = view;
	}
	public void setFragment(Fragment frag) {
		this.quadro = frag;
	}
	
	
	@Override
	public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1) {
	}
	
	@Override
	public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1) {
	}
	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
		//acessaQuadro();
	}
	
	private void acessaQuadro() {
		quadro.getFragmentManager()
	    .beginTransaction()
	    .detach(quadro)
	    .attach(quadro)
	    .commit();
	}

}
