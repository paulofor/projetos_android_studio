package  coletapreco.view.adapter.listauso.base;

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
import coletapreco.app.R;
import coletapreco.modelo.NaturezaProduto;
import coletapreco.view.NaturezaProdutoView;
import br.com.digicom.layout.ItemLista;
import br.com.digicom.quadro.IQuadroLista;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.layout.ResourceObj;
import coletapreco.tela.quadro.base.NaturezaProdutoQuadroListaBase;

public abstract class NaturezaProdutoListUsoAdapterBase extends BaseAdapter {
	
	private DigicomContexto context;
	private List<NaturezaProduto> lista;
	private IQuadroLista raiz;
	
	private Vibrator v = null;
	private boolean edicao = false;
	
	private ResourceObj resource = null;

	public NaturezaProdutoListUsoAdapterBase(List<NaturezaProduto> lista, DigicomContexto context ) {
		this.context = context;
		this.lista = lista;
		setEdicao(); // Forcando para fazer edicao - alterar se for necessario depois.
	}
	
	public void setListaNova(List<NaturezaProduto> listaNova) {
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

	public void setRaiz(IQuadroLista valor) {
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
		NaturezaProduto item = lista.get(position);
		
		resource = getLayoutItem();
		View saida = LayoutInflater.from(getContextAndroid()).inflate(resource.getCodigo(), null);
		if (!(saida instanceof ItemLista))
			throw new RuntimeException("Alterar layout raiz de item_natureza_produto.xml para br.com.digicom.layout.ItemLista");
		((ItemLista)saida).setItem(item);
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
	
	protected void montaItemLista(int position, NaturezaProduto item, View saida) {
		throw new UnsupportedOperationException("Fazer override de montaItemLista em "  + this.getClass().getSimpleName() + " ou verificar se nao esta sendo chamada via super. Arquivo de visao padrao eh: R.layout.item_natureza_produto");
	}
	protected ResourceObj getLayoutItem() {
		return new ResourceObj(R.layout.item_natureza_produto,"R.layout.item_natureza_produto");
	}
	/*
	@Override
	public boolean onTouch(View view, MotionEvent avent) {
	 	ItemLista objeto = (ItemLista) view;
		NaturezaProduto item = (NaturezaProduto) objeto.getItem();
		raiz.onToqueTela(item);
		return false;
	}
	
	@Override
	public boolean onLongClick(View view) {
		ItemLista objeto = (ItemLista) view;
		NaturezaProduto item = (NaturezaProduto) objeto.getItem();
		if (edicao) {
			v.vibrate(500);
		}
		raiz.onToqueLongoTela(item);
		return false;
	}
	*/
	
	protected void alteraQuadro(IFragment quadro) {
		raiz.alteraQuadro(quadro);
	}

}
