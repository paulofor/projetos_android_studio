
package  br.com.lojadigicom.precomed.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.data.contract.ModeloProdutoContract;
import br.com.lojadigicom.precomed.framework.util.ItemChoiceManager;
import br.com.lojadigicom.precomed.modelo.ModeloProduto;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class ModeloProdutoListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected ModeloProdutoListAdapterBase.ModeloProdutoListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private ModeloProduto item;
	public void setItem(ModeloProduto dado) {
		item = dado;
	}
	public ModeloProduto getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(ModeloProdutoListAdapterBase.ModeloProdutoListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public ModeloProdutoListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
}
