
package  br.com.lojadigicom.repcom.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.data.contract.ClienteContract;
import br.com.lojadigicom.repcom.framework.util.ItemChoiceManager;
import br.com.lojadigicom.repcom.modelo.Cliente;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class ClienteListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected ClienteListAdapterBase.ClienteListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private Cliente item;
	public void setItem(Cliente dado) {
		item = dado;
	}
	public Cliente getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(ClienteListAdapterBase.ClienteListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public ClienteListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
}
