
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

import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.framework.util.ItemChoiceManager;
import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.DispositivoUsuario;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import br.com.lojadigicom.coletorprecocliente.template.R;
import br.com.lojadigicom.coletorprecocliente.framework.storage.DownloadImageTask;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.com.lojadigicom.coletorprecocliente.template.R;

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
    protected ResourceObj resourceObj = getLayoutItemResource();

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
            layoutId = this.resourceObj.getCodigo();
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            // For?ando para o item de lista aparecer sem erros.
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            view.setFocusable(true);
            DispositivoUsuarioListViewHolderBase saida = getViewHolder(view);
            //DCLog.d(DCLog.TRACE_DISPLAY,this,"(Adapter)" + this.getClass().getSimpleName() + " / " + saida.getClass().getSimpleName());
            saida.setLista(this.mLista);
            saida.setOnClickHandler(this.mClickHandler);
            return saida;
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    //protected abstract int getItemLayoutId() ;
    protected abstract ResourceObj getLayoutItemResource();
    protected abstract DispositivoUsuarioListViewHolderBase getViewHolder(View v);

	public String getLayoutItem() {
		return resourceObj.getNome();
	}


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
    
    
    //protected void setImagemFromUrl(ImageView obj, String urlImagem) {
    //    new DownloadImageTask(obj).execute(urlImagem);
    //}
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
    
    protected void carregaImagemCache(String urlImagem, ImageView viewImagem) {
        Glide.with(mContext)
                .load(urlImagem)
                .error(R.drawable.img_nao_disponivel)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewImagem);
    }
}
