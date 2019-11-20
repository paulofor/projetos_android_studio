package  treinoacademia.view.adapter.listaedicao.base;

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
import treinoacademia.modelo.ExecucaoItemSerie;

import br.com.digicom.layout.ItemLista;
import br.com.digicom.layout.IItemLista;
import br.com.digicom.quadro.IQuadroListaEdicao;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.BaseListFragment;
import treinoacademia.tela.quadro.base.ExecucaoItemSerieQuadroListaBase;

public abstract class ExecucaoItemSerieListEdicaoAdapterBase extends DCBaseAdapter implements OnTouchListener, OnLongClickListener{
	
	private Context context;
	private List<ExecucaoItemSerie> lista;

	
	private Vibrator v = null;
	private boolean edicao = false;


	protected void setElementoTela(IFragment quadro, int recurso) {
		BaseListFragment frag = (BaseListFragment) getQuadroLista();
		frag.setElementoTela(quadro, recurso);
	}
	public ExecucaoItemSerieListEdicaoAdapterBase(List<ExecucaoItemSerie> lista, IQuadroListaEdicao origem, Context context ) {
		this.context = context;
		this.lista = lista;
		this.setRaiz(origem);
		setEdicao(); // Forcando para fazer edicao - alterar se for necessario depois.
	}
	public void setEdicao() {
		v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		edicao = true;
	}
	
	public Context getContextAndroid() {
		return context;
	}

	protected int getColor(int valor) {
		return context.getResources().getColor(valor);
	}
	protected IFragment getOrigem() {
		return (IFragment) getQuadroLista();
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
		ExecucaoItemSerie item = lista.get(position);
		
		View saida = LayoutInflater.from(context).inflate(getLayout(), null);
		if (!(saida instanceof IItemLista))
			throw new RuntimeException("Alterar layout raiz de R.layout.item_execucao_item_serie para br.com.digicom.layout.IItemLista");
		((IItemLista)saida).setItem(item);
		this.setTela(saida);
		montaItemLista(position,item, saida);
		/*
		TextView nomeTxt = (TextView) saida.findViewById(R.id.Nome);
		TextView codigoTxt = (TextView) saida.findViewById(R.id.Codigo);
		nomeTxt.setText(item.getNome());
		codigoTxt.setText("" + item.getIdProdutoEstrategico());
		*/
		saida.setOnTouchListener(this);
		saida.setOnLongClickListener(this);
		return saida;
		//return new ProdutoEstrategicoView(this.context, item );
	}
	
	protected int getLayout() {
		return R.layout.item_execucao_item_serie;
	}
	
	protected void montaItemLista(int position, ExecucaoItemSerie item, View saida) {
		throw new UnsupportedOperationException("Fazer override de montaItemLista em "  + this.getClass().getSimpleName() + " ou verificar se nao esta sendo chamada via super. Arquivo de visao padrao eh: R.layout.item_execucao_item_serie");
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent avent) {
	 	ItemLista objeto = (ItemLista) view;
		ExecucaoItemSerie item = (ExecucaoItemSerie) objeto.getItem();
		getQuadroLista().onToqueTela(item);
		return false;
	}
	
	@Override
	public boolean onLongClick(View view) {
		ItemLista objeto = (ItemLista) view;
		ExecucaoItemSerie item = (ExecucaoItemSerie) objeto.getItem();
		if (edicao) {
			v.vibrate(500);
		}
		((IQuadroListaEdicao)getQuadroLista()).onToqueLongoTela(item);
		return false;
	}
	
	

}
