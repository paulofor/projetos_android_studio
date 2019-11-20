
package  treinoacademia.tela.listauso.base;

import br.com.digicom.R;
import treinoacademia.modelo.*;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.view.adapter.listauso.base.ExecucaoItemSerieListUsoAdapterBase;
import treinoacademia.view.adapter.listauso.ExecucaoItemSerieListUsoAdapter;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.ExecucaoItemSerieServico;
import br.com.digicom.animacao.TrocaQuadro;

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

public abstract class ExecucaoItemSerieListaUsoBase extends BaseListFragment implements IQuadroLista {

	public final static String ITEM_CLICK = "ExecucaoItemSerieItemClick";

	private ExecucaoItemSerieListUsoAdapterBase adapt = null;
	private ExecucaoItemSerieServico servico = null;
	
		
	protected ExecucaoItemSerieServico getServico() {
		return servico;
	}
		
	public BaseAdapter getAdapter() {
		return adapt;
	}
		
		
	@Override
	protected void inicializaServicos() {
		servico = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
	}

	protected void atualizaLista() {
		adapt.notifyDataSetChanged();
	}
	protected void atualizaLista(List<ExecucaoItemSerie> listaNova) {
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
		preencheLista();
	}

	private void preencheLista() {
        //ListView lista = (ListView) getActivity().findViewById(R.id.listViewPrincipal);
        ExecucaoItemSerieServico servico = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        List<ExecucaoItemSerie> lista = getListaCorrente(dContexto.getContext(),servico);
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "getListaCorrente : List<ExecucaoItemSerie> -> " + lista.size() + " itens (Adapter:" + adapt.getClass().getName() + ")");
        adapt.setRaiz(this);
        setListAdapter(adapt);
	}
	
	protected ExecucaoItemSerieListUsoAdapterBase getAdapter(List<ExecucaoItemSerie> lista,DigicomContexto dContexto) {
		return new ExecucaoItemSerieListUsoAdapter(lista,dContexto);
	}
	
	protected List<ExecucaoItemSerie> getListaCorrente(Context contexto,ExecucaoItemSerieServico servico) {
		
		List<ExecucaoItemSerie> saida = servico.getAllTela(contexto);
		return saida;
		
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
	
	
	

}