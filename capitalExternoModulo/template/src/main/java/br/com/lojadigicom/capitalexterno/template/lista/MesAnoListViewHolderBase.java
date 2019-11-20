
package  br.com.lojadigicom.capitalexterno.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.lojadigicom.capitalexterno.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.capitalexterno.data.contract.MesAnoContract;
import br.com.lojadigicom.capitalexterno.framework.util.ItemChoiceManager;
import br.com.lojadigicom.capitalexterno.modelo.MesAno;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class MesAnoListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected MesAnoListAdapterBase.MesAnoListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private MesAno item;
	public void setItem(MesAno dado) {
		item = dado;
	}
	public MesAno getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(MesAnoListAdapterBase.MesAnoListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public MesAnoListViewHolderBase(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        DCIObjetoDominio item = (DCIObjetoDominio) mLista.get(this.getAdapterPosition());
        mClickHandler.onClick((int)item.getIdObj(), this);
    }
    
    protected final TextView getTextView(View v, int id, String nome) {
        TextView saida = (TextView) v.findViewById(id);
        if (saida==null) throw new RuntimeException("TextView " + nome + " nao encontrado em " + v);
        return saida;
    }
    protected final ImageView getImageView(View v, int id, String nome) {
        ImageView saida = (ImageView) v.findViewById(id);
        if (saida==null) throw new RuntimeException("ImageView " + nome + " nao encontrado em " + v);
        return saida;
    }
    protected final RatingBar getRatingBar(View v, int id, String nome) {
        RatingBar saida = (RatingBar) v.findViewById(id);
        if (saida==null) throw new RuntimeException("RatingBar " + nome + " nao encontrado em " + v);
        return saida;
    }
}
