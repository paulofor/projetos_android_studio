package br.com.digicom.layout.basica;

import br.com.digicom.layout.DCILayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class DCLinearLayout extends LinearLayout implements DCILayout{

	public DCLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public DCLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public DCLinearLayout(Context context) {
		super(context);
	}
	
	

}
