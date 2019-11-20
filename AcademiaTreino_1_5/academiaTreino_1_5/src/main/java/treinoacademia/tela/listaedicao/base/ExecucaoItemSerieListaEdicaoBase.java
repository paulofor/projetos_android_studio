
package  treinoacademia.tela.listaedicao.base;

import treinoacademia.app.R;
import treinoacademia.modelo.*;
import treinoacademia.modelo.agregado.*;
import treinoacademia.servico.*;
import treinoacademia.view.adapter.ExecucaoItemSerieListAdapter;
import treinoacademia.view.adapter.listaedicao.ExecucaoItemSerieListEdicaoAdapter;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.app.Constantes;
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

import android.app.Fragment;
import android.app.FragmentTransaction;

import br.com.digicom.log.DCLog;
import android.util.Log;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.servico.ServicoLocal;
import br.com.digicom.animacao.TrocaQuadro;

public abstract class ExecucaoItemSerieListaEdicaoBase extends BaseListFragment implements IQuadroListaEdicao  
	{

	public final static String ITEM_CLICK = "ExecucaoItemSerieItemClick";

	private ExecucaoItemSerieListEdicaoAdapter adapt = null;
	private List<ExecucaoItemSerie> lista = null;
	private Button btnCriaItem = null;
	
	private boolean salvaPreliminar = false;
	
	private ExecucaoItemSerieServico servico = null;

	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	protected void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	
	protected ExecucaoItemSerieServico getServico() {
		return servico;
	}
	
	@Override
	protected final void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaExecucaoItemSerie);
		logObjetoInterno();
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
		servico = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
		
	}

	
	// Nao passo para a arquitetura pq criaQuadroTrata e 
	// especifico de ListaEdicao que ? um conceito de template.
	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_ALTERACAO, true);
		bundle.setObjeto(Constantes.CHAVE_EXECUCAO_ITEM_SERIE, obj);
		IFragmentEdicao quadro = criaQuadroTrata(bundle);
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}

	protected void atualizaListaTela() {
		preencheLista(); // Talvez nao precise fazer isso sempre j? que existe o adapt.notifyDataSetChanged
		
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
		ResourceObj recurso = new ResourceObj(R.layout.lista_execucao_item_serie,"R.layout.lista_execucao_item_serie");
		return recurso;
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase, TelaListaEdicaoBase
	

	
	// Delegando a cria??o de objeto ao inicializaItemTela na camada servico
	public final void chamaCriaItem() {
		//ExecucaoItemSerie nova = servico.inicializaItemTela(getContext());
		ExecucaoItemSerie nova = FabricaVo.criaExecucaoItemSerie();
		nova = insereObjetoPrincipal(nova);
		nova = (ExecucaoItemSerie) ((ServicoLocal)servico).atribuiUsuario(nova);
		nova = servico.inicializaItemTela(nova,getContext());
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_EXECUCAO_ITEM_SERIE, nova);
		bundle.setObjeto(Constantes.CHAVE_ALTERACAO, !nova.getSomenteMemoria());
		IFragmentEdicao quadro = criaQuadroTrata(bundle);
		quadro.setBundle(bundle);
		exibeQuadro(quadro);
	}
	// Pode ser usado outro metodo de apresentacao na classe filha.
	protected void exibeQuadro(IFragment quadro) {
		TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}
	
	
	
	
	
	/*
	protected ExecucaoItemSerie criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em ExecucaoItemSerieQuadroLista retornando new ExecucaoItemSerie com inicializa??o de listas internas se necessario");
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
	
	protected IFragmentEdicao criaQuadroTrata(BundleFragment bundle) {
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em ExecucaoItemSerieListaEdicao retornando new ExecucaoItemSerieQuadroTrata ou verificar se nao esta sendo chamada via super");
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
		atualizaListaTela();
	}
	
	protected void preencheLista() {
        //ListView lista = (ListView) getActivity().findViewById(R.id.listViewPrincipal);
        ExecucaoItemSerieServico servico = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<ExecucaoItemSerie> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        //adapt.setRaiz(this);
        setListAdapter(adapt);
        adapt.notifyDataSetChanged(); 
	}
	
	private ExecucaoItemSerieListEdicaoAdapter getAdapter(List<ExecucaoItemSerie> lista,DigicomContexto dContexto) {
		return new ExecucaoItemSerieListEdicaoAdapter(lista,this,dContexto.getContext());
	}
	


	
	
	
	public void logObjetoInterno() {
	}
	
	
	// Classes dependentes para n?o associativas
	private DiaTreino diaTreino;
	public final void setDiaTreino(DiaTreino valor) {
		diaTreino = valor;
		diaTreino.setContexto(getContext());
	}
	protected final DiaTreino getDiaTreino() {
		return diaTreino;
	}
	
	
	
	private ExecucaoItemSerie insereObjetoPrincipal(ExecucaoItemSerie item) {
	
		item.setDiaTreino_Em(diaTreino);
	
		return item;
	}
	
	protected List<ExecucaoItemSerie> getListaCorrente(Context contexto, ExecucaoItemSerieServico servico) {
		List<ExecucaoItemSerie> listaSaida = null;
		
		if (diaTreino!=null) {
			listaSaida = servico.getPorEmDiaTreino(contexto, diaTreino.getId());
		}
		
		return listaSaida;
	}
	
	
	
	
	
	
}