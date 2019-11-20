package br.com.digicom.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import br.com.digicom.modelo.DCIObjetoDominio;

public class ItemLista extends LinearLayout {
	
	private DCIObjetoDominio item;
	
	public void setItem(DCIObjetoDominio item) {
		this.item = item;
	}
	public DCIObjetoDominio getItem() {
		return this.item;
	}

	public ItemLista(Context context) {
		super(context);
		setup();
	}
	public ItemLista(Context context, AttributeSet attrs, int defStyle) {
		super(context,attrs,defStyle);
		setup();
	}
	
	public ItemLista(Context context, AttributeSet attrs) {
		super(context,attrs);
		setup();
	}
	
	private void setup() {
		//final float scale = getContext().getResources().getDisplayMetrics().density;
		//int pixels = (int) (48 * scale + 0.5f);
		//this.setMinimumHeight(pixels);
	}
	
	public static DCIObjetoDominio getItem(View view) {
		View parent = (View)view.getParent();
		while (parent!=null && !(parent instanceof ItemLista)) {
			parent = (View) parent.getParent();
		}
		if (parent!=null) {
			return ((ItemLista)parent).getItem();
		} else {
			return null;
		}
	}
	
}
