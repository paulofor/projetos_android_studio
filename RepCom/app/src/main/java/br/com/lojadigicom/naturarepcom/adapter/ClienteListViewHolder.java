package br.com.lojadigicom.naturarepcom.adapter;

import android.view.View;
import android.widget.TextView;

import br.com.lojadigicom.naturarepcom.R;
import br.com.lojadigicom.repcom.template.lista.ClienteListViewHolderBase;

/**
 * Created by Paulo on 29/03/2016.
 */
public class ClienteListViewHolder extends ClienteListViewHolderBase {

    public TextView mNomeCliente = null;

    public ClienteListViewHolder(View itemView) {
        super(itemView);
        mNomeCliente = (TextView) itemView.findViewById(R.id.txtNomeCliente);
    }
}
