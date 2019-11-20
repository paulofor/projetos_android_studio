package br.com.digicom.adapter;

import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.quadro.QuadroTabs;

public abstract class TabAdapter extends FragmentStatePagerAdapter{

	protected DigicomContexto contexto = null;
	private QuadroTabs quadro = null;
	
	protected QuadroTabs getQuadroTab() {
		return quadro;
	}
	
	public void setDigicomContexto(DigicomContexto ctx) {
		this.contexto = ctx;
		inicializaItensTab();
	}
	protected abstract void inicializaItensTab();
	protected abstract String getTituloTab(int posicao);
	
	public TabAdapter(QuadroTabs quadro, FragmentManager fm) {
		super(fm);
		this.quadro = quadro;
	}
	
	@Override
	public final CharSequence getPageTitle(int position) {
		return getTituloTab(position);
	}

}
