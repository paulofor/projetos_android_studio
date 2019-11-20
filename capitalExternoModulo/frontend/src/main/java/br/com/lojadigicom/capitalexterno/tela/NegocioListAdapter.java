package br.com.lojadigicom.capitalexterno.tela;

import android.content.Context;
import android.view.View;

import br.com.lojadigicom.capitalexterno.framework.tela.ResourceObj;
import br.com.lojadigicom.capitalexterno.modelo.Negocio;
import br.com.lojadigicom.capitalexterno.template.lista.NegocioListAdapterBase;
import br.com.lojadigicom.capitalexterno.template.lista.NegocioListViewHolderBase;
import lojadigicom.com.br.frontend.R;

/**
 * Created by Paulo on 02/11/2016.
 */

public class NegocioListAdapter extends NegocioListAdapterBase {

    public NegocioListAdapter(Context context, NegocioListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        super(context,dh,emptyView,choiceMode);
    }

    @Override
    protected ResourceObj getLayoutItemResource() {
        return new ResourceObj(R.layout.item_negocio,"R.layout.item_negocio");
    }



    @Override
    protected NegocioListViewHolderBase getViewHolder(View v) {
        return new NegocioViewHolder(v);
    }

    @Override
    protected void onBindViewHolderDC(Negocio item, NegocioListViewHolderBase holder, int position) {
        NegocioViewHolder tela = (NegocioViewHolder) holder;
        tela.txtNomeNegocio.setText(item.getDescricao());
    }
}
