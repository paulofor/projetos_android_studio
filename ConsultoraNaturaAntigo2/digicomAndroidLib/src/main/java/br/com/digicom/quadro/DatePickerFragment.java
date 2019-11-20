package br.com.digicom.quadro;

import java.util.Calendar;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	private DatePickerListener listener = null;
	private DatePickerDialog dialog = null;
	private boolean apenasDataFutura = false;
	
	private String dataInicio = null;
	
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (this.dataInicio==null) {
			dialog = new DatePickerDialog(getActivity(), this, year, month, day);
		} else {
			//  DD-MM-AAAA
			//  0123456789
			year = Integer.parseInt(dataInicio.substring(6));
			month = Integer.parseInt(dataInicio.substring(3,5)) - 1;
			day =  Integer.parseInt(dataInicio.substring(0,2));
			dialog = new DatePickerDialog(getActivity(), this, year, month, day);
		}
		if (this.apenasDataFutura) {
			dialog.getDatePicker().setMinDate(c.getTimeInMillis());
		}
		return dialog;
	}
	
	public void apenasDataFutura() {
		apenasDataFutura = true;
	}
	
	public void setListener(DatePickerListener dado) {
		listener = dado;
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		this.listener.onDataSet(view, day, month, year);
	}

}
