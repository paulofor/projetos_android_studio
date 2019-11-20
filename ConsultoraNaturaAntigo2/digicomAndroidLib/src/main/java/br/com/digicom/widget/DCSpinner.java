package br.com.digicom.widget;

import android.content.Context;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import br.com.digicom.modelo.DCIObjetoDominio;

public class DCSpinner extends Spinner {

	public DCSpinner(Context context) {
		super(context);
	}
	
	public void setIdObj(long id) {
		SpinnerAdapter adapter = this.getAdapter();
		for (int i=0; i<adapter.getCount(); i++) {
			DCIObjetoDominio obj = (DCIObjetoDominio) adapter.getItem(i);
			if (obj.getId()==id) {
				this.setSelection(i);
				break;
			}
		}
	}

}
