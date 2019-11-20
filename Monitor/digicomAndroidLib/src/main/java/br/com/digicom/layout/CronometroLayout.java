package br.com.digicom.layout;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import br.com.digicom.R;
import br.com.digicom.componente.DCICronometro;
import br.com.digicom.widget.DCCronometro;
import br.com.digicom.widget.IDCCronometro;

public class CronometroLayout extends LinearLayout implements DCICronometro{

	private DCCronometro chronometer1 = null;
	
	public CronometroLayout(Context context) {
		super(context);
		setup(context);
	}
	
	public CronometroLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup(context);
	}
	public CronometroLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setup(context);
	}
	
	private void setup(Context context) {
		setOrientation(LinearLayout.HORIZONTAL);
		 LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 inflater.inflate(R.layout.componente_cronometro, this, true);
		 chronometer1 = (DCCronometro) this.findViewById(R.id.chronometer1);
	}

	@Override
	public void setListener(IDCCronometro valor) {
		chronometer1.setListener(valor);
	}

	@Override
	public void zera() {
		chronometer1.zeraRelogio();
	}

	@Override
	public void start() {
		this.chronometer1.start();
	}

	

}
