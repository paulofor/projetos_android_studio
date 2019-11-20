package  treinoacademia.view.adapter.listauso.base;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import br.com.digicom.adapter.DCBaseAdapter;
import android.view.View.OnTouchListener;
import android.view.View.OnLongClickListener;
import android.os.Vibrator;
import treinoacademia.app.R;
import treinoacademia.modelo.DiaTreino;

import br.com.digicom.layout.IItemLista;
import br.com.digicom.quadro.IQuadroLista;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.layout.ResourceObj;
import treinoacademia.tela.quadro.base.DiaTreinoQuadroListaBase;

public abstract class DiaTreinoListUsoAdapterBase extends DCBaseAdapter {
	
	private DigicomContexto context;
	private List<DiaTreino> lista;
	
	private Vibrator v = null;
	private boolean edicao = false;
	
	private ResourceObj resource = null;

	public DiaTreinoListUsoAdapterBase(List<DiaTreino> lista, DigicomContexto context ) {
		this.context = context;
		this.lista = lista;
		setEdicao(); // Forcando para fazer edicao - alterar se for necessario depois.
	}
	
	public void setListaNova(List<DiaTreino> listaNova) {
		this.lista = listaNova;
	}
	
	public void setEdicao() {
		v = (Vibrator) getContextAndroid().getSystemService(Context.VIBRATOR_SERVICE);
		edicao = true;
	}
	
	public Context getContextAndroid() {
		return context.getContext();
	}
	public DigicomContexto getContextDigicom() {
		return context;
	}

	protected int getColor(int valor) {
		return getContextAndroid().getResources().getColor(valor);
	}


	public int getCount() {
		return lista.size();
	}
	public Object getItem(int position) {
		return lista.get(position);
	}
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		DiaTreino item = lista.get(position);
		
		resource = getLayoutItem();
		View saida = LayoutInflater.from(getContextAndroid()).inflate(resource.getCodigo(), null);
		if (!(saida instanceof IItemLista))
			throw new RuntimeException("Alterar layout raiz de " + resource.getNome() + " para br.com.digicom.layout.IItemLista");
		((IItemLista)saida).setItem(item);
		this.setTela(saida);
		montaItemLista(position,item, saida);
		/*
		TextView nomeTxt = (TextView) saida.findViewById(R.id.Nome);
		TextView codigoTxt = (TextView) saida.findViewById(R.id.Codigo);
		nomeTxt.setText(item.getNome());
		codigoTxt.setText("" + item.getIdProdutoEstrategico());
		*/
		//Retirando para fazer o controle na lista
		//saida.setOnTouchListener(this);
		//saida.setOnLongClickListener(this);
		return saida;
		//return new ProdutoEstrategicoView(this.context, item );
	}
	
	protected void montaItemLista(int position, DiaTreino item, View saida) {
		throw new UnsupportedOperationException("Fazer override de montaIItemLista em "  + this.getClass().getSimpleName() + " ou verificar se nao esta sendo chamada via super. Arquivo de visao padrao eh: R.layout.item_dia_treino");
	}
	protected ResourceObj getLayoutItem() {
		return new ResourceObj(R.layout.item_dia_treino,"R.layout.item_dia_treino");
	}
	

}
