
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
import br.com.lojadigicom.precomed.modelo.DispositivoUsuario;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import br.com.lojadigicom.precomed.template.R;
import br.com.lojadigicom.precomed.framework.storage.DownloadImageTask;

/**
 * Created by Gerador de Codigo.
 */
public abstract class DispositivoUsuarioListAdapterBase extends RecyclerView.Adapter<DispositivoUsuarioListViewHolderBase>{

    //protected Cursor mCursor;
    protected List<DispositivoUsuario> mLista;
    private Context mContext;
    private DispositivoUsuarioListAdapterOnClickHandler mClickHandler;
    private View mEmptyView;
    private ItemChoiceManager mICM = null;

    public DispositivoUsuarioListAdapterBase(Context context, DispositivoUsuarioListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        mContext = context;
        mClickHandler = dh;
        mEmptyView = emptyView;
        //mICM = new ItemChoiceManager(this);
        //mICM.setChoiceMode(choiceMode);
    }


    @Override
    public final DispositivoUsuarioListViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if ( parent instanceof RecyclerView ) {
            int layoutId = -1;
            layoutId = getItemLayoutId();
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            view.setFocusable(true);
            DispositivoUsuarioListViewHolderBase saida = getViewHolder(view);
            saida.setLista(this.mLista);
            saida.setOnClickHandler(this.mClickHandler);
            return saida;
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    protected abstract int getItemLayoutId() ;
    protected abstract DispositivoUsuarioListViewHolderBase getViewHolder(View v);



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


    public final void swapCursor(List<DispositivoUsuario> novaLista) {
        //mCursor = newCursor;
        mLista = novaLista;
        notifyDataSetChanged();
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public static interface DispositivoUsuarioListAdapterOnClickHandler {
        void onClick(int id, DispositivoUsuarioListViewHolderBase vh);
    }
    
    
    @Override
    public void onBindViewHolder(DispositivoUsuarioListViewHolderBase holder, int position) {
        DispositivoUsuario obj = mLista.get(position);
        holder.setItem(obj);
        onBindViewHolderDC(obj,holder,position);
    }

    protected abstract void onBindViewHolderDC(DispositivoUsuario item,DispositivoUsuarioListViewHolderBase holder, int position);
    
    
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
