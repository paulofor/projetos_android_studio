package br.com.digicom.widget;

import android.content.Context;
import android.widget.Chronometer;

public class DCCronometro extends Chronometer {

	public DCCronometro(Context context) {
		super(context);
	}
	
	public void zera() {
		this.setText("00:00");
	}

}
