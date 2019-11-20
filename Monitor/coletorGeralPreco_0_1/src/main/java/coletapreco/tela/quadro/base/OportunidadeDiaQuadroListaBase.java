
package  coletapreco.tela.quadro.base;

import coletapreco.app.R;
import coletapreco.modelo.OportunidadeDia;
import coletapreco.servico.FabricaServico;
import coletapreco.servico.OportunidadeDiaServico;
import coletapreco.view.adapter.OportunidadeDiaListAdapter;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.servico.OportunidadeDiaServico;

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

public abstract class OportunidadeDiaQuadroListaBase extends BaseListFragment implements IQuadroListaEdicao {

	public final static String ITEM_CLICK = "OportunidadeDiaItemClick";

	OportunidadeDiaListAdapter adapt = null;
	private Button btnCriaItem = null;
	
	OportunidadeDiaServico servico = null;
	
	
	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	@Override
	protected void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaOportunidadeDia);
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
		servico = FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
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
		ResourceObj recurso = new ResourceObj(R.layout.lista_oportunidade_dia,"R.layout.lista_oportunidade_dia");
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
		//OportunidadeDia nova = servico.inicializaItemTela(getContext());
		OportunidadeDia nova = FabricaVo.criaOportunidadeDia();
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
	
	
	protected OportunidadeDia criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em OportunidadeDiaQuadroLista retornando new OportunidadeDia com inicializa??o de listas internas se necessario");
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
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em OportunidadeDiaQuadroLista retornando new OportunidadeDiaQuadroTrata ou verificar se nao esta sendo chamada via super");
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
        OportunidadeDiaServico servico = FabricaServico.getInstancia().getOportunidadeDiaServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        List<OportunidadeDia> lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<OportunidadeDia> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	protected OportunidadeDiaListAdapter getAdapter(List<OportunidadeDia> lista,DigicomContexto dContexto) {
		return new OportunidadeDiaListAdapter(lista,dContexto.getContext());
	}
	
	protected List<OportunidadeDia> getListaCorrente(Context contexto,OportunidadeDiaServico servico) {
		List<OportunidadeDia> saida = servico.getAllTela(contexto);
		return saida;
	}
	
	public void alteraQuadro(IFragment quadro) {
		setTituloTela();
		alteraQuadro(quadro,br.com.digicom.R.id.fragment_container);
	}
	
	public String getTituloTela()  {
		return "Alterar em " + this.getClass().getSimpleName();
	}

}