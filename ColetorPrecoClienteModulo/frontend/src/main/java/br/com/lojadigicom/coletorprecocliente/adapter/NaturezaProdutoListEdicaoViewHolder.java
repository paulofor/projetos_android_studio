package br.com.lojadigicom.coletorprecocliente.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.template.lista.NaturezaProdutoListViewHolderBase;

/**
 * Created by Paulo on 18/02/16.
 */
public class NaturezaProdutoListEdicaoViewHolder extends NaturezaProdutoListViewHolderBase {

    //public final TextView mNomeNatureza;
    //public final TextView mQtdeOportunidade;
    private final CheckBox mChkNaturezaProduto;



    public NaturezaProdutoListEdicaoViewHolder(View itemView) {
        super(itemView);
        mChkNaturezaProduto = (CheckBox) itemView.findViewById(R.id.chkNaturezaProduto);
        //mNomeNatureza = (TextView) itemView.findViewById(R.id.txtNomeNatureza);
        //mQtdeOportunidade = (TextView) itemView.findViewById(R.id.txtQtdeOportunidade);
    }

    public void setNomeNatureza(String nomeNatureza) {
        mChkNaturezaProduto.setText(nomeNatureza);
    }
    public boolean isChecked() {
        return mChkNaturezaProduto.isChecked();
    }

    public void setChecked(boolean b) {
        mChkNaturezaProduto.setChecked(b);
    }
}
