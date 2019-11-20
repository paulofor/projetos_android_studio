
package  treinoacademia.tela.quadro.base;

import treinoacademia.app.R;
import treinoacademia.modelo.DispositivoUsuario;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.DispositivoUsuarioServico;
import treinoacademia.view.adapter.DispositivoUsuarioListAdapter;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.DispositivoUsuarioServico;

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

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.DialogFragment;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;
import android.util.Log;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.layout.ResourceObj;
import android.widget.BaseAdapter;
import br.com.digicom.animacao.TrocaQuadro;

public abstract class DispositivoUsuarioQuadroListaBase extends BaseListFragment implements IQuadroListaEdicao {

	public final static String ITEM_CLICK = "DispositivoUsuarioItemClick";

	DispositivoUsuarioListAdapter adapt = null;
	private Button btnCriaItem = null;
	
	DispositivoUsuarioServico servico = null;
	
	
	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	@Override
	protected void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaDispositivoUsuario);
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
		servico = FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
	}

	
	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		IFragmentEdicao quadro = criaQuadroTrata();
		quadro.setItem(obj);
		quadro.setAlteracao();
		alteraQuadro(quadro);
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
	    // Ao se conhecer melhor o ciclo de vida dos fragments pensar em otimizar esse trecho
		// evitar processar algo que n?o muda e evitar n?o processar algo que muda.
		// Fazendo um algoritmo que sirva para fragments de smartphone, que fica um por tela
		// Quanto de tablet que pode ficar mais de um
		// Decidir ate 28-06-2014
	}
	
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.lista_dispositivo_usuario,"R.layout.lista_dispositivo_usuario");
		return recurso;
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase
	
	
	// Delegando a cria??o de objeto ao inicializaItemTela na camada servico
	public final void chamaCriaItem() {
		IFragmentEdicao quadro = criaQuadroTrata();
		//DispositivoUsuario nova = servico.inicializaItemTela(getContext());
		DispositivoUsuario nova = FabricaVo.criaDispositivoUsuario();
		//nova = insereObjetoPrincipal(nova);
		nova = servico.inicializaItemTela(nova,getContext());
		quadro.setItem(nova);
		quadro.setInsercao();
		
	}
	
	protected boolean getDialog() {
		return false;
	}
	
	
	
	protected DispositivoUsuario criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em DispositivoUsuarioQuadroLista retornando new DispositivoUsuario com inicializa??o de listas internas se necessario");
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
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em DispositivoUsuarioQuadroLista retornando new DispositivoUsuarioQuadroTrata ou verificar se nao esta sendo chamada via super");
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

	protected void preencheLista() {
        //ListView lista = (ListView) getActivity().findViewById(R.id.listViewPrincipal);
        DispositivoUsuarioServico servico = FabricaServico.getInstancia().getDispositivoUsuarioServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        List<DispositivoUsuario> lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<DispositivoUsuario> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        adapt.setRaiz(this);
        setListAdapter(adapt);
        adapt.notifyDataSetChanged();
	}
	
	protected DispositivoUsuarioListAdapter getAdapter(List<DispositivoUsuario> lista,DigicomContexto dContexto) {
		return new DispositivoUsuarioListAdapter(lista,dContexto.getContext());
	}
	
	protected List<DispositivoUsuario> getListaCorrente(Context contexto,DispositivoUsuarioServico servico) {
		List<DispositivoUsuario> saida = servico.getAllTela(contexto);
		return saida;
	}
	
	
	// Tive que manter para que se possa criar Dialogs caso seja necess?rio.
	// Pode ser feito de outra forma mas acho essa mais simples. Avaliar !!! ( 18-10-2015)
	private void alteraQuadro(IFragment quadro) {
		if (getDialog())
			TrocaQuadro.getInstancia().criaDialog(quadro,this);
		else
			TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}
	
	

}