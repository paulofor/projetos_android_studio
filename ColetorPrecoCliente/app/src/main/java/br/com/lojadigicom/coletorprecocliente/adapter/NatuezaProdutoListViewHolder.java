package br.com.lojadigicom.coletorprecocliente.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListViewHolderBase;



public class NatuezaProdutoListViewHolder extends NaturezaProdutoListViewHolderBase {

    public final TextView mNomeNatureza;
    public final TextView mQtdeOportunidade;


    public NatuezaProdutoListViewHolder(View itemView) {
        super(itemView);
        mNomeNatureza = (TextView) itemView.findViewById(R.id.txtNomeNatureza);
        mQtdeOportunidade = (TextView) itemView.findViewById(R.id.txtQtdeOportunidade);
    }


}
