package br.com.lojadigicom.coletorprecocliente.adapter;

import android.content.Context;
import android.view.View;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.framework.tela.ResourceObj;
import br.com.lojadigicom.coletorprecocliente.modelo.InteresseProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.ProdutoCliente;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListAdapterBase;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListViewHolderBase;

/**
 * Created by Paulo on 15/11/2016.
 */

public class InteresseProdutoEsperaAdapter extends InteresseProdutoListAdapterBase{


    public InteresseProdutoEsperaAdapter(Context context, InteresseProdutoListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        super(context, dh, emptyView, choiceMode);
    }

    @Override
    protected ResourceObj getLayoutItemResource() {
        return new ResourceObj(R.layout.item_interesse_produto_espera,"R.layout.item_interesse_produto_espera");
    }

    @Override
    protected InteresseProdutoListViewHolderBase getViewHolder(View v) {
        return new InteresseProdutoEsperaViewHolder(v);
    }

    @Override
    protected void onBindViewHolderDC(InteresseProduto item, InteresseProdutoListViewHolderBase holder, int position) {
        InteresseProdutoEsperaViewHolder hold = (InteresseProdutoEsperaViewHolder) holder;
        ProdutoCliente produto = item.getProdutoCliente_ReferenteA();
        if (produto!=null) {
            //hold.txtLoja.setText(produto.getLoja());
            hold.txtNomeProduto.setText(produto.getNome());
            //hold.txtPrecoProduto.setText(produto.getPrecoAtualTela());
            this.carregaImagemCache(produto.getImagem(),hold.imgProduto);
            //this.setImagemFromUrl(hold.imgProduto,produto.getImagem());
        } else {
            // existe erro - não veio o produto
            //hold.txtLoja.setText("-");
            hold.txtNomeProduto.setText("-");
            //hold.txtPrecoProduto.setText("-");
        }
    }
}
