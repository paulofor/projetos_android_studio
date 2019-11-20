package br.com.lojadigicom.coletorprecocliente.adapter;

import android.content.Context;
import android.view.View;


import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListViewHolderBase;


/**
 * Created by Paulo on 09/11/15.
 */
public class NaturezaProdutoListAdapter extends NaturezaProdutoListAdapterBase {


    public NaturezaProdutoListAdapter(Context context, NaturezaProdutoListAdapterBase.NaturezaProdutoListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        super(context, dh, emptyView, choiceMode);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_natureza_produto;
    }

    @Override
    protected NaturezaProdutoListViewHolderBase getViewHolder(View v) {
        return new NatuezaProdutoListViewHolder(v);
    }

    @Override
    public void onBindViewHolderDC(NaturezaProduto naturezaProduto, NaturezaProdutoListViewHolderBase holder, int position) {
        NatuezaProdutoListViewHolder hold = (NatuezaProdutoListViewHolder) holder;
        //mCursor.moveToPosition(position);
        hold.mNomeNatureza.setText(naturezaProduto.getNomeNaturezaProduto());
        hold.mQtdeOportunidade.setText("" + naturezaProduto.getQtdeOportunidadeDia());
        //hold.mNomeNatureza.setText("Natureza Produto");


    }
}
