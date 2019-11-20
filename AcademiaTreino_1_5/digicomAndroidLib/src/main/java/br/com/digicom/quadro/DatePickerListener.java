package br.com.digicom.quadro;

import android.widget.DatePicker;

public interface DatePickerListener {

	void onDataSet(DatePicker view, int day, int month, int year);

}
