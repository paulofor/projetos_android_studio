
package  br.com.lojadigicom.precomed.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.precomed.data.contract.ModeloProdutoProdutoContract;
import br.com.lojadigicom.precomed.framework.util.ItemChoiceManager;
import br.com.lojadigicom.precomed.modelo.ModeloProdutoProduto;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class ModeloProdutoProdutoListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected ModeloProdutoProdutoListAdapterBase.ModeloProdutoProdutoListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private ModeloProdutoProduto item;
	public void setItem(ModeloProdutoProduto dado) {
		item = dado;
	}
	public ModeloProdutoProduto getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(ModeloProdutoProdutoListAdapterBase.ModeloProdutoProdutoListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public ModeloProdutoProdutoListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
}
