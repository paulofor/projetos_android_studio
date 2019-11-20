
package  br.com.lojadigicom.repcom.template.lista;


import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.lojadigicom.repcom.framework.modelo.DCIObjetoDominio;
import br.com.lojadigicom.repcom.data.contract.CategoriaProdutoProdutoContract;
import br.com.lojadigicom.repcom.framework.util.ItemChoiceManager;
import br.com.lojadigicom.repcom.modelo.CategoriaProdutoProduto;

/**
 * Created by Paulo on 03/11/15.
 */
public abstract class CategoriaProdutoProdutoListViewHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener{

    //protected Cursor mCursor;
    protected List mLista;
    protected CategoriaProdutoProdutoListAdapterBase.CategoriaProdutoProdutoListAdapterOnClickHandler mClickHandler;
    protected ItemChoiceManager mICM = null;


	private CategoriaProdutoProduto item;
	public void setItem(CategoriaProdutoProduto dado) {
		item = dado;
	}
	public CategoriaProdutoProduto getItem() {
		return item;
	}

    //public void setCursor(Cursor valor) {
    //    mCursor = valor;
    //}
    public void setLista(List valor) {
    	mLista = valor;
    }
    
    
    public void setOnClickHandler(CategoriaProdutoProdutoListAdapterBase.CategoriaProdutoProdutoListAdapterOnClickHandler valor){
        mClickHandler = valor;
    }

    public CategoriaProdutoProdutoListViewHolderBase(View itemView) {
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
