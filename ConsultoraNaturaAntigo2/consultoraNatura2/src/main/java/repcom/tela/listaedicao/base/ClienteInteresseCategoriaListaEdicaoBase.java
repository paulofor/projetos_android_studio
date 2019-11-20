
package  repcom.tela.listaedicao.base;

import repcom.app.R;
import repcom.modelo.*;
import repcom.modelo.agregado.*;
import repcom.servico.*;
import repcom.view.adapter.ClienteInteresseCategoriaListAdapter;
import repcom.view.adapter.listaedicao.ClienteInteresseCategoriaListEdicaoAdapter;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.ClienteInteresseCategoriaServico;
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

public abstract class ClienteInteresseCategoriaListaEdicaoBase extends BaseListFragment implements IQuadroListaEdicao  
	, DialogListListener {

	public final static String ITEM_CLICK = "ClienteInteresseCategoriaItemClick";

	private ClienteInteresseCategoriaListEdicaoAdapter adapt = null;
	private List<ClienteInteresseCategoria> lista = null;
	private Button btnCriaItem = null;
	
	private boolean salvaPreliminar = false;
	
	private ClienteInteresseCategoriaServico servico = null;

	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	protected void setSalvaPreliminar(boolean valor) {
		salvaPreliminar = valor;
	}
	
	protected ClienteInteresseCategoriaServico getServico() {
		return servico;
	}
	
	@Override
	protected final void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaClienteInteresseCategoria);
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
		servico = FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
		
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
		ResourceObj recurso = new ResourceObj(R.layout.lista_cliente_interesse_categoria,"R.layout.lista_cliente_interesse_categoria");
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
		dialog.show(getActivity().getSupportFragmentManager(), "ListaClienteInteresseCategoriaDialog");
	}
	
	@Override
	public final void onDialogNegativeClick(List<DCIObjetoDominio> listaEscolhidos) {
	}
	
	protected DialogListFragment criaDialog() {
		throw new UnsupportedOperationException("Fazer override de criaDialog em ClienteInteresseCategoriaListaEdicao com return new ClienteInteresseCategoriaQuadroTrata extends DialogListFragment");
	}
	
	
	
	
	/*
	protected ClienteInteresseCategoria criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em ClienteInteresseCategoriaQuadroLista retornando new ClienteInteresseCategoria com inicializa??o de listas internas se necessario");
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
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em ClienteInteresseCategoriaListaEdicao retornando new ClienteInteresseCategoriaQuadroTrata ou verificar se nao esta sendo chamada via super");
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
        ClienteInteresseCategoriaServico servico = FabricaServico.getInstancia().getClienteInteresseCategoriaServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<ClienteInteresseCategoria> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        //adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	private ClienteInteresseCategoriaListEdicaoAdapter getAdapter(List<ClienteInteresseCategoria> lista,DigicomContexto dContexto) {
		return new ClienteInteresseCategoriaListEdicaoAdapter(lista,this,dContexto.getContext());
	}
	
	
	
	
	public void alteraQuadro(IFragment quadro) {
		TrocaQuadro.getInstancia().alteraQuadroListaParaDetalhe(quadro,getActivity());
	}


	
	// Tratamento Especial para Tela a serem embutidas.
	protected Cliente item1 = null;
	protected CategoriaProduto item2 = null;
	public void setCliente(Cliente valor) {
		item1 = valor;
	}
	public void setCategoriaProduto(CategoriaProduto valor) {
		item2 = valor;
	}
	protected List<ClienteInteresseCategoria> getListaCorrente(Context contexto, ClienteInteresseCategoriaServico servico) {
		List<ClienteInteresseCategoria> listaSaida = null;
		if (item1==null && item2==null) {
			throw new QuadroException(this,"Precisa setar um dos objetos");
		}
		if (item1!=null) {
			listaSaida = servico.getPorAssociadaCliente(contexto, item1.getId());
		}
		if (item2!=null) {
			listaSaida = servico.getPorAssociadaCategoriaProduto(contexto, item2.getId());
		}
		return listaSaida;
	}
	protected List getListaItensDialog() {
		List listaSaida = null;
		if (item1==null && item2==null) {
			throw new QuadroException(this,"Precisa setar um dos objetos");
		}
		if (item1!=null) {
			CategoriaProdutoServico srv = FabricaServico.getInstancia().getCategoriaProdutoServico(FabricaServico.TIPO_SQLITE);
			listaSaida = srv.getAllTela(this.getContext().getContext());
		}
		if (item2!=null) {
			ClienteServico srv = FabricaServico.getInstancia().getClienteServico(FabricaServico.TIPO_SQLITE);
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
	
	
	
	
	
	private ClienteInteresseCategoria insereObjetoPrincipal(ClienteInteresseCategoria item) {
		return item;
	}
	
}