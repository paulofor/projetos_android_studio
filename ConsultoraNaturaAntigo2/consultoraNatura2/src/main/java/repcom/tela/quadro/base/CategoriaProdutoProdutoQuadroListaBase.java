
package  repcom.tela.quadro.base;

import repcom.app.R;
import repcom.modelo.CategoriaProdutoProduto;
import repcom.servico.FabricaServico;
import repcom.servico.CategoriaProdutoProdutoServico;
import repcom.view.adapter.CategoriaProdutoProdutoListAdapter;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.CategoriaProdutoProdutoServico;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Button;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseListFragment;
import br.com.digicom.quadro.IQuadroListaEdicao;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.log.DCLog;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.DialogFragment;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;
import android.util.Log;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.layout.ResourceObj;
import android.widget.BaseAdapter;
import br.com.digicom.animacao.TrocaQuadro;

public abstract class CategoriaProdutoProdutoQuadroListaBase extends BaseListFragment implements IQuadroListaEdicao {

	public final static String ITEM_CLICK = "CategoriaProdutoProdutoItemClick";

	CategoriaProdutoProdutoListAdapter adapt = null;
	private Button btnCriaItem = null;
	
	CategoriaProdutoProdutoServico servico = null;
	
	
	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	@Override
	protected void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaCategoriaProdutoProduto);
	}
	@Override
	protected void inicializaListenersBase() {
		btnCriaItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaCriaItem();
			}
		});
	}
	

	
	
	
	@Override
	protected void inicializaServicos() {
		servico = FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
	}

	
	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		IFragmentEdicao quadro = criaQuadroTrata();
		quadro.setItem(obj);
		quadro.setAlteracao();
		this.alteraQuadro(quadro);
	}

	
	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
	}

	protected String getPalavraChave() {
		return ITEM_CLICK;
	}

	@Override
	public void onAlteraQuadro() {
	}
	

	public void onStart() {
		super.onStart();
		preencheLista(); // Talvez nao precise fazer isso sempre j? que existe o adapt.notifyDataSetChanged
		setBarraTop();
		setBarraBottom();
		adapt.notifyDataSetChanged(); // Colocar dentro do preencheLista ?
		// Ao se conhecer melhor o ciclo de vida dos fragments pensar em otimizar esse trecho
		// evitar processar algo que n?o muda e evitar n?o processar algo que muda.
		// Fazendo um algoritmo que sirva para fragments de smartphone, que fica um por tela
		// Quanto de tablet que pode ficar mais de um
		// Decidir ate 28-06-2014
	}
	
	protected void setBarraTop() {
	}
	protected void setBarraBottom() {
	}
	
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.lista_categoria_produto_produto,"R.layout.lista_categoria_produto_produto");
		return recurso;
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase
	@Override
	@Deprecated
	public ResourceObj getRecurso() {
		return this.getLayoutTela();
	}
	
	// Delegando a cria??o de objeto ao inicializaItemTela na camada servico
	public final void chamaCriaItem() {
		IFragmentEdicao quadro = criaQuadroTrata();
		//CategoriaProdutoProduto nova = servico.inicializaItemTela(getContext());
		CategoriaProdutoProduto nova = FabricaVo.criaCategoriaProdutoProduto();
		//nova = insereObjetoPrincipal(nova);
		nova = servico.inicializaItemTela(nova,getContext());
		quadro.setItem(nova);
		quadro.setInsercao();
		if (dialog)
			this.criaDialog(quadro);
		else
			this.alteraQuadro(quadro);
	}
	// Passar para o framework ?
	private boolean dialog = false;
	protected void setDialog(boolean valor) {
		dialog = valor;
	}
	private void criaDialog(IFragmentEdicao quadro) {
		DialogFragment tela = (DialogFragment) quadro;
		tela.show(getFragmentManager(), this.getTituloTela());
	}
	
	
	protected CategoriaProdutoProduto criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em CategoriaProdutoProdutoQuadroLista retornando new CategoriaProdutoProduto com inicializa??o de listas internas se necessario");
		/* Exemplo - Criar inicializando dados e listas internas 
		protected SerieTreino criaNova() {
			SerieTreino nova = FabricaVo.criaSerieTreino();
			nova.setDataInicial(UtilDatas.getTimestampAtual());
			List<ItemSerie> lista = new ArrayList<ItemSerie>();
			nova.setListaItemSerie_Possui(lista);
			return nova;
		}
		*/
	}
	protected IFragmentEdicao criaQuadroTrata() {
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em CategoriaProdutoProdutoQuadroLista retornando new CategoriaProdutoProdutoQuadroTrata ou verificar se nao esta sendo chamada via super");
	}

	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
	}

	@Override
	protected void inicializaItensTela() {
	}

	@Override
	protected void inicializaListeners() {
	}

	private void preencheLista() {
        //ListView lista = (ListView) getActivity().findViewById(R.id.listViewPrincipal);
        CategoriaProdutoProdutoServico servico = FabricaServico.getInstancia().getCategoriaProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        List<CategoriaProdutoProduto> lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<CategoriaProdutoProduto> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	protected CategoriaProdutoProdutoListAdapter getAdapter(List<CategoriaProdutoProduto> lista,DigicomContexto dContexto) {
		return new CategoriaProdutoProdutoListAdapter(lista,dContexto.getContext());
	}
	
	protected List<CategoriaProdutoProduto> getListaCorrente(Context contexto,CategoriaProdutoProdutoServico servico) {
		List<CategoriaProdutoProduto> saida = servico.getAllTela(contexto);
		return saida;
	}
	
	public void alteraQuadro(IFragment quadro) {
		TrocaQuadro.getInstancia().alteraQuadroListaParaDetalhe(quadro,getActivity());
	}
	
	

}