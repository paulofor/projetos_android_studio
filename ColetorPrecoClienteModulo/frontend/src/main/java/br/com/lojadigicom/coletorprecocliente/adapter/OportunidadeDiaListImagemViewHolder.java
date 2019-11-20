package br.com.lojadigicom.coletorprecocliente.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListViewHolderBase;

/**
 * Created by Paulo on 24/10/16.
 */
public class OportunidadeDiaListImagemViewHolder extends OportunidadeDiaListViewHolderBase {

    public TextView mNomeProduto;
    public TextView mNomeLoja;
    public TextView mPrecoAtual;
    public TextView mDataMudanca;
    public ImageView mImgemProduto;

    public OportunidadeDiaListImagemViewHolder(View itemView) {
        super(itemView);
        mNomeProduto = (TextView) itemView.findViewById(R.id.txtNomeProduto);
        mNomeLoja = (TextView) itemView.findViewById(R.id.txtNomeLoja);
        mPrecoAtual = (TextView) itemView.findViewById(R.id.txtPrecoAtual);
        mImgemProduto = (ImageView) itemView.findViewById(R.id.imgProduto);
        mDataMudanca = (TextView) itemView.findViewById(R.id.txtDataMudanca);
    }
}
