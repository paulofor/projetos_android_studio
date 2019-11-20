package br.com.digicom.quadro;

import java.util.ArrayList;
import java.util.List;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.digicom.layout.ResourceObj;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.DCIObjetoDominio;

public abstract class BaseDialogListFragment extends DialogFragment implements IFragmentEdicao, DialogListListener{

	public final static String CHAVE_LISTA_TOTAL = "BDLFChaveListaTotal";
	public final static String CHAVE_LISTA_ESCOLHIDOS = "BDLFChaveListaEscolhidos";
	//public final static String CHAVE_LISTENER = "BDLFListener";
	
	//private DialogListListener listener = null;
	private GerenciadorFragment ger = new GerenciadorFragment(this);
	private GerenciadorItensTela gerItens = new GerenciadorItensTela(this);
	
	private List listaItens = null; // Objeto simples da tela
	private List preEscolha = null; // Relacionamento
	private List listaEscolhidas = null;
	private View tela = null;
	private BundleFragment bundle = null;

	private IFragment container;
	
	protected List getPreEscolha() {
		return preEscolha;
	}
	
	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DCLog.d(DCLog.CICLO_VIEW, this, "onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);
		//tela = ger.getTela(inflater, container);
		//ger.inicializacaoDialogFragment();
		//return tela;
	}
	
	private Activity mActivity = null;
	public void setActivityAlternativo(Activity activity) {
		mActivity = activity;
	}
	public Activity getActivityAlternativo() {
		return mActivity;
	}
	public final void setBundle(BundleFragment dado) {
		bundle = dado;
	}
	public final BundleFragment getBundle() {
		return bundle;
	}
	public void setContainerFragment(IFragment dado) {
		this.container = dado;
	}
	public IFragment getContainerFragment() {
		return this.container;
	}
	public BaseDialogListFragment() {
		listaEscolhidas = new ArrayList();  
		preEscolha = new ArrayList();
	}
	
	
	private void setListaItens(List valor) {
		listaItens = valor;
	}
	private void setListaPreEscolhidos(List valor) {
		this.preEscolha = valor;
		for (Object obj : listaItens) {
			if (verificaItemPreEscolhido(obj)) {
				listaEscolhidas.add(obj);
			}
		}
	}

	// Aqueles que estao na lista de escolhidos e não estão na lista de
	// preescolhidos
	private List<DCIObjetoDominio> getListaInclusao() {
		List saida = new ArrayList<DCIObjetoDominio>();
		for (Object obj : listaEscolhidas) { // ObjetoAssociado
			DCIObjetoDominio item = (DCIObjetoDominio) obj;
			if (!existeNaListaInclusao(item,preEscolha)) { // Relacionamentos
				saida.add(item);
			}
		}
		return saida;
	}
	// Existe na pre e nao existe na escolhidos.
	private List<DCIObjetoDominio> getListaExclusao() {
		List saida = new ArrayList<DCIObjetoDominio>();
		for (Object obj : preEscolha) { // Relacionamentos
			DCIObjetoDominio item = (DCIObjetoDominio) obj;
			if (!existeNaListaExclusao(item,listaEscolhidas)) { // ObjetoAssociado
				saida.add(item);
			}
		}
		return saida;
	}
	
	private boolean existeNaListaInclusao(DCIObjetoDominio objAssociado, List listaRelacionamento) {
		for (Object objRel : listaRelacionamento) {
			if (this.comparaRelacionamentoComItem(objAssociado, objRel)) return true;
		}
		return false;
	}
	private boolean existeNaListaExclusao(DCIObjetoDominio objRel, List listaAssociado) {
		for (Object objAssociado : listaAssociado) {
			if (this.comparaRelacionamentoComItem(objAssociado, objRel)) return true;
		}
		return false;
	}

	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Set the dialog title
	    builder.setTitle("Escolha");
	    // Specify the list array, the items to be selected by default (null for none),
	    // and the listener through which to receive callbacks when items are selected
	    builder.setMultiChoiceItems(getNomes(), getPreEscolhidos(), new DialogInterface.OnMultiChoiceClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int which, boolean isChecked) {
	                   if (isChecked) {
	                       // If the user checked the item, add it to the selected items
	                	   adicionaEscolhido(which);
	                   } else {
	                       // Else, if the item is already in the array, remove it 
	                	   removeEscolhido(which);
	                   }
	               }
	           });
	    // Set the action buttons
	     builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // User clicked OK, so save the mSelectedItems results somewhere
	                   // or return them to the component that opened the dialog
	            	   onDialogPositiveClick(getListaInclusao(),getListaExclusao());
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   onDialogNegativeClick(listaEscolhidas);
	               }
	           });

	    return builder.create();
    }
	
	protected void adicionaEscolhido(int indice) {
		DCIObjetoDominio item = (DCIObjetoDominio)this.listaItens.get(indice);
		this.listaEscolhidas.add(item);
	}
	protected void removeEscolhido(int indice) {
		DCIObjetoDominio item = (DCIObjetoDominio)this.listaItens.get(indice);
		this.listaEscolhidas.remove(item);
	}
	
	
	protected CharSequence[] getNomes() {
		if (listaItens==null) throw new RuntimeException("Lista Itens null");
		CharSequence[] lista = new CharSequence[listaItens.size()];
		int i = 0;
		for (Object item:listaItens) {
			lista[i++] = item.toString();
		}
		return lista;
	}
	
	private boolean[] getPreEscolhidos() {
		if (listaItens==null) throw new RuntimeException("Lista Itens null");
		boolean[] lista = new boolean[listaItens.size()];
		int i = 0;
		for (Object item:listaItens) {
			lista[i++] = verificaItemPreEscolhido(item);
		}
		return lista;
	}
	
	protected boolean verificaItemPreEscolhido(Object item) {
		boolean saida = false;
		for (Object itemRelacionamento : this.preEscolha) {
			if (comparaRelacionamentoComItem(item,itemRelacionamento)) {
				saida = true;
				break;
			}
		}
		return saida;
	}
	
	protected abstract boolean comparaRelacionamentoComItem(Object item, Object relacionamento);
	
	@Override
	public final void extraiBundle() {
		this.setListaItens((List) this.getBundle().getObjeto(CHAVE_LISTA_TOTAL));
		this.setListaPreEscolhidos((List) this.getBundle().getObjeto(CHAVE_LISTA_ESCOLHIDOS));
	}

	public final ResourceObj getRecurso() {
		return ger.getRecurso();
	}

	@Override
	public final void inicializaParaTeste() {
		ger.inicializacaoBaseFragment();
	}
	
	@Override
	public void setItem(DCIObjetoDominio item) {
	}

	@Override
	public void setInsercao() {
	}

	@Override
	public void setAlteracao() {
	}

	@Override
	public boolean validaDadosQuadro() {
		return true;
	}

	
	public final void setTituloTela() {
		String titulo = getTituloTela();
		this.getActivity().getActionBar().setTitle(titulo);
	}
	


}
