package br.com.digicom.quadro;

import java.util.ArrayList;
import java.util.List;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import br.com.digicom.modelo.DCIObjetoDominio;

public abstract class DialogListFragment extends DialogFragment{

	private DialogListListener listener = null;
	
	private List listaItens = null; // Objeto simples da tela
	private List preEscolha = null; // Relacionamento
	private List listaEscolhidas = null;
	
	
	public DialogListFragment() {
		listaEscolhidas = new ArrayList();  
		preEscolha = new ArrayList();
	}
	
	public void setDialogListListener(DialogListListener valor) {
		this.listener = valor;
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
	public void setListas(List listaTotal, List listaPreEscolhidos) {
		setListaItens(listaTotal);
		setListaPreEscolhidos(listaPreEscolhidos);
	}
	
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Set the dialog title
	    builder.setTitle("Escolha de Roupas")
	    // Specify the list array, the items to be selected by default (null for none),
	    // and the listener through which to receive callbacks when items are selected
	           .setMultiChoiceItems(getNomes(), getPreEscolhidos(), new DialogInterface.OnMultiChoiceClickListener() {
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
	           })
	    // Set the action buttons
	           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // User clicked OK, so save the mSelectedItems results somewhere
	                   // or return them to the component that opened the dialog
	            	   listener.onDialogPositiveClick(listaEscolhidas);
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   listener.onDialogNegativeClick(listaEscolhidas);
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


}
