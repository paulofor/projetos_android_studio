
package  coletapreco.tela.listaedicao.base;

import coletapreco.app.R;
import coletapreco.modelo.*;
import coletapreco.modelo.agregado.*;
import coletapreco.servico.*;
import coletapreco.view.adapter.CategoriaLojaListAdapter;
import coletapreco.view.adapter.listaedicao.CategoriaLojaListEdicaoAdapter;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.servico.CategoriaLojaServico;
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

public abstract class CategoriaLojaListaEdicaoBase extends BaseListFragment implements IQuadroListaEdicao  
	{

	public final static String ITEM_CLICK = "CategoriaLojaItemClick";

	private CategoriaLojaListEdicaoAdapter adapt = null;
	private List<CategoriaLoja> lista = null;
	private Button btnCriaItem = null;
	
	private boolean salvaPreliminar = false;
	
	private CategoriaLojaServico servico = null;

	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	protected void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	
	protected CategoriaLojaServico getServico() {
		return servico;
	}
	
	@Override
	protected final void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaCategoriaLoja);
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
		servico = FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
		
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
		ResourceObj recurso = new ResourceObj(R.layout.lista_categoria_loja,"R.layout.lista_categoria_loja");
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
		//CategoriaLoja nova = servico.inicializaItemTela(getContext());
		CategoriaLoja nova = FabricaVo.criaCategoriaLoja();
		nova = insereObjetoPrincipal(nova);
		nova = (CategoriaLoja) ((ServicoLocal)servico).atribuiUsuario(nova);
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
	protected CategoriaLoja criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em CategoriaLojaQuadroLista retornando new CategoriaLoja com inicializa??o de listas internas se necessario");
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
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em CategoriaLojaListaEdicao retornando new CategoriaLojaQuadroTrata ou verificar se nao esta sendo chamada via super");
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
        CategoriaLojaServico servico = FabricaServico.getInstancia().getCategoriaLojaServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<CategoriaLoja> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        //adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	private CategoriaLojaListEdicaoAdapter getAdapter(List<CategoriaLoja> lista,DigicomContexto dContexto) {
		return new CategoriaLojaListEdicaoAdapter(lista,this,dContexto.getContext());
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


	
	
	
	
	
	private CategoriaLoja insereObjetoPrincipal(CategoriaLoja item) {
	
		return item;
	}
	
	
	protected List<CategoriaLoja> getListaCorrente(Context contexto,CategoriaLojaServico servico) {
		List<CategoriaLoja> saida = servico.getAllTela(contexto);
		return saida;
	}
	
	
	
	
}