package treinoacademia.app;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{

	private Context ctx;
	private List<String> lista;
	
	public MyAdapter(Context ctx, List<String> lista) {
		this.ctx = ctx;
		this.lista = lista;
	}
	
	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int arg0) {
		return lista.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		TextView tv = new TextView(ctx);
		tv.setText(lista.get(arg0));
		tv.setTextColor(Color.WHITE);
		return tv;
	}

}
