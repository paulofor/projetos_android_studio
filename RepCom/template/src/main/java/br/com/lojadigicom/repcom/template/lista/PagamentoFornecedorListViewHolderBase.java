
package  br.com.lojadigicom.repcom.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.data.contract.PagamentoFornecedorContract;
import br.com.lojadigicom.repcom.framework.util.ItemChoiceManager;
import br.com.lojadigicom.repcom.modelo.PagamentoFornecedor;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class PagamentoFornecedorListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected PagamentoFornecedorListAdapterBase.PagamentoFornecedorListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private PagamentoFornecedor item;
	public void setItem(PagamentoFornecedor dado) {
		item = dado;
	}
	public PagamentoFornecedor getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(PagamentoFornecedorListAdapterBase.PagamentoFornecedorListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public PagamentoFornecedorListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
}
