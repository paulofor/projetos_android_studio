
package  br.com.lojadigicom.coletorprecocliente.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.lojadigicom.coletorprecocliente.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.coletorprecocliente.data.contract.PalavraChavePesquisaContract;
import br.com.lojadigicom.coletorprecocliente.framework.util.ItemChoiceManager;
import br.com.lojadigicom.coletorprecocliente.modelo.PalavraChavePesquisa;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class PalavraChavePesquisaListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected PalavraChavePesquisaListAdapterBase.PalavraChavePesquisaListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private PalavraChavePesquisa item;
	public void setItem(PalavraChavePesquisa dado) {
		item = dado;
	}
	public PalavraChavePesquisa getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(PalavraChavePesquisaListAdapterBase.PalavraChavePesquisaListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public PalavraChavePesquisaListViewHolderBase(View itemView) {
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
