
package  repcom.tela.listaedicao.base;

import repcom.app.R;
import repcom.modelo.*;
import repcom.modelo.agregado.*;
import repcom.servico.*;
import repcom.view.adapter.ProdutoListAdapter;
import repcom.view.adapter.listaedicao.ProdutoListEdicaoAdapter;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.ProdutoServico;
import android.widget.BaseAdapter;

import java.util.List;
import br.com.digicom.quadro.*;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Button;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.log.DCLog;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.animacao.TrocaQuadro;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.servico.ServicoLocal;

public abstract class ProdutoListaEdicaoBase extends BaseListFragment implements IQuadroListaEdicao  
	{

	public final static String ITEM_CLICK = "ProdutoItemClick";

	private ProdutoListEdicaoAdapter adapt = null;
	private List<Produto> lista = null;
	private Button btnCriaItem = null;
	
	private boolean salvaPreliminar = false;
	
	private ProdutoServico servico = null;

	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	protected void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	
	protected ProdutoServico getServico() {
		return servico;
	}
	
	@Override
	protected final void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaProduto);
	}
	@Override
	protected final void inicializaListenersBase() {
		btnCriaItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaCriaItem();
			}
		});
	}
	@Override
	protected final void inicializaServicosBase() {
		servico = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
		
	}

	
	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		IFragmentEdicao quadro = criaQuadroTrata();
		quadro.setItem(obj);
		quadro.setAlteracao();
		this.alteraQuadro(quadro);
	}

	protected void atualizaListaTela() {
		preencheLista(); // Talvez nao precise fazer isso sempre j? que existe o adapt.notifyDataSetChanged
		adapt.notifyDataSetChanged(); // Colocar dentro do preencheLista ?
	}
	

	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
	}

	protected String getPalavraChave() {
		return ITEM_CLICK;
	}

	

	public void onStart() {
		super.onStart();
		atualizaListaTela();
		// Ao se conhecer melhor o ciclo de vida dos fragments pensar em otimizar esse trecho
		// evitar processar algo que n?o muda e evitar n?o processar algo que muda.
		// Fazendo um algoritmo que sirva para fragments de smartphone, que fica um por tela
		// Quanto de tablet que pode ficar mais de um
		// Decidir ate 28-06-2014
	}
	

	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.lista_produto,"R.layout.lista_produto");
		return recurso;
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase, TelaListaEdicaoBase
	@Override
	@Deprecated
	public final ResourceObj getRecurso() {
		return this.getLayoutTela();
	}

	
	// Delegando a cria??o de objeto ao inicializaItemTela na camada servico
	public final void chamaCriaItem() {
		IFragmentEdicao quadro = criaQuadroTrata();
		//Produto nova = servico.inicializaItemTela(getContext());
		Produto nova = FabricaVo.criaProduto();
		nova = insereObjetoPrincipal(nova);
		nova = (Produto) ((ServicoLocal)servico).atribuiUsuario(nova);
		nova = servico.inicializaItemTela(nova,getContext());
		quadro.setItem(nova);
		if (salvaPreliminar) {
			quadro.setAlteracao();
		} else {
			quadro.setInsercao();
		}
		this.alteraQuadro(quadro);
	}
	
	
	
	
	/*
	protected Produto criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em ProdutoQuadroLista retornando new Produto com inicializa??o de listas internas se necessario");
		// Exemplo - Criar inicializando dados e listas internas 
		protected SerieTreino criaNova() {
			SerieTreino nova = FabricaVo.criaSerieTreino();
			nova.setDataInicial(UtilDatas.getTimestampAtual());
			List<ItemSerie> lista = new ArrayList<ItemSerie>();
			nova.setListaItemSerie_Possui(lista);
			return nova;
		}
	}
	*/
	
	protected IFragmentEdicao criaQuadroTrata() {
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em ProdutoListaEdicao retornando new ProdutoQuadroTrata ou verificar se nao esta sendo chamada via super");
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
	@Override
	protected void inicializaServicos() {
	}
	@Override
	public void onAlteraQuadro() {
	}
	
	private void preencheLista() {
        //ListView lista = (ListView) getActivity().findViewById(R.id.listViewPrincipal);
        ProdutoServico servico = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<Produto> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        //adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	private ProdutoListEdicaoAdapter getAdapter(List<Produto> lista,DigicomContexto dContexto) {
		return new ProdutoListEdicaoAdapter(lista,this,dContexto.getContext());
	}
	
	
	
	
	public void alteraQuadro(IFragment quadro) {
		TrocaQuadro.getInstancia().alteraQuadroListaParaDetalhe(quadro,getActivity());
	}


	
	
	
	
	
	private Produto insereObjetoPrincipal(Produto item) {
	
		return item;
	}
	
	
	protected List<Produto> getListaCorrente(Context contexto,ProdutoServico servico) {
		List<Produto> saida = servico.getAllTela(contexto);
		return saida;
	}
	
	
	
	
}