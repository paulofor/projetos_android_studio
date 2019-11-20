
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
import br.com.lojadigicom.repcom.modelo.ClienteInteresseCategoria;


/**
 * Created by Gerador de Codigo.
 */
public abstract class ClienteInteresseCategoriaListAdapterBase extends RecyclerView.Adapter<ClienteInteresseCategoriaListViewHolderBase>{

    //protected Cursor mCursor;
    protected List<ClienteInteresseCategoria> mLista;
    private Context mContext;
    private ClienteInteresseCategoriaListAdapterOnClickHandler mClickHandler;
    private View mEmptyView;
    private ItemChoiceManager mICM = null;

    public ClienteInteresseCategoriaListAdapterBase(Context context, ClienteInteresseCategoriaListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        mContext = context;
        mClickHandler = dh;
        mEmptyView = emptyView;
        //mICM = new ItemChoiceManager(this);
        //mICM.setChoiceMode(choiceMode);
    }


    @Override
    public final ClienteInteresseCategoriaListViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if ( parent instanceof RecyclerView ) {
            int layoutId = -1;
            layoutId = getItemLayoutId();
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            view.setFocusable(true);
            ClienteInteresseCategoriaListViewHolderBase saida = getViewHolder(view);
            saida.setLista(this.mLista);
            saida.setOnClickHandler(this.mClickHandler);
            return saida;
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    protected abstract int getItemLayoutId() ;
    protected abstract ClienteInteresseCategoriaListViewHolderBase getViewHolder(View v);



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


    public final void swapCursor(List<ClienteInteresseCategoria> novaLista) {
        //mCursor = newCursor;
        mLista = novaLista;
        notifyDataSetChanged();
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public static interface ClienteInteresseCategoriaListAdapterOnClickHandler {
        void onClick(int id, ClienteInteresseCategoriaListViewHolderBase vh);
    }
    
    
    @Override
    public void onBindViewHolder(ClienteInteresseCategoriaListViewHolderBase holder, int position) {
        ClienteInteresseCategoria obj = mLista.get(position);
        holder.setItem(obj);
        onBindViewHolderDC(obj,holder,position);
    }

    protected abstract void onBindViewHolderDC(ClienteInteresseCategoria item,ClienteInteresseCategoriaListViewHolderBase holder, int position);
    
}
