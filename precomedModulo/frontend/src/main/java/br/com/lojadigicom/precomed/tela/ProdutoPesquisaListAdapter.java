package br.com.lojadigicom.precomed.tela;

import android.view.View;

import br.com.lojadigicom.frontend.R;
import br.com.lojadigicom.precomed.modelo.ProdutoPesquisa;
import br.com.lojadigicom.precomed.template.lista.ProdutoPesquisaListAdapterBase;
import br.com.lojadigicom.precomed.template.lista.ProdutoPesquisaListViewHolderBase;

/**
 * Created by Paulo on 05/11/2016.
 */

public class ProdutoPesquisaListAdapter extends ProdutoPesquisaListAdapterBase {
    @Override
    protected int getItemLayoutId() {
        return R.layout.item_produto_pesquisa;
    }

    @Override
    protected ProdutoPesquisaListViewHolderBase getViewHolder(View v) {
        return null;
    }

    @Override
    protected void onBindViewHolderDC(ProdutoPesquisa item, ProdutoPesquisaListViewHolderBase holder, int position) {

    }
}
