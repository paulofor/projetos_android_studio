
package  br.com.lojadigicom.precomed.template.lista;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.lojadigicom.precomed.framework.util.ItemChoiceManager;
import java.util.List;
import br.com.lojadigicom.precomed.modelo.PrecoProduto;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import br.com.lojadigicom.precomed.template.R;
import br.com.lojadigicom.precomed.framework.storage.DownloadImageTask;

/**
 * Created by Gerador de Codigo.
 */
public abstract class PrecoProdutoListAdapterBase extends RecyclerView.Adapter<PrecoProdutoListViewHolderBase>{

    //protected Cursor mCursor;
    protected List<PrecoProduto> mLista;
    private Context mContext;
    private PrecoProdutoListAdapterOnClickHandler mClickHandler;
    private View mEmptyView;
    private ItemChoiceManager mICM = null;

    public PrecoProdutoListAdapterBase(Context context, PrecoProdutoListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        mContext = context;
        mClickHandler = dh;
        mEmptyView = emptyView;
        //mICM = new ItemChoiceManager(this);
        //mICM.setChoiceMode(choiceMode);
    }


    @Override
    public final PrecoProdutoListViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if ( parent instanceof RecyclerView ) {
            int layoutId = -1;
            layoutId = getItemLayoutId();
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            view.setFocusable(true);
            PrecoProdutoListViewHolderBase saida = getViewHolder(view);
            saida.setLista(this.mLista);
            saida.setOnClickHandler(this.mClickHandler);
            return saida;
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    protected abstract int getItemLayoutId() ;
    protected abstract PrecoProdutoListViewHolderBase getViewHolder(View v);



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


    public final void swapCursor(List<PrecoProduto> novaLista) {
        //mCursor = newCursor;
        mLista = novaLista;
        notifyDataSetChanged();
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public static interface PrecoProdutoListAdapterOnClickHandler {
        void onClick(int id, PrecoProdutoListViewHolderBase vh);
    }
    
    
    @Override
    public void onBindViewHolder(PrecoProdutoListViewHolderBase holder, int position) {
        PrecoProduto obj = mLista.get(position);
        holder.setItem(obj);
        onBindViewHolderDC(obj,holder,position);
    }

    protected abstract void onBindViewHolderDC(PrecoProduto item,PrecoProdutoListViewHolderBase holder, int position);
    
    
    protected void setImagemFromUrl(ImageView obj, String urlImagem) {
        new DownloadImageTask(obj).execute(urlImagem);
    }
    /*
    protected void setImagemFromUrl(ImageView obj, String urlImagem) {
        Glide.with(mContext)
                .load(urlImagem)
                .asBitmap()
                .error(R.drawable.img_nao_disponivel)
                .into(obj)
                .getRequest();
    }
    */
}