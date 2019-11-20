
package  repcom.tela.listaedicao.base;

import repcom.app.R;
import repcom.modelo.*;
import repcom.modelo.agregado.*;
import repcom.servico.*;
import repcom.view.adapter.ProdutoVendaListAdapter;
import repcom.view.adapter.listaedicao.ProdutoVendaListEdicaoAdapter;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.ProdutoVendaServico;
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

public abstract class ProdutoVendaListaEdicaoBase extends BaseListFragment implements IQuadroListaEdicao  
	, DialogListListener {

	public final static String ITEM_CLICK = "ProdutoVendaItemClick";

	private ProdutoVendaListEdicaoAdapter adapt = null;
	private List<ProdutoVenda> lista = null;
	private Button btnCriaItem = null;
	
	private boolean salvaPreliminar = false;
	
	private ProdutoVendaServico servico = null;

	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	protected void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	
	protected ProdutoVendaServico getServico() {
		return servico;
	}
	
	@Override
	protected final void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaProdutoVenda);
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
		servico = FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
		
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
		ResourceObj recurso = new ResourceObj(R.layout.lista_produto_venda,"R.layout.lista_produto_venda");
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
		//ProdutoVenda nova = servico.inicializaItemTela(getContext());
		ProdutoVenda nova = FabricaVo.criaProdutoVenda();
		nova = insereObjetoPrincipal(nova);
		nova = (ProdutoVenda) ((ServicoLocal)servico).atribuiUsuario(nova);
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
	protected ProdutoVenda criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em ProdutoVendaQuadroLista retornando new ProdutoVenda com inicializa??o de listas internas se necessario");
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
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em ProdutoVendaListaEdicao retornando new ProdutoVendaQuadroTrata ou verificar se nao esta sendo chamada via super");
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
        ProdutoVendaServico servico = FabricaServico.getInstancia().getProdutoVendaServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<ProdutoVenda> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        //adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	private ProdutoVendaListEdicaoAdapter getAdapter(List<ProdutoVenda> lista,DigicomContexto dContexto) {
		return new ProdutoVendaListEdicaoAdapter(lista,this,dContexto.getContext());
	}
	
	
	
	
	public void alteraQuadro(IFragment quadro) {
		TrocaQuadro.getInstancia().alteraQuadroListaParaDetalhe(quadro,getActivity());
	}


	
	// Tratamento Especial para Tela a serem embutidas.
	protected Produto item1 = null;
	protected Venda item2 = null;
	public void setProduto(Produto valor) {
		item1 = valor;
	}
	public void setVenda(Venda valor) {
		item2 = valor;
	}
	protected List<ProdutoVenda> getListaCorrente(Context contexto, ProdutoVendaServico servico) {
		List<ProdutoVenda> listaSaida = null;
		if (item1==null && item2==null) {
			throw new QuadroException(this,"Precisa setar um dos objetos");
		}
		if (item1!=null) {
			listaSaida = servico.getPorAssociadaProduto(contexto, item1.getId());
		}
		if (item2!=null) {
			listaSaida = servico.getPorAssociadaVenda(contexto, item2.getId());
		}
		return listaSaida;
	}
	protected List getListaItensDialog() {
		List listaSaida = null;
		if (item1==null && item2==null) {
			throw new QuadroException(this,"Precisa setar um dos objetos");
		}
		if (item1!=null) {
			VendaServico srv = FabricaServico.getInstancia().getVendaServico(FabricaServico.TIPO_SQLITE);
			listaSaida = srv.getAllTela(this.getContext().getContext());
		}
		if (item2!=null) {
			ProdutoServico srv = FabricaServico.getInstancia().getProdutoServico(FabricaServico.TIPO_SQLITE);
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
	
	
	
	
	
	private ProdutoVenda insereObjetoPrincipal(ProdutoVenda item) {
		return item;
	}
	
}