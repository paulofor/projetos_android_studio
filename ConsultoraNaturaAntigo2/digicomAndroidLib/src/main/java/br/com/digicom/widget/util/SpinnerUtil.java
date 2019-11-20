package br.com.digicom.widget.util;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import br.com.digicom.modelo.DCIObjetoDominio;

public class SpinnerUtil {

	/*
	public static Spinner<T> montaSpinner(Spinner spn, Class<T> type, List lista, Context contexto) {
		ArrayAdapter<T> dataAdapter = new ArrayAdapter<T>(contexto, android.R.layout.simple_spinner_item, lista);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn.setAdapter(dataAdapter);

		return spn;
	}
	*/
	public static void setIdObj(long id, Spinner spn) {
		SpinnerAdapter adapter = spn.getAdapter();
		for (int i=0; i<adapter.getCount(); i++) {
			DCIObjetoDominio obj = (DCIObjetoDominio) adapter.getItem(i);
			if (obj.getId()==id) {
				spn.setSelection(i);
				break;
			}
		}
	}
	
	public static void setLista(List lista, Spinner spn, Context contexto) {
		ArrayAdapter dataAdapter = new ArrayAdapter(contexto, android.R.layout.simple_spinner_item, lista);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn.setAdapter(dataAdapter);
	}
}
