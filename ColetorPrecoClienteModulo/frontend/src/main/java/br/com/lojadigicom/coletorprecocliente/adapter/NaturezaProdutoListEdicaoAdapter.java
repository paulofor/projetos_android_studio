package br.com.lojadigicom.coletorprecocliente.adapter;

import android.content.Context;
import android.view.View;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListViewHolderBase;

/**
 * Created by Paulo on 18/02/16.
 */
public class NaturezaProdutoListEdicaoAdapter extends NaturezaProdutoListAdapterBase {

    public NaturezaProdutoListEdicaoAdapter(Context context, NaturezaProdutoListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        super(context, dh, emptyView, choiceMode);
    }

    @Override
    protected ResourceObj getLayoutItemResource() {
        return new ResourceObj(R.layout.item_natureza_produto_edita,"R.layout.item_natureza_produto_edita");
    }

    @Override
    protected NaturezaProdutoListViewHolderBase getViewHolder(View v) {
        return new NaturezaProdutoListEdicaoViewHolder(v);
    }

    @Override
    public void onBindViewHolderDC(NaturezaProduto naturezaProduto, NaturezaProdutoListViewHolderBase holder, int position) {
        NaturezaProdutoListEdicaoViewHolder hold = (NaturezaProdutoListEdicaoViewHolder) holder;
        hold.setNomeNatureza(naturezaProduto.getNomeNaturezaProduto());
        if (naturezaProduto.existeListaUsuarioPesquisa_PesquisadoPor()) {
            hold.setChecked(true);
        } else {
            hold.setChecked(false);
        }
    }
}
