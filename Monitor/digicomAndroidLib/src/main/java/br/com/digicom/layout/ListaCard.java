package br.com.digicom.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ListView;

public class ListaCard extends ListView {

	public ListaCard(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//setAtributos();
	}
	public ListaCard(Context context, AttributeSet attrs) {
		super(context, attrs);
		//setAtributos();
	}
	public ListaCard(Context context) {
		super(context);
	}
	
	
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		setAtributos();
	}
	private void setAtributos() {
		this.setDivider(null);
		this.setCacheColorHint(Color.TRANSPARENT);
		this.setDividerHeight(getPixel(10));
		this.setSelector(android.R.color.transparent);
		this.setPadding(0, getPixel(10),0, getPixel(10));
	}

	private int getPixel(int dps) {
		float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, getResources().getDisplayMetrics());
		return Math.round(pixels);
	}
}
