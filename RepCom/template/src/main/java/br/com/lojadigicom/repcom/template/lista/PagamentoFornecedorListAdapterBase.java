
package  br.com.lojadigicom.repcom.template.lista;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.lojadigicom.repcom.framework.util.ItemChoiceManager;
import java.util.List;
import br.com.lojadigicom.repcom.modelo.PagamentoFornecedor;


/**
 * Created by Gerador de Codigo.
 */
public abstract class PagamentoFornecedorListAdapterBase extends RecyclerView.Adapter<PagamentoFornecedorListViewHolderBase>{

    //protected Cursor mCursor;
    protected List<PagamentoFornecedor> mLista;
    private Context mContext;
    private PagamentoFornecedorListAdapterOnClickHandler mClickHandler;
    private View mEmptyView;
    private ItemChoiceManager mICM = null;

    public PagamentoFornecedorListAdapterBase(Context context, PagamentoFornecedorListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        mContext = context;
        mClickHandler = dh;
        mEmptyView = emptyView;
        //mICM = new ItemChoiceManager(this);
        //mICM.setChoiceMode(choiceMode);
    }


    @Override
    public final PagamentoFornecedorListViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if ( parent instanceof RecyclerView ) {
            int layoutId = -1;
            layoutId = getItemLayoutId();
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            view.setFocusable(true);
            PagamentoFornecedorListViewHolderBase saida = getViewHolder(view);
            saida.setLista(this.mLista);
            saida.setOnClickHandler(this.mClickHandler);
            return saida;
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    protected abstract int getItemLayoutId() ;
    protected abstract PagamentoFornecedorListViewHolderBase getViewHolder(View v);



    @Override
    public final int getItemCount() {
        if ( null == mLista) return 0;
        return mLista.size();
    }
    public final void onRestoreInstanceState(Bundle savedInstanceState) {
        //mICM.onRestoreInstanceState(savedInstanceState);
    }

    public final void onSaveInstanceState(Bundle outState) {
        //mICM.onSaveInstanceState(outState);
    }


    public final void swapCursor(List<PagamentoFornecedor> novaLista) {
        //mCursor = newCursor;
        mLista = novaLista;
        notifyDataSetChanged();
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public static interface PagamentoFornecedorListAdapterOnClickHandler {
        void onClick(int id, PagamentoFornecedorListViewHolderBase vh);
    }
    
    
    @Override
    public void onBindViewHolder(PagamentoFornecedorListViewHolderBase holder, int position) {
        PagamentoFornecedor obj = mLista.get(position);
        holder.setItem(obj);
        onBindViewHolderDC(obj,holder,position);
    }

    protected abstract void onBindViewHolderDC(PagamentoFornecedor item,PagamentoFornecedorListViewHolderBase holder, int position);
    
}
