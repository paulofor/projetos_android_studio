package br.com.digicom.layout;

import android.view.View;
import br.com.digicom.quadro.BaseFragment;

public class UtilLayout {

	/*
	public static BaseFragment getBaseFragmentParent(View item) {
		View parent = (View)item.getParent();
		while (parent!=null && !(parent instanceof BaseFragment)) {
			parent = (View) parent.getParent();
		}
		if (parent!=null) {
			return (BaseFragment)parent;
		} else {
			return null;
		}
	}
	*/
	
	public static ItemLista getItemListaParent(View item) {
		View parent = (View)item.getParent();
		while (parent!=null && !(parent instanceof ItemLista)) {
			parent = (View) parent.getParent();
		}
		if (parent!=null) {
			return (ItemLista)parent;
		} else {
			return null;
		}
	}
}
