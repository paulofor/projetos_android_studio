package br.com.lojadigicom.coletorprecocliente.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.template.lista.InteresseProdutoListViewHolderBase;

/**
 * Created by Paulo on 15/11/2016.
 */

public class InteresseProdutoEsperaViewHolder extends InteresseProdutoListViewHolderBase{

    public TextView txtNomeProduto;
    //public TextView txtPrecoProduto;
    //public TextView txtLoja;
    public ImageView imgProduto;

    public InteresseProdutoEsperaViewHolder(View itemView) {
        super(itemView);
        //txtLoja = (TextView) itemView.findViewById(R.id.txtLoja);
        //txtPrecoProduto = (TextView)itemView.findViewById(R.id.txtPrecoProduto);
        txtNomeProduto = (TextView)itemView.findViewById(R.id.txtNomeProduto);
        imgProduto = (ImageView) itemView.findViewById(R.id.imgProduto);

    }
}
