
package  br.com.lojadigicom.treinoacademia.template.lista;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import br.com.lojadigicom.treinoacademia.framework.tela.ResourceObj;
import br.com.lojadigicom.treinoacademia.framework.util.ItemChoiceManager;
import java.util.List;
import br.com.lojadigicom.treinoacademia.modelo.GrupoMuscular;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import br.com.lojadigicom.treinoacademia.template.R;
import br.com.lojadigicom.treinoacademia.framework.storage.DownloadImageTask;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.com.lojadigicom.treinoacademia.template.R;

/**
 * Created by Gerador de Codigo.
 */
public abstract class GrupoMuscularListAdapterBase extends RecyclerView.Adapter<GrupoMuscularListViewHolderBase>{

    //protected Cursor mCursor;
    protected List<GrupoMuscular> mLista;
    private Context mContext;
    private GrupoMuscularListAdapterOnClickHandler mClickHandler;
    private View mEmptyView;
    private ItemChoiceManager mICM = null;
    protected ResourceObj resourceObj = getLayoutItemResource();

    public GrupoMuscularListAdapterBase(Context context, GrupoMuscularListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        mContext = context;
        mClickHandler = dh;
        mEmptyView = emptyView;
        //mICM = new ItemChoiceManager(this);
        //mICM.setChoiceMode(choiceMode);
    }


    @Override
    public final GrupoMuscularListViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if ( parent instanceof RecyclerView ) {
            int layoutId = -1;
            layoutId = this.resourceObj.getCodigo();
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            // For?ando para o item de lista aparecer sem erros.
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            view.setFocusable(true);
            GrupoMuscularListViewHolderBase saida = getViewHolder(view);
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
    protected abstract GrupoMuscularListViewHolderBase getViewHolder(View v);

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


    public final void swapCursor(List<GrupoMuscular> novaLista) {
        //mCursor = newCursor;
        mLista = novaLista;
        notifyDataSetChanged();
        mEmptyView.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public static interface GrupoMuscularListAdapterOnClickHandler {
        void onClick(int id, GrupoMuscularListViewHolderBase vh);
    }
    
    
    @Override
    public void onBindViewHolder(GrupoMuscularListViewHolderBase holder, int position) {
        GrupoMuscular obj = mLista.get(position);
        holder.setItem(obj);
        onBindViewHolderDC(obj,holder,position);
    }

    protected abstract void onBindViewHolderDC(GrupoMuscular item,GrupoMuscularListViewHolderBase holder, int position);
    
    
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
