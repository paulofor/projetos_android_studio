package br.com.digicom.layout.basica;

import br.com.digicom.layout.DCILayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class DCFrameLayout extends FrameLayout implements DCILayout{
	public DCFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public DCFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public DCFrameLayout(Context context) {
		super(context);
	}
	
	
}
