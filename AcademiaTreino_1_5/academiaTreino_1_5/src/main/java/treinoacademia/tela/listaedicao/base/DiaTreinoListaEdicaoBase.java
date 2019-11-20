
package  treinoacademia.tela.listaedicao.base;

import treinoacademia.app.R;
import treinoacademia.modelo.*;
import treinoacademia.modelo.agregado.*;
import treinoacademia.servico.*;
import treinoacademia.view.adapter.DiaTreinoListAdapter;
import treinoacademia.view.adapter.listaedicao.DiaTreinoListEdicaoAdapter;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.DiaTreinoServico;
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

public abstract class DiaTreinoListaEdicaoBase extends BaseListFragment implements IQuadroListaEdicao  
	{

	public final static String ITEM_CLICK = "DiaTreinoItemClick";

	private DiaTreinoListEdicaoAdapter adapt = null;
	private List<DiaTreino> lista = null;
	private Button btnCriaItem = null;
	
	private boolean salvaPreliminar = false;
	
	private DiaTreinoServico servico = null;

	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	protected void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	
	protected DiaTreinoServico getServico() {
		return servico;
	}
	
	@Override
	protected final void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaDiaTreino);
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
		servico = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
		
	}

	
	// Nao passo para a arquitetura pq criaQuadroTrata e 
	// especifico de ListaEdicao que ? um conceito de template.
	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_ALTERACAO, true);
		bundle.setObjeto(Constantes.CHAVE_DIA_TREINO, obj);
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
		ResourceObj recurso = new ResourceObj(R.layout.lista_dia_treino,"R.layout.lista_dia_treino");
		return recurso;
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase, TelaListaEdicaoBase
	

	
	// Delegando a cria??o de objeto ao inicializaItemTela na camada servico
	public final void chamaCriaItem() {
		//DiaTreino nova = servico.inicializaItemTela(getContext());
		DiaTreino nova = FabricaVo.criaDiaTreino();
		nova = insereObjetoPrincipal(nova);
		nova = (DiaTreino) ((ServicoLocal)servico).atribuiUsuario(nova);
		nova = servico.inicializaItemTela(nova,getContext());
		BundleFragment bundle = new BundleFragment();
		bundle.setObjeto(Constantes.CHAVE_DIA_TREINO, nova);
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
	protected DiaTreino criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em DiaTreinoQuadroLista retornando new DiaTreino com inicializa??o de listas internas se necessario");
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
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em DiaTreinoListaEdicao retornando new DiaTreinoQuadroTrata ou verificar se nao esta sendo chamada via super");
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
        DiaTreinoServico servico = FabricaServico.getInstancia().getDiaTreinoServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<DiaTreino> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        //adapt.setRaiz(this);
        setListAdapter(adapt);
        adapt.notifyDataSetChanged(); 
	}
	
	private DiaTreinoListEdicaoAdapter getAdapter(List<DiaTreino> lista,DigicomContexto dContexto) {
		return new DiaTreinoListEdicaoAdapter(lista,this,dContexto.getContext());
	}
	


	
	
	
	public void logObjetoInterno() {
	}
	
	
	// Classes dependentes para n?o associativas
	private SerieTreino serieTreino;
	public final void setSerieTreino(SerieTreino valor) {
		serieTreino = valor;
		serieTreino.setContexto(getContext());
	}
	protected final SerieTreino getSerieTreino() {
		return serieTreino;
	}
	
	
	
	private DiaTreino insereObjetoPrincipal(DiaTreino item) {
	
		item.setSerieTreino_SerieDia(serieTreino);
	
		return item;
	}
	
	protected List<DiaTreino> getListaCorrente(Context contexto, DiaTreinoServico servico) {
		List<DiaTreino> listaSaida = null;
		
		if (serieTreino!=null) {
			listaSaida = servico.getPorSerieDiaSerieTreino(contexto, serieTreino.getId());
		}
		
		return listaSaida;
	}
	
	
	
	
	
	
}