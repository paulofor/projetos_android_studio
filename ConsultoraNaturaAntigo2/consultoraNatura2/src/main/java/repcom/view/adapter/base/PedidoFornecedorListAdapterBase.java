package  repcom.view.adapter.base;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View.OnTouchListener;
import android.view.View.OnLongClickListener;
import android.os.Vibrator;
import repcom.app.R;
import repcom.modelo.PedidoFornecedor;
import repcom.view.PedidoFornecedorView;
import br.com.digicom.layout.ItemLista;
import br.com.digicom.quadro.IQuadroListaEdicao;
import br.com.digicom.quadro.IFragment;
import repcom.tela.quadro.base.PedidoFornecedorQuadroListaBase;

public abstract class PedidoFornecedorListAdapterBase extends BaseAdapter implements OnTouchListener, OnLongClickListener{
	
	private Context context;
	private List<PedidoFornecedor> lista;
	private IQuadroListaEdicao raiz;
	
	private Vibrator v = null;
	private boolean edicao = false;


	public PedidoFornecedorListAdapterBase(List<PedidoFornecedor> lista, Context context ) {
		this.context = context;
		this.lista = lista;
		//this.raiz = origem;
	}

	
	public Context getContextAndroid() {
		return context;
	}

	protected int getColor(int valor) {
		return context.getResources().getColor(valor);
	}

	
	public void setRaiz(IQuadroListaEdicao valor) {
		raiz = valor;
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
		PedidoFornecedor item = lista.get(position);
		
		View saida = LayoutInflater.from(context).inflate(R.layout.item_pedido_fornecedor, null);
		if (!(saida instanceof ItemLista))
			throw new RuntimeException("Alterar layout raiz de item_pedido_fornecedor.xml para br.com.digicom.layout.ItemLista");
		((ItemLista)saida).setItem(item);
		montaItemLista(position, item, saida);
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
	
	protected void montaItemLista(int posicao, PedidoFornecedor item, View saida) {
		throw new UnsupportedOperationException("Fazer override de montaItemLista em "  + this.getClass().getSimpleName() + " ou verificar se nao esta sendo chamada via super. Arquivo de visao padrao eh: R.layout.item_pedido_fornecedor");
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent avent) {
	 	ItemLista objeto = (ItemLista) view;
		PedidoFornecedor item = (PedidoFornecedor) objeto.getItem();
		raiz.onToqueTela(item);
		return false;
	}
	
	@Override
	public boolean onLongClick(View view) {
		ItemLista objeto = (ItemLista) view;
		PedidoFornecedor item = (PedidoFornecedor) objeto.getItem();
		if (edicao) {
			v.vibrate(500);
		}
		raiz.onToqueLongoTela(item);
		return false;
	}
	
	protected void alteraQuadro(IFragment quadro) {
		raiz.alteraQuadro(quadro);
	}

}
