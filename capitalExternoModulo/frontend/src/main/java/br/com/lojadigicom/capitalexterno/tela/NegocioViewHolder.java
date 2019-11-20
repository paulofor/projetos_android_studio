package br.com.lojadigicom.capitalexterno.tela;

import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.capitalexterno.template.lista.NegocioListViewHolderBase;
import lojadigicom.com.br.frontend.R;

/**
 * Created by Paulo on 02/11/2016.
 */

public class NegocioViewHolder extends NegocioListViewHolderBase{

    public final TextView txtNomeNegocio;


    public NegocioViewHolder(View itemView) {
        super(itemView);
        txtNomeNegocio = (TextView) itemView.findViewById(R.id.txtNomeNegocio);
    }
}
