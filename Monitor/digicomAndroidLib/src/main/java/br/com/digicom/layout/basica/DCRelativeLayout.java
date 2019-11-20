package br.com.digicom.layout.basica;

import br.com.digicom.layout.DCILayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class DCRelativeLayout extends RelativeLayout implements DCILayout{

	public DCRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public DCRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public DCRelativeLayout(Context context) {
		super(context);
	}
	
	
}
