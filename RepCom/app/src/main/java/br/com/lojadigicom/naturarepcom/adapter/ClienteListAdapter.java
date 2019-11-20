package br.com.lojadigicom.naturarepcom.adapter;

import android.content.Context;
import android.view.View;

import br.com.lojadigicom.naturarepcom.R;
import br.com.lojadigicom.repcom.modelo.Cliente;
import br.com.lojadigicom.repcom.template.lista.ClienteListAdapterBase;
import br.com.lojadigicom.repcom.template.lista.ClienteListViewHolderBase;

/**
 * Created by Paulo on 29/03/2016.
 */
public class ClienteListAdapter extends ClienteListAdapterBase {

    public ClienteListAdapter(Context context, ClienteListAdapterOnClickHandler dh, View emptyView, int choiceMode) {
        super(context, dh, emptyView, choiceMode);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_cliente;
    }

    @Override
    protected ClienteListViewHolderBase getViewHolder(View v) {
        return new ClienteListViewHolder(v);
    }

    @Override
    protected void onBindViewHolderDC(Cliente item, ClienteListViewHolderBase holder, int position) {
        ClienteListViewHolder hold = (ClienteListViewHolder) holder;
        hold.mNomeCliente.setText(item.getNome());
    }
}
