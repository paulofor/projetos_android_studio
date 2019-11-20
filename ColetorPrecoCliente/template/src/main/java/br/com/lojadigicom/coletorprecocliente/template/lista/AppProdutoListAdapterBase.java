
package  br.com.lojadigicom.coletorprecocliente.template.lista;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.framework.util.ItemChoiceManager;
import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.AppProduto;


/**
 * Created by Gerador de Codigo.
 */
public abstract class AppProdutoListAdapterBase extends RecyclerView.Adapter<AppProdutoListViewHolderBase>{

    //protected Cursor mCursor;
    protected List<AppProduto> mLista;
    private Context mContext;
    private AppProdutoListAdapterOnClickHandler mClickHandler;
    private View mEmptyView;
    private ItemChoiceManager mICM = null;

    public AppProdutoListAdapterBase(Context context, AppProdutoListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        mContext = context;
        mClickHandler = dh;
        mEmptyView = emptyView;
        //mICM = new ItemChoiceManager(this);
        //mICM.setChoiceMode(choiceMode);
    }


    @Override
    public final AppProdutoListViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if ( parent instanceof RecyclerView ) {
            int layoutId = -1;
            layoutId = getItemLayoutId();
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            view.setFocusable(true);
            AppProdutoListViewHolderBase saida = getViewHolder(view);
            saida.setLista(this.mLista);
            saida.setOnClickHandler(this.mClickHandler);
            return saida;
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    protected abstract int getItemLayoutId() ;
    protected abstract AppProdutoListViewHolderBase getViewHolder(View v);



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


    public final void swapCursor(List<AppProduto> novaLista) {
        //mCursor = newCursor;
        mLista = novaLista;
        notifyDataSetChanged();
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public static interface AppProdutoListAdapterOnClickHandler {
        void onClick(int id, AppProdutoListViewHolderBase vh);
    }
    
    
    @Override
    public void onBindViewHolder(AppProdutoListViewHolderBase holder, int position) {
        AppProduto obj = mLista.get(position);
        holder.setItem(obj);
        onBindViewHolderDC(obj,holder,position);
    }

    protected abstract void onBindViewHolderDC(AppProduto item,AppProdutoListViewHolderBase holder, int position);
    
}
