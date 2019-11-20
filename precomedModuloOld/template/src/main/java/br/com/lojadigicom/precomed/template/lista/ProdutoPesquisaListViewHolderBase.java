
package  br.com.lojadigicom.precomed.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.data.contract.ProdutoPesquisaContract;
import br.com.lojadigicom.precomed.framework.util.ItemChoiceManager;
import br.com.lojadigicom.precomed.modelo.ProdutoPesquisa;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class ProdutoPesquisaListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected ProdutoPesquisaListAdapterBase.ProdutoPesquisaListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private ProdutoPesquisa item;
	public void setItem(ProdutoPesquisa dado) {
		item = dado;
	}
	public ProdutoPesquisa getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(ProdutoPesquisaListAdapterBase.ProdutoPesquisaListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public ProdutoPesquisaListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
}
