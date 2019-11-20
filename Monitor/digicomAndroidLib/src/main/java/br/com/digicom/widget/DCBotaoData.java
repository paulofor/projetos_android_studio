package br.com.digicom.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import br.com.digicom.quadro.DatePickerFragment;
import br.com.digicom.quadro.DatePickerListener;

public class DCBotaoData extends Button implements DatePickerListener{
	
	private Activity activity = null;
	private DatePickerFragment dialogoData = new DatePickerFragment();
	private String dataAtual = "";
	
	private DCListenerBotaoData listener = null;
	
	public void setListener(DCListenerBotaoData dado){
		this.listener = dado;
	}
	
	public String getData() {
		return dataAtual;
	}
	public void setActivity(Activity dado) {
		activity = dado;
	}
	
	public DCBotaoData(Context context) {
		super(context);
		inicializaItem(context);
	}
	
	public DCBotaoData(Context context, AttributeSet attrs) {
		super(context, attrs);
		inicializaItem(context);
	}

	public DCBotaoData(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inicializaItem(context);
	}

	
	private void inicializaItem(Context context) {
		this.dialogoData.setListener(this);
		this.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialogoData.setDataInicio(dataAtual);
				if (activity==null) throw new RuntimeException("Precisa setar o activity para usar DCBotaoData");
				dialogoData.show(activity.getFragmentManager(), "datePicker");
			}
		});
	}

	@Override
	public void onDataSet(DatePicker view, int day, int month, int year) {
		dataAtual = String.format("%02d", day) + "/" +String.format("%02d", (month+1)) + "/"	+ year;
		this.setText(dataAtual);
		if (this.listener!=null) listener.onDataSet(dataAtual);
	}
	
	public void setData(String data) {
		dataAtual = data;
		this.setText(dataAtual);
	}
}
