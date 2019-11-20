
package  coletapreco.tela.listaedicao.base;

import coletapreco.app.R;
import coletapreco.modelo.*;
import coletapreco.modelo.agregado.*;
import coletapreco.servico.*;
import coletapreco.view.adapter.ModeloProdutoProdutoListAdapter;
import coletapreco.view.adapter.listaedicao.ModeloProdutoProdutoListEdicaoAdapter;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.servico.ModeloProdutoProdutoServico;
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

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.servico.ServicoLocal;

public abstract class ModeloProdutoProdutoListaEdicaoBase extends BaseListFragment implements IQuadroListaEdicao  
	, DialogListListener {

	public final static String ITEM_CLICK = "ModeloProdutoProdutoItemClick";

	private ModeloProdutoProdutoListEdicaoAdapter adapt = null;
	private List<ModeloProdutoProduto> lista = null;
	private Button btnCriaItem = null;
	
	private boolean salvaPreliminar = false;
	
	private ModeloProdutoProdutoServico servico = null;

	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	protected void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	
	protected ModeloProdutoProdutoServico getServico() {
		return servico;
	}
	
	@Override
	protected final void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaModeloProdutoProduto);
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
		servico = FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
		
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
		ResourceObj recurso = new ResourceObj(R.layout.lista_modelo_produto_produto,"R.layout.lista_modelo_produto_produto");
		return recurso;
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase, TelaListaEdicaoBase
	@Override
	@Deprecated
	public final ResourceObj getRecurso() {
		return this.getLayoutTela();
	}

	
	// Um objeto Associativo
	
	// Sem atributos
	public final void chamaCriaItem() {
	
		DialogListListener listener = (DialogListListener) this;	
		DialogListFragment dialog = criaDialog();
		dialog.setDialogListListener(listener);
		dialog.setListas(getListaItensDialog(), this.lista);
		dialog.show(getActivity().getSupportFragmentManager(), "ListaModeloProdutoProdutoDialog");
	}
	
	@Override
	public final void onDialogNegativeClick(List<DCIObjetoDominio> listaEscolhidos) {
	}
	
	protected DialogListFragment criaDialog() {
		throw new UnsupportedOperationException("Fazer override de criaDialog em ModeloProdutoProdutoListaEdicao com return new ModeloProdutoProdutoQuadroTrata extends DialogListFragment");
	}
	
	
	
	
	/*
	protected ModeloProdutoProduto criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em ModeloProdutoProdutoQuadroLista retornando new ModeloProdutoProduto com inicializa??o de listas internas se necessario");
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
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em ModeloProdutoProdutoListaEdicao retornando new ModeloProdutoProdutoQuadroTrata ou verificar se nao esta sendo chamada via super");
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
        ModeloProdutoProdutoServico servico = FabricaServico.getInstancia().getModeloProdutoProdutoServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<ModeloProdutoProduto> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        //adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	private ModeloProdutoProdutoListEdicaoAdapter getAdapter(List<ModeloProdutoProduto> lista,DigicomContexto dContexto) {
		return new ModeloProdutoProdutoListEdicaoAdapter(lista,this,dContexto.getContext());
	}
	
	
	
	// Existe tambem em PrincipalActivityBase.javajet
	// Permitindo o uso somente de quadros que herdem de algum do framework
	public void alteraQuadro(IFragment quadro) {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		//transaction.setCustomAnimations(R.anim.slide_in_right,0);
		transaction.replace(br.com.digicom.R.id.fragment_container,(Fragment) quadro);
		transaction.addToBackStack(null);
		transaction.commit();
	}


	
	// Tratamento Especial para Tela a serem embutidas.
	protected ModeloProduto item1 = null;
	protected Produto item2 = null;
	public void setModeloProduto(ModeloProduto valor) {
		item1 = valor;
	}
	public void setProduto(Produto valor) {
		item2 = valor;
	}
	protected List<ModeloProdutoProduto> getListaCorrente(Context contexto, ModeloProdutoProdutoServico servico) {
		List<ModeloProdutoProduto> listaSaida = null;
		if (item1==null && item2==null) {
			throw new QuadroException(this,"Precisa setar um dos objetos");
		}
		if (item1!=null) {
			listaSaida = servico.getPorReferenteAModeloProduto(contexto, item1.getId());
		}
		if (item2!=null) {
			listaSaida = servico.getPorReferenteAProduto(contexto, item2.getId());
		}
		return listaSaida;
	}
	protected List getListaItensDialog() {
		List listaSaida = null;
		if (item1==null && item2==null) {
			throw new QuadroException(this,"Precisa setar um dos objetos");
		}
		if (item1!=null) {
			ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
			listaSaida = srv.getAllTela(this.getContext().getContext());
		}
		if (item2!=null) {
			ModeloProdutoServico srv = FabricaServico.getInstancia().getModeloProdutoServico(FabricaServico.TIPO_SQLITE);
			listaSaida = srv.getAllTela(this.getContext().getContext());
		}
		return listaSaida;
	}
	public void onDialogPositiveClick(List<DCIObjetoDominio> listaEscolhidos) {
		if (item1==null && item2==null) {
			throw new QuadroException(this,"Precisa setar um dos objetos");
		}
		if (item1!=null) {
			this.getServico().atualizaRelacionamento(item1, listaEscolhidos);
			this.atualizaListaTela();
		}
		if (item2!=null) {
			this.getServico().atualizaRelacionamento(item2, listaEscolhidos);
			this.atualizaListaTela();
		}
	}
	
	
	
	
	
	private ModeloProdutoProduto insereObjetoPrincipal(ModeloProdutoProduto item) {
		return item;
	}
	
}