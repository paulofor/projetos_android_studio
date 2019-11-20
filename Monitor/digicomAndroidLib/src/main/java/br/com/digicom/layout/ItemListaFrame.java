package br.com.digicom.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import br.com.digicom.R;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.DCIObjetoDominio;

public class ItemListaFrame extends ItemLista {

	private ViewGroup principal = null;
	
	/*
	private void posicionaFilhos() {
		View child = null;
		final int count = getChildCount();
		principal = (ViewGroup) this.findViewById(R.id.lytPrincipal);
		while (getChildAt(1)!=null) {
			child = getChildAt(1);
		    this.removeViewAt(1);
		    principal.addView(child);
		}
	}
	*/

	public ItemListaFrame(Context context) {
		this(context, null);
		DCLog.ciclo(this, "ItemListaFrame(Context context, AttributeSet attrs, int defStyle)");
	}
	public ItemListaFrame(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		DCLog.ciclo(this, "ItemListaFrame(Context context, AttributeSet attrs, int defStyle)");
	}
	public ItemListaFrame(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        DCLog.ciclo(this, "ItemListaFrame(Context context, AttributeSet attrs, int defStyle)");
        this.inflateInterno(context);
	}

	@Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        //Forward these calls to the content view
        DCLog.ciclo(this, "addView(View child, int index, ViewGroup.LayoutParams params)");
        DCLog.ciclo(child, "child");
        if (child instanceof DCILayout) {
        	super.addView(child, index, params);
        	return;
        }
		//principal = (LinearLayout) findViewById(R.id.lytPrincipal);
		//if (principal==null) super.addView(child, index, params);
		//principal = (LinearLayout) findViewById(R.id.lytPrincipal);
		//this.removeView(child);
		principal.addView(child, index, params);
    }
	
	
	
	@Override
	protected void onAttachedToWindow() {
		DCLog.ciclo(this, "onAttachedToWindow()");
		super.onAttachedToWindow();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		DCLog.ciclo(this, "onDraw(Canvas canvas)");
		super.onDraw(canvas);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		DCLog.ciclo(this, "onMeasure(int widthMeasureSpec, int heightMeasureSpec)");
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		DCLog.ciclo(this, "onLayout(boolean changed, int l, int t, int r, int b)");
		super.onLayout(changed, l, t, r, b);
	}
	
	
	private void inflateInterno(Context contexto) {
		DCLog.ciclo(this, "inflateInterno(Context contexto)");
		LayoutInflater.from(contexto).inflate(R.layout.item_lista_cartao, this);
		principal = (LinearLayout) findViewById(R.id.lytPrincipal);
	}
	
	
	

	
	/*
	protected boolean addViewInLayout(View child, int index, LayoutParams params) {
		DCLog.d(DCLog.DISPLAY, this, "addView*");
		return super.addViewInLayout(child, index, params);
	}

	protected boolean addViewInLayout(View child, int index,LayoutParams params, boolean preventRequestLayout) {
		DCLog.d(DCLog.DISPLAY, this, "addView*");
		return super.addViewInLayout(child, index, params, preventRequestLayout);
	}

	

	
	protected void attachLayoutAnimationParameters(View child,
			LayoutParams params, int index, int count) {
		// TODO Auto-generated method stub
		super.attachLayoutAnimationParameters(child, params, index, count);
	}

	
	protected void attachViewToParent(View child, int index, LayoutParams params) {
		// TODO Auto-generated method stub
		super.attachViewToParent(child, index, params);
	}

	@Override
	public void addView(View child) {
		DCLog.d(DCLog.DISPLAY, this, "addView*");
		super.addView(child);
		if (principal!=null) principal.addView(child);
	}

	@Override
	public void addView(View child, int index) {
		DCLog.d(DCLog.DISPLAY, this, "addView*");
		super.addView(child, index);
		if (principal!=null)
			principal.addView(child, index);
	}

	

	@Override
	public void addView(View child, int width, int height) {
		DCLog.d(DCLog.DISPLAY, this, "addView*");
		super.addView(child, width, height);
		if (principal!=null) principal.addView(child, width, height);
	}

	@Override
	public void addView(View child, LayoutParams params) {
		// Esse que e chamado.
		DCLog.d(DCLog.DISPLAY, this, "addView");
		super.addView(child, params);
		if (principal!=null) principal.addView(child, params);
	}

	@Override
	public void addView(View child, int index, LayoutParams params) {
		DCLog.d(DCLog.DISPLAY, this, "addView*");
		super.addView(child, index, params);
		if (principal!=null) principal.addView(child, params);
	}

	public static DCIObjetoDominio getItem(View view) {
		View parent = (View) view.getParent();
		while (parent != null && !(parent instanceof ItemLista)) {
			parent = (View) parent.getParent();
		}
		if (parent != null) {
			return ((ItemLista) parent).getItem();
		} else {
			return null;
		}
	}
	*/
}
