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

public class InteresseProdutoMonitoraAdapter extends InteresseProdutoListAdapterBase{


    public InteresseProdutoMonitoraAdapter(Context context, InteresseProdutoListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        super(context, dh, emptyView, choiceMode);
    }

    @Override
    protected ResourceObj getLayoutItemResource() {
        return new ResourceObj(R.layout.item_interesse_produto_monitora3,"R.layout.item_interesse_produto_monitora3");
    }

    @Override
    protected InteresseProdutoListViewHolderBase getViewHolder(View v) {
        return new InteresseProdutoMonitoraViewHolder(v);
    }

    @Override
    protected void onBindViewHolderDC(InteresseProduto item, InteresseProdutoListViewHolderBase holder, int position) {
        InteresseProdutoMonitoraViewHolder hold = (InteresseProdutoMonitoraViewHolder) holder;
        ProdutoCliente produto = item.getProdutoCliente_ReferenteA();
        hold.imgPrecoBaixo.setVisibility(View.INVISIBLE);
        hold.imgPrecoAlto.setVisibility(View.INVISIBLE);
        if (produto!=null) {
            hold.txtLoja.setText(produto.getLoja());
            hold.txtNomeProduto.setText(produto.getNome());
            hold.txtPreco.setText("R$ " + item.getValorTela());
            if (item.getNovo()) {
                hold.rtbRecomendacao.setVisibility(View.INVISIBLE);
                hold.txtNovo.setVisibility(View.VISIBLE);
            } else {
                hold.rtbRecomendacao.setVisibility(View.VISIBLE);
                hold.txtNovo.setVisibility(View.INVISIBLE);
                hold.rtbRecomendacao.setRating(item.getNota());
                if (item.getDiferencaAnterior()<0) {
                    hold.imgPrecoBaixo.setVisibility(View.VISIBLE);
                }
                if (item.getDiferencaAnterior()>0) {
                    hold.imgPrecoAlto.setVisibility(View.VISIBLE);
                }
            }
            if (item.getMudanca()) {
                hold.imgMudanca.setVisibility(View.VISIBLE);
            } else {
                hold.imgMudanca.setVisibility(View.INVISIBLE);
            }

            //this.setImagemFromUrl(hold.imgProduto,produto.getImagem());
            this.carregaImagemCache(produto.getImagem(),hold.imgProduto);
        } else {
            // existe erro - não veio o produto
            hold.txtLoja.setText("-");
            hold.txtNomeProduto.setText("-");
            hold.txtPreco.setText("-");
        }
    }
}
