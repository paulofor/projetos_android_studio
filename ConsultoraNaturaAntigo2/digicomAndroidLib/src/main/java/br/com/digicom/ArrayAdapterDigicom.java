package br.com.digicom;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ArrayAdapterDigicom<T> extends ArrayAdapter<T> {

	public ArrayAdapterDigicom(Context context, int textViewResourceId,
			List<T> objects) {
		super(context, textViewResourceId, objects);
	}

	/*
	public ArrayAdapterDigicom(Context context, int textViewResourceId) {
		super(context, textViewResourceId);

	}
	*/
	
	public View getDropDownView (int position, View convertView, ViewGroup parent) {
		View v =  super.getDropDownView(position, convertView, parent);
		return v;
	}
	

}
