package br.com.lojadigicom.coletorprecocliente.adapter;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.template.lista.OportunidadeDiaListViewHolderBase;

/**
 * Created by Paulo on 15/01/16.
 */
public class OportunidadeDiaListViewHolder extends OportunidadeDiaListViewHolderBase {

    public TextView mNomeProduto;
    public TextView mNomeLoja;
    public TextView mPrecoAtual;
    public TextView mPrecoAnterior;
    public TextView mDataMudanca;
    public TextView mPrecoMinimo;
    public TextView mPrecoMedio;

    public OportunidadeDiaListViewHolder(View itemView) {
        super(itemView);
        mNomeProduto = (TextView) itemView.findViewById(R.id.txtNomeProduto);
        mNomeLoja = (TextView) itemView.findViewById(R.id.txtNomeLoja);
        mPrecoAtual = (TextView) itemView.findViewById(R.id.txtPrecoAtual);
        mPrecoAnterior = (TextView) itemView.findViewById(R.id.txtPrecoAnterior);
        mDataMudanca = (TextView) itemView.findViewById(R.id.txtDataMudanca);
        mPrecoMinimo = (TextView) itemView.findViewById(R.id.txtPrecoMinimo);
        mPrecoMedio = (TextView) itemView.findViewById(R.id.txtPrecoMedio);
    }
}
