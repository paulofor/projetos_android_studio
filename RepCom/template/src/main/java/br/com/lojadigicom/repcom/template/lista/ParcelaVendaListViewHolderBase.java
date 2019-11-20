
package  br.com.lojadigicom.repcom.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.data.contract.ParcelaVendaContract;
import br.com.lojadigicom.repcom.framework.util.ItemChoiceManager;
import br.com.lojadigicom.repcom.modelo.ParcelaVenda;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class ParcelaVendaListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected ParcelaVendaListAdapterBase.ParcelaVendaListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private ParcelaVenda item;
	public void setItem(ParcelaVenda dado) {
		item = dado;
	}
	public ParcelaVenda getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(ParcelaVendaListAdapterBase.ParcelaVendaListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public ParcelaVendaListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
}
