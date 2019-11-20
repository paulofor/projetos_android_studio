
package  br.com.lojadigicom.repcom.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.data.contract.ProdutoVendaContract;
import br.com.lojadigicom.repcom.framework.util.ItemChoiceManager;
import br.com.lojadigicom.repcom.modelo.ProdutoVenda;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class ProdutoVendaListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected ProdutoVendaListAdapterBase.ProdutoVendaListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private ProdutoVenda item;
	public void setItem(ProdutoVenda dado) {
		item = dado;
	}
	public ProdutoVenda getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(ProdutoVendaListAdapterBase.ProdutoVendaListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public ProdutoVendaListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
}
