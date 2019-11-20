package br.com.digicom.layout;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.widget.DatePicker;

public class DatePickerUtil {

	public static Timestamp getValor(DatePicker datePicker) {
		int year = datePicker.getYear();
		int mes = datePicker.getMonth();
		int dia = datePicker.getDayOfMonth();
		Calendar calendar = new GregorianCalendar(year, mes, dia);
		calendar.getTimeInMillis();
		return new Timestamp(calendar.getTimeInMillis());
	}
}
