
package  coletapreco.tela.listauso.base;

import br.com.digicom.R;
import coletapreco.modelo.LojaNatureza;
import coletapreco.servico.FabricaServico;
import coletapreco.servico.LojaNaturezaServico;
import coletapreco.view.adapter.listauso.base.LojaNaturezaListUsoAdapterBase;
import coletapreco.view.adapter.listauso.LojaNaturezaListUsoAdapter;
import coletapreco.modelo.vo.FabricaVo;
import coletapreco.servico.LojaNaturezaServico;

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
import br.com.digicom.quadro.IQuadroLista;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.log.DCLog;
import br.com.digicom.layout.ResourceObj;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;
import android.util.Log;
import br.com.digicom.modelo.DCIObjetoDominio;
import android.widget.BaseAdapter;

public abstract class LojaNaturezaListaUsoBase extends BaseListFragment implements IQuadroLista {

	public final static String ITEM_CLICK = "LojaNaturezaItemClick";

	private LojaNaturezaListUsoAdapterBase adapt = null;
	private LojaNaturezaServico servico = null;
		
	protected LojaNaturezaServico getServico() {
		return servico;
	}
		
	public BaseAdapter getAdapter() {
		return adapt;
	}
		
		
	@Override
	protected void inicializaServicos() {
		servico = FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
	}

	protected void atualizaLista() {
		adapt.notifyDataSetChanged();
	}
	protected void atualizaLista(List<LojaNatureza> listaNova) {
		adapt.setListaNova(listaNova);
		adapt.notifyDataSetChanged();
	}

	

	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
	}

	protected String getPalavraChave() {
		return ITEM_CLICK;
	}

	

	public void onStart() {
		super.onStart();
		preencheLista(); // Talvez nao precise fazer isso sempre j? que existe o adapt.notifyDataSetChanged
		adapt.notifyDataSetChanged(); // Colocar dentro do preencheLista ?
		// Ao se conhecer melhor o ciclo de vida dos fragments pensar em otimizar esse trecho
		// evitar processar algo que n?o muda e evitar n?o processar algo que muda.
		// Fazendo um algoritmo que sirva para fragments de smartphone, que fica um por tela
		// Quanto de tablet que pode ficar mais de um
		// Decidir ate 28-06-2014
	}
	
	
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.lista_uso_padrao,"R.layout.lista_uso_padrao");
		return recurso;
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase, TelaListaEdicaoBase
	@Override
	@Deprecated
	public final ResourceObj getRecurso() {
		return this.getLayoutTela();
	}
	

	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
	}
	/*
	Para esse tipo de tela nao est? implementado
	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
	}
	*/

	@Override
	protected void inicializaItensTela() {
	}

	@Override
	protected void inicializaListeners() {
	}
	@Override
	public void onAlteraQuadro() {
	}

	private void preencheLista() {
        //ListView lista = (ListView) getActivity().findViewById(R.id.listViewPrincipal);
        LojaNaturezaServico servico = FabricaServico.getInstancia().getLojaNaturezaServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        List<LojaNatureza> lista = getListaCorrente(dContexto.getContext(),servico);
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "getListaCorrente : List<LojaNatureza> -> " + lista.size() + " itens (Adapter:" + adapt.getClass().getName() + ")");
        adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	protected LojaNaturezaListUsoAdapterBase getAdapter(List<LojaNatureza> lista,DigicomContexto dContexto) {
		return new LojaNaturezaListUsoAdapter(lista,dContexto);
	}
	
	protected List<LojaNatureza> getListaCorrente(Context contexto,LojaNaturezaServico servico) {
		
		throw new UnsupportedOperationException("Fazer override de getListaCorrente em LojaNaturezaListaUso, trata-se de classe de relacionamento");
		
	}
	
	// Existe tambem em PrincipalActivityBase.javajet
	// Permitindo o uso somente de quadros que herdem de algum do framework
	public void alteraQuadro(IFragment quadro) {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		//transaction.setCustomAnimations(R.anim.slide_in_right,0);
		transaction.replace(R.id.fragment_container,(Fragment) quadro);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	public void alteraQuadro(IFragment quadro, String titulo) {
		FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		//transaction.setCustomAnimations(R.anim.slide_in_right,0);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.replace(R.id.fragment_container,(Fragment) quadro);
		transaction.addToBackStack(null);
		transaction.commit();
		getActivity().getActionBar().setTitle(titulo);
	}
	public final void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		DCIObjetoDominio item =  (DCIObjetoDominio) getListAdapter().getItem(position);
		onToqueTela(item);
	}
	
}