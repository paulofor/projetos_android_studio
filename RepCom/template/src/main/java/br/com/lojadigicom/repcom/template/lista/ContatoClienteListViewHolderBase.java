
package  br.com.lojadigicom.repcom.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.data.contract.ContatoClienteContract;
import br.com.lojadigicom.repcom.framework.util.ItemChoiceManager;
import br.com.lojadigicom.repcom.modelo.ContatoCliente;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class ContatoClienteListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected ContatoClienteListAdapterBase.ContatoClienteListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private ContatoCliente item;
	public void setItem(ContatoCliente dado) {
		item = dado;
	}
	public ContatoCliente getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(ContatoClienteListAdapterBase.ContatoClienteListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public ContatoClienteListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
}
